/*
 * Copyright 2016-2017 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.nextop.gadget.core.message.jgroups;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Baoyi Chen
 */
public class Main {
	private static final int NODE_COUNT = 10;
	private static final List<Process> processes = new CopyOnWriteArrayList<>();
	static String localIp;
	static {
		try {
			localIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// Clean up previous cluster files
		Files.list(Path.of("/app/erebor/jgroups/MyTestCluster")).forEach(path -> {
			try {
				Files.deleteIfExists(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		// cleanup on JVM shutdown
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("JVM shutting down, cleaning up subprocesses...");
			for (Process p : processes) {
				if (p.isAlive()) {
					p.destroyForcibly();
				}
			}
		}));
		
		String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String mainClass = "cn.nextop.gadget.core.message.jgroups.ClusterNode";
		
		for (int i = 1; i <= NODE_COUNT; i++) {
			String nodeName = "Node-" + i;
			start(javaBin, classpath, mainClass, nodeName, i == 1);
			Thread.sleep(500);
		}
		
		Thread.sleep(5000);
		
		// kill Node-1 and create Node-11
		System.out.println("Killing Node-1 (simulating kill -9)...");
		stop(0);
		start(javaBin, classpath, mainClass, "Node-11", true);
		System.in.read();
	}
	
	private static void stop(int i) {
		processes.get(i).destroyForcibly();
		processes.remove(i);
	}
	
	private static void start(String javaBin, String classpath, String mainClass, String nodeName, boolean log) throws IOException {
		ProcessBuilder pb = new ProcessBuilder(javaBin, "-Djgroups.bind_addr=" + localIp, "-cp", classpath, mainClass, nodeName);
		pb.redirectErrorStream(true);
		Process process = pb.start();
		processes.add(process);
		
		if (log) {
			new Thread(() -> {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println("[" + nodeName + "] " + line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
