package graf.gremlin
package process.traversal.dsl.graph

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.structure.{ Direction, Vertex }

class GrafVertexTraversal[S](private[graph] override val traversal: GraphTraversal[S, Vertex]) extends GrafElementTraversal(traversal) {

  def out: GrafVertexTraversal[S] =
    traversal.out()

  def out(label: String, labels: String*): GrafVertexTraversal[S] =
    traversal.out(label +: labels: _*)

  def outE: GrafEdgeTraversal[S] =
    traversal.outE()

  def outE(label: String, labels: String*): GrafEdgeTraversal[S] =
    traversal.outE(label +: labels: _*)

  def in: GrafVertexTraversal[S] =
    traversal.in()

  def in(label: String, labels: String*): GrafVertexTraversal[S] =
    traversal.in(label +: labels: _*)

  def inE: GrafEdgeTraversal[S] =
    traversal.inE()

  def inE(label: String, labels: String*): GrafEdgeTraversal[S] =
    traversal.inE(label +: labels: _*)

  def both: GrafVertexTraversal[S] =
    traversal.both()

  def both(label: String, labels: String*): GrafVertexTraversal[S] =
    traversal.both(label +: labels: _*)

  def bothE: GrafEdgeTraversal[S] =
    traversal.bothE()

  def bothE(label: String, labels: String*): GrafEdgeTraversal[S] =
    traversal.bothE(label +: labels: _*)

  def toE(direction: Direction, edgeLabels: String*): GrafEdgeTraversal[S] =
    traversal.toE(direction, edgeLabels: _*)

  def to(direction: Direction, edgeLabels: String*): GrafVertexTraversal[S] =
    traversal.to(direction, edgeLabels: _*)
}
