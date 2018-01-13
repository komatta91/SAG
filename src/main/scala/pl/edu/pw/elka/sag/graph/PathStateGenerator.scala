package pl.edu.pw.elka.sag.graph

/**
  * World state element trait
  */
trait  PathStateGenerator {

  /**
    * Method providing current state of the World
    * @param path - last known World state
    * @return - new World state
    */
  def getPathState(path:Path) : PathState
}
