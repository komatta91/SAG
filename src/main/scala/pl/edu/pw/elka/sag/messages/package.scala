package pl.edu.pw.elka.sag

import pl.edu.pw.elka.sag.graph.{Path, PathState}

package object messages {
  case class BeginSimulation(val numVehicles:Int)

  case class ConvoyPrepare(val numVehicles:Int, val path:Path)
  case object ConvoyPrepared

  case class ConvoyMove(val path:PathState)
  case object ConvoyMoveFinish
  case object ConvoyDestinationReached

  case class VehicleMove(val path:PathState, val prevPosition:Double)
  case object VehicleDone
  case object VehicleDestinationReached
}
