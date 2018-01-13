package pl.edu.pw.elka.sag

import akka.actor.{ActorSystem, Props}
import pl.edu.pw.elka.sag.messages.BeginSimulation
import pl.edu.pw.elka.sag.actors.World
import pl.edu.pw.elka.sag.graph.impl.{PathGeneratorImpl, PathStateGeneratorImpl}

object Main extends App {
  var system = ActorSystem("BaseSystem")
  var actor = system.actorOf(Props(new World(new PathGeneratorImpl(), new PathStateGeneratorImpl())), "World")
  actor ! BeginSimulation(5)
}
