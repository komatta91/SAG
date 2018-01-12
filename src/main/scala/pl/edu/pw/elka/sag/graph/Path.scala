package pl.edu.pw.elka.sag.graph

class Path(val len:Double, val crossings:Map[Double, Char]) {
  def finish(to:Double) : Boolean = {
    if (to >= len) {
      return true
    }
    return false
  }

  def enterCrossing(from: Double, to:Double) : Boolean = {
    return !crossings.filter( {case (key, value) => from <= key && key < to } ).isEmpty
  }

  def getCrossing(from: Double, to:Double) : Char = {
   return crossings.filter({case (key, value) => from <= key && key < to }).minBy({case (key, value) => key})._2
  }

  def getCrossingPoint(crossing:Char): Double = {
    return crossings.filter({case (key, value) => value == crossing }).minBy({case (key, value) => value})._1
  }
}
