package pl.edu.pw.elka.sag.graph

case class PathState(val iterNum:Int, crossings:scala.collection.mutable.Map[Char, Boolean]) {

  def isDriveThrough(crossing : Char): Boolean = {
    (!crossings.get(crossing).isEmpty && crossings(crossing))
  }
}
