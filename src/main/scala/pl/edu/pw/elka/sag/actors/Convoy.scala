package pl.edu.pw.elka.sag.actors

import akka.actor.{Actor, ActorLogging, Props}
import pl.edu.pw.elka.sag.graph.{Path, PathState}
import pl.edu.pw.elka.sag.messages._

class Convoy  extends Actor with ActorLogging{
  override def receive: Receive = {
    case ConvoyPrepare(numVehicles:Int, path:Path) => {
      log.debug("Convoy::ConvoyPrepare")
      context.actorOf(Props{new Vehicle(numVehicles, self, path)}, "V" + numVehicles)
    }

    case ConvoyMove(path:PathState) => {
      log.debug("Convoy::ConvoyMove")
      context.children.foreach(_ ! VehicleMove(path, -1))
    }

    case VehicleDone => {
      log.debug("Convoy::VehicleDone")
      context.parent ! ConvoyPrepared
    }

    case  VehicleDestinationReached => {
      log.debug("Convoy::VehicleDestinationReached")
      println("Convoy reached final destination")
      context.parent ! ConvoyDestinationReached
    }
  }
}
