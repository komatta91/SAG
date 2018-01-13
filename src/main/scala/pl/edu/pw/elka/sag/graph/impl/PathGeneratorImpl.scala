package pl.edu.pw.elka.sag.graph.impl

import pl.edu.pw.elka.sag.graph.{Path, PathGenerator}

/**
  * World generator element generating linear Path with intersections for the Vehicles to traverse
  */
class PathGeneratorImpl extends PathGenerator {
  def getRandomInt(min: Int, max: Int): Int = {
    val r = scala.util.Random
    return min + r.nextInt(max - min)
  }

  def getRandomDouble(min: Double, max: Double): Double = {
    val r = scala.util.Random
    return min + (max - min) * r.nextDouble()
  }

  override def getPath(): Path = {
    val length = getRandomInt(1000, 2000)
    val speed = getRandomDouble(25, 50)
    val posCross1 = getRandomDouble(0, length / 3)
    val posCross2 = getRandomDouble(posCross1 + speed * 2, length / 2)
    val posCross3 = getRandomDouble(posCross2 + speed * 2, length)
    new Path(length, Map[Double, Char](posCross1 -> 'A', posCross2 -> 'B', posCross3 -> 'C'), speed)
  }


}
