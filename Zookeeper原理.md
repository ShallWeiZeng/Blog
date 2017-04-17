# Zookeeper原理
ZooKeeper是一个分布式的，开放源码的分布式应用程序协调服务，它包含一个简单的原语集，分布式应用程序可以基于它实现同步服务，配置维护和命名服务等
## Zookeeper基本原理
Zookeeper中的角色主要有以下三类
领导者（leader）：领导者负责进行投票的发起和决议<br/>
学习者（Learner）---分为两类<br/>
- 跟随者（Follower）：Follower用于接收客户端请求并向客户端返回结果<br/>
- 观察者（ObServer）：Observer可以接收客户端连接，将写请求转发给Leader节点。但Observer不参加投票过程，只同步Leader的状态。Observer的目的是为了扩展系统，提高读取速度<br/>

客户端（Client）：请求发起方
