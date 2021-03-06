# akka-cluster-example
Akka Cluster - simple example


## Steps to reproduce:
* Launch first node which joins itself and runs at port 2551 using 
```
sbt "runMain me.eax.akka_examples.AkkaClusterExample"
```
* Modify the application.conf and change the port to 0.
* Launch the second node which join the first node at 2551 and also subscribes to the events.
```
sbt "runMain me.eax.akka_examples.AkkaClusterExampleNew"
```
* In the output of second node, you should see 2 events for MemberUp. 1 for old node 2551 and 1 for new one.
```
[Listener] node is up: Member(address = akka.tcp://system@127.0.0.1:2551, status = Up)
[Listener] node is up: Member(address = akka.tcp://system@127.0.0.1:55450, status = Up)
```
