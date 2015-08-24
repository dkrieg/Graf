package graf.gremlin
package process.traversal.dsl.graph

import structure._

case class GrafVertexGraphTraversal[Start](private[graph] override val graphTraversal: GraphTraversal[Start, Vertex]) extends GrafElementGraphTraversal(graphTraversal) {
  def out: GrafVertexGraphTraversal[Start] =
    graphTraversal.out()

  def out(label: String, labels: String*): GrafVertexGraphTraversal[Start] =
    graphTraversal.out(label +: labels:_*)

  def outE: GrafEdgeGraphTraversal[Start] =
    graphTraversal.outE()

  def outE(label: String, labels: String*): GrafEdgeGraphTraversal[Start] =
    graphTraversal.outE(label +: labels:_*)

  def in: GrafVertexGraphTraversal[Start] =
    graphTraversal.in()

  def in(label: String, labels: String*): GrafVertexGraphTraversal[Start] =
    graphTraversal.in(label +: labels:_*)

  def inE: GrafEdgeGraphTraversal[Start] =
    graphTraversal.inE()

  def inE(label: String, labels: String*): GrafEdgeGraphTraversal[Start] =
    graphTraversal.inE(label +: labels:_*)

  def both: GrafVertexGraphTraversal[Start] =
    graphTraversal.both()

  def both(label: String, labels: String*): GrafVertexGraphTraversal[Start] =
    graphTraversal.both(label +: labels:_*)

  def bothE: GrafEdgeGraphTraversal[Start] =
    graphTraversal.bothE()

  def bothE(label: String, labels: String*): GrafEdgeGraphTraversal[Start] =
    graphTraversal.bothE(label +: labels:_*)
}
