package graf.gremlin
package process.traversal.dsl.graph

import graf.gremlin.structure._

case class GrafEdgeGraphTraversal[S](private[graph] override val traversal: GraphTraversal[S, Edge]) extends GrafElementGraphTraversal(traversal) {


}
