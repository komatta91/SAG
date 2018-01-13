package pl.edu.pw.elka.sag.graph

/**
  * World state element modelling Path wth crossings traversed by the Vehicles at a given simulation step
  * @param iterNum - incremental number of the simulation step
  * @param crossings - state of the crossings in Path
  */
case class PathState(val iterNum:Int, crossings:scala.collection.mutable.Map[Char, Boolean]) {

  /**
    * Predicate for crossing traversal possibility
    * @param crossing - crossing name
    * @return - true if crossing can be traversed at the current point in time/simulation, false otherwise
    */
  def isDriveThrough(crossing : Char): Boolean = {
    (!crossings.get(crossing).isEmpty && crossings(crossing))
  }
}
