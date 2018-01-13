package pl.edu.pw.elka.sag.graph

/**
  * Immutable World state element modelling Path with crossings traversed by the Vehicles
  * @param len - length of the Path
  * @param crossings - map of the crossings in format [position, name]
  * @param maxSpeed - max Vehicle traversal speed
  */
class Path(val len:Double, val crossings:Map[Double, Char], val maxSpeed:Double) {

  /**
    * Predicate for traversal completion
    * @param to - next position in Path
    * @return - true if Path traversal is completed, false otherwise
    */
  def finish(to:Double) : Boolean = {
    to >= len
  }

  /**
    * Predicate for crossing traversal
    * @param from - current position in Path
    * @param to - next position in Path
    * @return - true if crossing can be traversed, false otherwise
    */
  def enterCrossing(from: Double, to:Double) : Boolean = {
    !crossings.filter( {case (key, value) => from <= key && key < to } ).isEmpty
  }

  /**
    * Getter returning crossing on the currently traversed Path part
    * @param from - current position in Path
    * @param to - next position in Path
    * @return - crossing occurring in the Path between positions or null
    */
  def getCrossing(from: Double, to:Double) : Char = {
   crossings.filter({case (key, _) => from <= key && key < to }).minBy({case (key, _) => key})._2
  }

  /**
    * Getter returning crossing position on the Path
    * @param crossing - name of the crossing
    * @return - crossing position in the Path or null
    */
  def getCrossingPoint(crossing:Char): Double = {
    crossings.filter({case (_, value) => value == crossing }).minBy({case (_, value) => value})._1
  }
}
