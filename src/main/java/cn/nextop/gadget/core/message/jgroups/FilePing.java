package cn.nextop.gadget.core.message.jgroups;

import java.io.File;
import java.util.List;

import org.jgroups.Address;
import org.jgroups.protocols.FILE_PING;
import org.jgroups.protocols.PingData;
import org.jgroups.util.Responses;
import org.jgroups.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Baoyi Chen
 */
public class FilePing extends FILE_PING {
	private static final Logger LOGGER = LoggerFactory.getLogger(FilePing.class);
	
	@Override
	public Responses findMembers(List<Address> members, boolean initial_discovery, boolean async, long timeout) {
		Responses out = super.findMembers(members, initial_discovery, async, timeout);
		LOGGER.info("FilePing.findMembers: members={}, initial_discovery={}, async={}, timeout={}, resp={}",
				members, initial_discovery, async, timeout, out.toString());
		return out;
	}
	
	protected void readAll(List<Address> members, String clustername, Responses responses) {
		File dir=new File(root_dir, clustername);
		if(!dir.exists())
			dir.mkdir();
		
		File[] files=dir.listFiles(filter); // finds all files ending with '.list'
		for(File file: files) {
			List<PingData> list=null;
			// implementing a simple spin lock doing a few attempts to read the file
			// this is done since the file may be written in concurrency and may therefore not be readable
			for(int i=0; i < 3; i++) {
				if(file.exists()) {
					try {
						if((list=read(file)) != null)
							break;
					}
					catch(Exception e) {
					}
				}
				Util.sleep(50);
			}
			
			if(list == null) {
				log.warn("failed reading " + file.getAbsolutePath());
				continue;
			}
			
			// JGRP-2892
			// simulate a delay to reproduce the issue
			Util.sleep(100);
			
			for(PingData data: list) {
				if(members == null || members.contains(data.getAddress()))
					responses.addResponse(data, true);
				if(local_addr != null && !local_addr.equals(data.getAddress()))
					addDiscoveryResponseToCaches(data.getAddress(), data.getLogicalName(), data.getPhysicalAddr());
			}
		}
	}
}
