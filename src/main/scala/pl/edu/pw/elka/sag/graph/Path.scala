package pl.edu.pw.elka.sag.graph

class Path(val len:Double, val crossings:Map[Double, Char], val maxSpeed:Double) {
  def finish(to:Double) : Boolean = {
    to >= len
  }

  def enterCrossing(from: Double, to:Double) : Boolean = {
    !crossings.filter( {case (key, value) => from <= key && key < to } ).isEmpty
  }

  def getCrossing(from: Double, to:Double) : Char = {
   crossings.filter({case (key, _) => from <= key && key < to }).minBy({case (key, _) => key})._2
  }

  def getCrossingPoint(crossing:Char): Double = {
    crossings.filter({case (_, value) => value == crossing }).minBy({case (_, value) => value})._1
  }
}
