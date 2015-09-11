package graf

import graf.gremlin.process.traversal.dsl.graph.{ GrafGraphTraversalSource, GrafTraversalSourceBuilder }
import org.apache.tinkerpop.gremlin.process.traversal.TraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph

package object gremlin {
  val GRAPH = Graph.GRAPH

  def grafStandard: TraversalSource.Builder[GrafGraphTraversalSource] = new GrafTraversalSourceBuilder

}
