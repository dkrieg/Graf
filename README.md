# Graf[T] [![Build Status](https://api.travis-ci.org/dkrieg/Graf.png?branch=master)](http://travis-ci.org/dkrieg/Graf)
While a [**Graf**](https://en.wikipedia.org/wiki/Graf) is the historical title of German nobility, 
**Graf**[T] is an attempt to `lift` TinkerPop3 into the noble ranks of `higher-kinded types` in Scala.

**Graf**[T] currently uses [Scalaz] (https://github.com/scalaz/scalaz) to extend the 
[FP](https://en.wikipedia.org/wiki/Functional_programming) semantics of TinkerPop3 for use with Scala. 
[TinkerPop3] (http://tinkerpop.incubator.apache.org/) provides graph computing capabilities for both graph databases 
(OLTP) and graph analytic systems (OLAP).

Technically, **Graf**[T] is an instance of Free Monad, uses a Natural Transformation for Reader semantics and Task 
memoization to create a referentially transparent API that given the same input produces the same output despite global 
state change that may have occurred in the graph.  This of course has benefits and drawback but referentially 
transparent functions are far easier to reason about. 

### Example Code
* [GrafApp.scala](https://github.com/dkrieg/Graf/blob/master/src/example/scala/com/graf/GrafApp.scala), 
[GrafApp2.scala](https://github.com/dkrieg/Graf/blob/master/src/example/scala/com/graf/GrafApp2.scala),
[GrafApp3.scala](https://github.com/dkrieg/Graf/blob/master/src/example/scala/com/graf/GrafApp3.scala) 
For closer examination
```scala
object GrafApp extends App {

  // create a script to modify and traverse a graph
  val script = Graf {
    for {
      // access the Graph
      g ← G

      // create some vertices
      marko =  g + (Person,   Name("marko"),  Age(29))
      vadas =  g + (Person,   Name("vadas"),  Age(27))
      lop =    g + (Software, Name("lop"),    Lang("java"))
      josh =   g + (Person,   Name("josh"),   Age(32))
      ripple = g + (Software, Name("ripple"), Lang("java"))
      peter =  g + (Person,   Name("peter"),  Age(35))

      // link vertices to edges
      _ = marko --- (Knows,   Weight(0.5d)) --> vadas
      _ = marko --- (Knows,   Weight(1.0d)) --> josh
      _ = marko --- (Created, Weight(0.4d)) --> lop
      _ = josh  --- (Created, Weight(1.0d)) --> ripple
      _ = josh  --- (Created, Weight(0.4d)) --> lop
      _ = peter --- (Created, Weight(0.2d)) --> lop

      // map over all edges to create a sorted list
      eq = g.edges.toList sortWith { (a, b) ⇒
        a.id[Long].compareTo(b.id[Long]) < 0
      }

      // yield the sorted list of Show[Edge] strings for all edges
    } yield eq map (_.shows)
  }
  // NOTE: nothing has happened - the world is unchanged!

  // open a Graph
  val graph = TinkerGraphFactory.open()

  // apply a Graph instance to the script to create an runnable Task
  val task = script(graph)
  println(graph)
  script(graph)
  script.bind(graph) // alternate syntax
  script.bind(graph) // bind the graph to the script gives you a new One Time Task - but does not alter the graph
  script.bind(graph)
  println(graph)
  // NOTE: we are ready to change the world but it remains unchanged!

  task.run
  println(graph)
  task.run
  task.run // The task is referentially transparent - it executes once and memoizes the results
  task.run
  println(graph)

  // print resulting list of strings to console
  task.run.foreach(println)

  // output the graph
  graph.io(GrafIO.GraphSON).writer.create().writeGraph(Console.out, graph)

  //  close the Graph
  graph.close()

  // NOTE: We Have Changed The World!
}
```
### References
* [TinkerPop3] (http://tinkerpop.incubator.apache.org/) provides graph computing capabilities for both graph databases (OLTP) and graph analytic systems (OLAP)
* [Scalaz] (https://github.com/scalaz/scalaz) is a Scala library for functional programming.
* [Gremlin Scala] (https://github.com/mpollmeier/gremlin-scala) "Scala wrapper for Apache TinkerPop 3 Graph DSL".
