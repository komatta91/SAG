package pl.edu.pw.elka.sag.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import pl.edu.pw.elka.sag.graph.{Path, PathState}
import pl.edu.pw.elka.sag.messages.{VehicleDestinationReached, VehicleDone, VehicleMove}

class Vehicle(private val num: Int, private val convoy: ActorRef, path: Path) extends Actor with ActorLogging {
  val maxSpeed = path.maxSpeed
  var speed =  0.0
  var position = 0.0

  def doMove(pathState: PathState, prevPosition: Double): PathState = {
    var drive = true
    if (path.enterCrossing(position, position + speed)) {
      val crossing = path.getCrossing(position, position + speed)
      if (pathState.isDriveThrough(crossing)) {
        position += speed
      } else {
        position = path.getCrossingPoint(crossing)
        drive = false
      }
    } else {
      position += speed
    }
    if (drive) {
      if (prevPosition == -1) {
        speed =  maxSpeed / 2
      } else {
        speed = math.min(maxSpeed, prevPosition - position)
      }
    } else {
      speed = 0
    }
    if (position > path.len) {
      position = path.len
    }

    pathState
  }

  def checkFinish(): Boolean = {
    path.finish(position)
  }

  def receive: Receive = {
    case VehicleMove(pathState: PathState, prevPosition: Double) => {
      if (!checkFinish()) {
        val newPathState = doMove(pathState, prevPosition)
        println(pathState.iterNum + ": Vehicle: " + num + f" moving to:" + "%8.2f".formatLocal(java.util.Locale.US, position) + " with speed:" + "%8.2f".formatLocal(java.util.Locale.US, speed))
        context.children.foreach(_ ! VehicleMove(newPathState, position))
      } else {
        context.children.foreach(_ ! VehicleMove(pathState, position))
      }
      if (num == 1) {
        if (checkFinish()) {
          convoy ! VehicleDestinationReached
        } else {
          convoy ! VehicleDone
        }
      }
    }
  }

  log.debug("Vehicle::Create: " + num)
  if (num > 1) {
    context.actorOf(Props {
      new Vehicle(num - 1, convoy, path)
    }, "V" + (num - 1))
  } else {
    convoy ! VehicleDone
  }
}
