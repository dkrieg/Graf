package graf.gremlin
package process.traversal.dsl.graph

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.structure.{Direction, Edge}

class GrafEdgeTraversal[S](private[graph] override val traversal: GraphTraversal[S, Edge]) extends GrafGraphTraversal(traversal) {

  def inV: GrafVertexTraversal[S] = traversal.inV()

  def outV: GrafVertexTraversal[S] = traversal.outV()

  def bothV: GrafVertexTraversal[S] = traversal.bothV()

  def otherV: GrafVertexTraversal[S] = traversal.otherV()

  def toV(direction: Direction): GrafVertexTraversal[S] = traversal.toV(direction)
}
