package pl.edu.pw.elka.sag.actors

import akka.actor.{Actor, ActorLogging, Props}
import pl.edu.pw.elka.sag.messages.BeginSimulation
import pl.edu.pw.elka.sag.graph.{PathGenerator, PathState, PathStateGenerator}
import pl.edu.pw.elka.sag.messages._

/**
  * Pseudo-agent providing the world state and simulation process orchestration
  * @param pathGenerator
  * @param pathStateGenerator
  */
class World(private val pathGenerator: PathGenerator, private val pathStateGenerator: PathStateGenerator) extends Actor with ActorLogging {
  val convoy = context.actorOf(Props[Convoy], name = "convoy")
  var path = pathGenerator.getPath()

  println("****** Generated Path ******")
  println(("Path length: %8.2f".formatLocal(java.util.Locale.US, path.len)))
  println(("Max speed: %8.2f".formatLocal(java.util.Locale.US, path.maxSpeed)))
  path.crossings.foreach({ case (key, value) => println("Cross " + value + " on position " + "%8.2f".formatLocal(java.util.Locale.US, key)) })
  println("****************************")

  /**
    * Path state getter
    * @return - next Path state
    */
  def generatePathState() : PathState = {
    pathStateGenerator.getPathState(path)
  }

  override def receive: Receive = {
    case BeginSimulation(n:Int) => {
      log.debug("World::BeginSimulation")
      convoy ! ConvoyPrepare(n, path)
    }
    case ConvoyPrepared => {
      log.debug("World::ConvoyPrepared")
      convoy ! ConvoyMove(generatePathState())
    }

    case ConvoyMoveFinish => {
      log.debug("World::ConvoyMoveFinish")
      convoy ! ConvoyMove(generatePathState())
    }

    case ConvoyDestinationReached => {
      log.debug("World::ConvoyDestinationReached")
      context.system.terminate()
    }
  }
}
