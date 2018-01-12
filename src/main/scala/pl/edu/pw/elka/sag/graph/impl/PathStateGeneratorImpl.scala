package pl.edu.pw.elka.sag.graph.impl

import pl.edu.pw.elka.sag.graph.{Path, PathGenerator, PathState, PathStateGenerator}

class PathStateGeneratorImpl extends PathStateGenerator {
  var iteration = 0
  val map = scala.collection.mutable.Map[Char,Boolean]()
  map.put('A',true)
  map.put('B',true)
  map.put('C',true)

  override def getPathState(path: Path): PathState = {
    iteration += 1
    if (iteration % 4 == 0) {
      map.put('A', !map.get('A').get)
    }
    if (iteration % 5 == 0) {
      map.put('B', !map.get('B').get)
    }
    if (iteration % 2 == 0) {
      map.put('C', !map.get('C').get)
    }
    return new PathState(iteration, map)
  }
}
