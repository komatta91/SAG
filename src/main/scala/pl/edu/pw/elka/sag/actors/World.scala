package pl.edu.pw.elka.sag.actors

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.Logging
import pl.edu.pw.elka.sag.messages.BeginSimulation
import pl.edu.pw.elka.sag.graph.{PathGenerator, PathState, PathStateGenerator}
import pl.edu.pw.elka.sag.messages._

class World(private val pathGenerator: PathGenerator, private val pathStateGenerator: PathStateGenerator) extends Actor with ActorLogging {
  val convoy = context.actorOf(Props[Convoy], name = "convoy")
  var path = pathGenerator.getPath()

  println("****** Generated Path ******")
  println(("Path length: " + path.len))
  println(("Max speed: " + path.len))
  path.crossings.foreach({ case (key, value) => println("Cross " + value + " on position " + (key - (key % 0.01))) })
  println("****************************")

  def generatePathState() : PathState = {
    pathStateGenerator.getPathState(path);
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
