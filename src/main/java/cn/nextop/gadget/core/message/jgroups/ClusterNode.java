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

import java.util.concurrent.ThreadLocalRandom;

import org.jgroups.JChannel;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.jgroups.conf.ClassConfigurator;

/**
 * @author Baoyi Chen
 */
public class ClusterNode implements Receiver {
	
	private JChannel channel;
	private final String name;
	private final String configFile = "jgroups-custom.xml";
	
	public ClusterNode(String name) {
		this.name = name;
	}
	
	public void start() throws Exception {
		channel = new JChannel(configFile).setName(name);
		channel.setReceiver(this);
		channel.connect("MyTestCluster");
	}
	
	@Override
	public void viewAccepted(View view) {
		System.out.println("[" + name + "] View: " + view);
	}
	
	public static void main(String[] args) throws Exception {
		ClassConfigurator.addProtocol((short) 1024, FilePing.class);
		String name = "Node" + ThreadLocalRandom.current().nextInt(1000);
		if (args.length > 0) {
			name = args[0];
		}
		new ClusterNode(name).start();
		Thread.currentThread().join();
	}
}
