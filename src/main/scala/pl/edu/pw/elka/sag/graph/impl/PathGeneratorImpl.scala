package pl.edu.pw.elka.sag.graph.impl

import pl.edu.pw.elka.sag.graph.{Path, PathGenerator}

class PathGeneratorImpl extends PathGenerator{
  override def getPath(): Path = {
    new Path(1000, Map[Double,Char](100.0 -> 'A', 200.0 -> 'B', 500.0 -> 'C'))
  }
}
