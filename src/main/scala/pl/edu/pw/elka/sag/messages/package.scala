package pl.edu.pw.elka.sag

import pl.edu.pw.elka.sag.graph.{Path, PathState}

/**
  * Package containing all messages exchanged between agents
  */
package object messages {

  /**
    * Message initiating the Simulation of Path traversal
    * @param numVehicles - amount of simulated Vehicles
    */
  case class BeginSimulation(val numVehicles:Int)



  /**
    * Message initializing the Vehicles
    * @param numVehicles - amount of simulated Vehicles
    * @param path - initial World state
    */
  case class ConvoyPrepare(val numVehicles:Int, val path:Path)

  /**
    * Message sent upon completion of Vehicle initialization
    */
  case object ConvoyPrepared


  /**
    * Message initiating all Vehicles traversal in a simulation step
    * @param path - current World state
    */
  case class ConvoyMove(val path:PathState)

  /**
    * Message sent upon completion of all Vehicles traversal in a simulation step
    */
  case object ConvoyMoveFinish

  /**
    * Message sent upon completion of convoy Path traversal
    */
  case object ConvoyDestinationReached


  /**
    * Message initiating single Vehicle traversal in a simulation step
    * @param path - current World state
    * @param prevPosition - last Vehicle position
    */
  case class VehicleMove(val path:PathState, val prevPosition:Double)

  /**
    * Message sent upon completion of given Vehicle traversal in a simulation step
    */
  case object VehicleDone

  /**
    * Message sent upon completion of given Vehicle Path traversal
    */
  case object VehicleDestinationReached
}
