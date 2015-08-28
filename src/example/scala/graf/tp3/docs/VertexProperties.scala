package graf.tp3.docs

import graf.gremlin._
import graf.gremlin.structure.util._

object VertexProperties extends App {
  def ==>(e: Any): Unit = println("==>" + e)

  val graph = TinkerGraphFactory.open()
  ==>(graph)

  val g = graph.traversal(grafBuilder)
  //  val g = graph.traversal()
  ==>(g)

  val v = g.addV("name", "marko", "name", "marko a. rodriguez").next()
  ==>(g)

  g.V(v).properties.count.foreach(==>)
  g.V(v).properties("name").count.foreach(==>)
  g.V(v).properties.foreach(==>)
  g.V(v).properties("name").foreach(==>)
  g.V(v).properties("name").hasValue("marko").foreach(==>)
  g.V(v).properties("name").hasValue("marko").property("acl", "private").foreach(==>)
  g.V(v).properties("name").hasValue("marko a. rodriguez").foreach(==>)
  g.V(v).properties("name").hasValue("marko a. rodriguez").property("acl", "public").foreach(==>)
  g.V(v).properties("name").has("acl", "public").value.foreach(==>)
  g.V(v).properties("name").has("acl", "public").drop.iterate()
  g.V(v).properties("name").has("acl", "public").value.foreach(==>)
  g.V(v).properties("name").has("acl", "private").value.foreach(==>)
  g.V(v).properties.foreach(==>)
  g.V(v).properties.properties.foreach(==>)
  g.V(v).properties.property("date",2014).foreach(==>)
  g.V(v).properties.property("creator","stephen").foreach(==>)
  g.V(v).properties.properties.foreach(==>)
  g.V(v).properties("name").valueMap.foreach(==>)
  g.V(v).property("name","okram").foreach(==>)
  g.V(v).values("name").foreach(==>)
}
