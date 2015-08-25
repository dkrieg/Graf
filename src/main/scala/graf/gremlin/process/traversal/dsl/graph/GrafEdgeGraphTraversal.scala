package graf.gremlin
package process.traversal.dsl.graph

case class GrafEdgeGraphTraversal[Start](private[graph] override val graphTraversal: GraphTraversal[Start, Edge]) extends GrafElementGraphTraversal(graphTraversal) {

  def inV: GrafVertexGraphTraversal[Start] = graphTraversal.inV()

  def outV: GrafVertexGraphTraversal[Start] = graphTraversal.outV()

  def bothV: GrafVertexGraphTraversal[Start] = graphTraversal.bothV()

  def otherV: GrafVertexGraphTraversal[Start] = graphTraversal.otherV()
}
