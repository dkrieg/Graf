# Graf[T]
While a [**Graf**](https://en.wikipedia.org/wiki/Graf) is the historical title of German nobility, 
**Graf**[T] is an attempt to `lift` TinkerPop3 into the noble ranks of `higher-kinded types` in Scala.

**Graf**[T] uses Scalaz to create an [FP](https://en.wikipedia.org/wiki/Functional_programming) solution to 
interfacing with TinkerPop3 in Scala. [TinkerPop3] (http://tinkerpop.incubator.apache.org/) provides graph computing 
capabilities for both graph databases (OLTP) and graph analytic systems (OLAP).  Technically, **Graf**[T]
is an instance of Kleisli and Free Monad and can be used to express any code that interacts 
with the Graph in a monadic style.  [Gremlin Scala](https://github.com/mpollmeier/gremlin-scala) wraps the TinkerPop3
API and introducing more type-safe functions. 

### Example Code
* [GrafApp.scala](https://github.com/dkrieg/Graf/blob/master/src/example/scala/com/graf/GrafApp.scala), 
[GrafApp2.scala](https://github.com/dkrieg/Graf/blob/master/src/example/scala/com/graf/GrafApp2.scala),
[GrafApp3.scala](https://github.com/dkrieg/Graf/blob/master/src/example/scala/com/graf/GrafApp3.scala) For closer examination
```scala
object GrafApp extends App {

  // declare some values
  implicit class EdgeOps(e: ScalaEdge) {
    def weight(w: Double) = e.setProperty("weight", w)
  }

  def age(a: Int) = "age" -> a

  def lang(n: String) = "lang" -> n

  // create a script to modify and traverse a graph
  val script: Graf[Task[List[String]]] = Graf {
    for {
      // access the Graph
      g ← G

      // create some vertices
      marko = g ++ ("person", "marko", age(29))
      vadas = g ++ ("person", "vadas", age(27))
      lop = g ++ ("software", "lop", lang("java"))
      josh = g ++ ("person", "josh", age(32))
      ripple = g ++ ("software", "ripple", lang("java"))
      peter = g ++ ("person", "peter", age(35))

      // link vertices to edges
      _ = (marko -- "knows" -> vadas).weight(0.5d)
      _ = (marko -- "knows" -> josh).weight(1.0d)
      _ = (marko -- "created" -> lop).weight(0.4d)
      _ = (josh -- "created" -> ripple).weight(1.0d)
      _ = (josh -- "created" -> lop).weight(0.4d)
      _ = (peter -- "created" -> lop).weight(0.2d)

      // map over all edges to create a sorted list
      eq = g.E.toList() sortWith { (a, b) ⇒
        a.id.asInstanceOf[Long].compareTo(b.id.asInstanceOf[Long]) < 0
      }

      // yield the sorted list of Show[Edge] strings for all edges
    } yield eq map (_.shows)
  }
  // NOTE: nothing has happened - the world is unchanged!

  // open a Graph
  val graph = TinkerGraph.open

  // apply a Graph instance to the script to create an runnable Task
  val task = script(graph)
  // NOTE: we are ready to change the world but it remains unchanged!

  // run the Task
  val result = task.run

  // print resulting list of strings to console
  result.foreach(println)

  // output the graph
  graph.io(graphson()).writer.create.writeGraph(Console.out, graph)

  //  close the Graph
  graph.close()

  // NOTE: We Have Changed The World!
}
```
### References
* [TinkerPop3] (http://tinkerpop.incubator.apache.org/) provides graph computing capabilities for both graph databases (OLTP) and graph analytic systems (OLAP)
* [Scalaz] (https://github.com/scalaz/scalaz) is a Scala library for functional programming.
* [Gremlin Scala] (https://github.com/mpollmeier/gremlin-scala) "Scala wrapper for Apache TinkerPop 3 Graph DSL".
