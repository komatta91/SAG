package pl.edu.pw.elka.sag.graph

/**
  * World generator element trait
  */
trait  PathGenerator {

  /**
    * Method generating new World
    * @return - Path representing the World starting state
    */
  def getPath() : Path
}
