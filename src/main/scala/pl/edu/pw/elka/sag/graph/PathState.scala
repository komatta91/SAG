package pl.edu.pw.elka.sag.graph

case class PathState(val iterNum:Int, crossings:scala.collection.mutable.Map[Char, Boolean]) {

  def isDriveThrough(crossing : Char): Boolean = {
    if (!crossings.get(crossing).isEmpty && crossings(crossing)) {
      return true
    }
    return false
  }
}
