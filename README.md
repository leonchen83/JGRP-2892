# This project is to reproduce JGRP-2892

# Run Main

If async_discovery="false", you will see the following output in Main:

```shell
[Node-1] 25-07-17 15:02:32.373 INFO [main JChannel.info:131]local_addr: 24f53c45-5dd1-4a08-aaef-69bcff6060a1, name: Node-1
[Node-1] 
[Node-1] -------------------------------------------------------------------
[Node-1] GMS: address=Node-1, cluster=MyTestCluster, physical address=192.168.99.149:7800
[Node-1] -------------------------------------------------------------------
[Node-1] 25-07-17 15:02:32.389 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [done]
[Node-1] 25-07-17 15:02:32.391 INFO [main GMS.info:131]Node-1: no members discovered after 2 ms: creating cluster as coordinator
[Node-1] [Node-1] View: [Node-1|0] (1) [Node-1]
[Node-1] [Node-1] View: [Node-1|1] (2) [Node-1, Node-2]
[Node-1] [Node-1] View: [Node-1|2] (3) [Node-1, Node-2, Node-3]
[Node-1] [Node-1] View: [Node-1|3] (4) [Node-1, Node-2, Node-3, Node-4]
[Node-1] [Node-1] View: [Node-1|4] (5) [Node-1, Node-2, Node-3, Node-4, Node-5]
[Node-1] [Node-1] View: [Node-1|5] (6) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6]
[Node-1] [Node-1] View: [Node-1|6] (7) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6, Node-7]
[Node-1] [Node-1] View: [Node-1|7] (8) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6, Node-7, Node-8]
[Node-1] [Node-1] View: [Node-1|8] (9) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6, Node-7, Node-8, Node-9]
[Node-1] [Node-1] View: [Node-1|9] (10) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6, Node-7, Node-8, Node-9, Node-10]
Killing Node-1 (simulating kill -9)...
[Node-11] 25-07-17 15:02:42.332 INFO [main JChannel.info:131]local_addr: 016bf1e8-2225-4967-9a43-624983d68a99, name: Node-11
[Node-11] 
[Node-11] -------------------------------------------------------------------
[Node-11] GMS: address=Node-11, cluster=MyTestCluster, physical address=192.168.99.149:7800
[Node-11] -------------------------------------------------------------------
[Node-11] 25-07-17 15:02:42.468 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=10 rsps (1 coords) [done]
[Node-11] 25-07-17 15:02:48.486 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:02:48.596 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=10 rsps (1 coords) [done]
[Node-11] 25-07-17 15:02:54.602 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:02:54.714 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=10 rsps (1 coords) [done]
[Node-11] 25-07-17 15:03:00.721 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:03:00.848 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=9 rsps (1 coords) [done]
[Node-11] [Node-11] View: [Node-2|11] (10) [Node-2, Node-3, Node-4, Node-5, Node-6, Node-7, Node-8, Node-9, Node-10, Node-11]
[Node-11] 25-07-17 15:03:23.724 INFO [Timer runner-1,MyTestCluster,Node-11 FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=false, async=false, timeout=0, resp=10 rsps (1 coords) [done]

```

If async_discovery="true", you will see the following output in Main:

```shell
[Node-1] 25-07-17 15:04:22.627 INFO [main JChannel.info:131]local_addr: 5060a640-6cb1-427d-b232-53afd4622a66, name: Node-1
[Node-1] 
[Node-1] -------------------------------------------------------------------
[Node-1] GMS: address=Node-1, cluster=MyTestCluster, physical address=192.168.99.149:7800
[Node-1] -------------------------------------------------------------------
[Node-1] 25-07-17 15:04:22.640 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-1] 25-07-17 15:04:22.641 INFO [main GMS.info:131]Node-1: no members discovered after 3 ms: creating cluster as coordinator
[Node-1] [Node-1] View: [Node-1|0] (1) [Node-1]
[Node-1] [Node-1] View: [Node-1|1] (2) [Node-1, Node-2]
[Node-1] [Node-1] View: [Node-1|2] (3) [Node-1, Node-2, Node-3]
[Node-1] [Node-1] View: [Node-1|3] (4) [Node-1, Node-2, Node-3, Node-4]
[Node-1] [Node-1] View: [Node-1|4] (5) [Node-1, Node-2, Node-3, Node-4, Node-5]
[Node-1] [Node-1] View: [Node-1|5] (6) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6]
[Node-1] [Node-1] View: [Node-1|6] (7) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6, Node-7]
[Node-1] [Node-1] View: [Node-1|7] (8) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6, Node-7, Node-8]
[Node-1] [Node-1] View: [Node-1|8] (9) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6, Node-7, Node-8, Node-9]
[Node-1] [Node-1] View: [Node-1|9] (10) [Node-1, Node-2, Node-3, Node-4, Node-5, Node-6, Node-7, Node-8, Node-9, Node-10]
Killing Node-1 (simulating kill -9)...
[Node-11] 25-07-17 15:04:32.735 INFO [main JChannel.info:131]local_addr: 3b03dfd1-a764-4691-a79f-25e639b83d4a, name: Node-11
[Node-11] 
[Node-11] -------------------------------------------------------------------
[Node-11] GMS: address=Node-11, cluster=MyTestCluster, physical address=192.168.99.149:7800
[Node-11] -------------------------------------------------------------------
[Node-11] 25-07-17 15:04:32.747 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-11] 25-07-17 15:04:38.879 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:04:38.880 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-11] 25-07-17 15:04:44.894 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:04:44.897 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-11] 25-07-17 15:04:50.911 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:04:50.912 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-11] 25-07-17 15:04:56.924 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:04:56.927 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-11] 25-07-17 15:05:02.938 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:05:02.938 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-11] 25-07-17 15:05:08.940 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:05:08.941 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-11] 25-07-17 15:05:14.955 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:05:14.956 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
[Node-11] 25-07-17 15:05:20.969 WARN [main GMS.warn:115]Node-11: JOIN(Node-11) sent to Node-1 timed out (after 6000 ms), on try 0
[Node-11] 25-07-17 15:05:20.970 INFO [main FilePing.findMembers:23]FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]

```

We can observe that when `async_discovery="true"`, the console continuously prints messages like:

```
[Node-11] 25-07-17 15:05:20.970 INFO [main FilePing.findMembers:23] FilePing.findMembers: members=null, initial_discovery=true, async=false, timeout=6000, resp=0 rsps (0 coords) [pending]
```


