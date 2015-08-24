package graf.gremlin
package process.traversal.dsl.graph

import structure._

case class GrafVertexGraphTraversal[S](private[graph] override val traversal: GraphTraversal[S, Vertex]) extends GrafElementGraphTraversal(traversal) {

}
