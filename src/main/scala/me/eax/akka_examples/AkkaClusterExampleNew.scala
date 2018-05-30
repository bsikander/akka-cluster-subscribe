package me.eax.akka_examples

import akka.cluster.ClusterEvent._
import akka.actor._
import akka.event._
import akka.cluster._

class ClusterListenerNew extends Actor with ActorLogging {

  val cluster = Cluster(context.system)

  // subscribe to cluster changes, re-subscribe when restart
  override def preStart() {
    cluster.join(AddressFromURIString("akka.tcp://system@127.0.0.1:2551"))
    cluster.subscribe(self, classOf[MemberEvent])
  }

  override def postStop() {
    cluster.unsubscribe(self)
  }

  def receive = LoggingReceive {
    case MemberUp(member) =>
      log.info(s"[Listener] node is up: $member")

    case ev: MemberEvent =>
      log.info(s"[Listener] event: $ev")
  }
}

object AkkaClusterExampleNew extends App {
  val system = ActorSystem("system")
  system.actorOf(Props[ClusterListenerNew], "clusterListener")
  system.awaitTermination()
}
