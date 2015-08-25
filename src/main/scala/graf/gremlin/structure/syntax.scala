package graf.gremlin.structure

import graf.gremlin.structure.schema.{ KeyValue, Atom, Label }
import org.apache.tinkerpop.gremlin.process.traversal.P
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal

object syntax {

  implicit class GraphWithPlus(g: Graph) {

    def +(label: String): Vertex = g.addVertex(label)

    def +(label: Label, atoms: Atom*): Vertex = g.addVertex(
      (label +: atoms).flatMap { atom ⇒ Seq(atom.key, atom.value) }: _*)
  }

  implicit class VertexWithArrows(v: Vertex) {

    def <--(se: SemiEdge) = se.from.addEdge(se.label.value.asInstanceOf[String], v, se.atoms: _*)

    def <--(de: SemiDoubleEdge): (Edge, Edge) =
      v.addEdge(
        de.label.value.asInstanceOf[String], de.right, de.atoms.flatMap { atom ⇒ Seq(atom.key, atom.value) }: _*) ->
        de.right.addEdge(de.label.value.asInstanceOf[String], v, de.atoms.flatMap { atom ⇒ Seq(atom.key, atom.value) }: _*)

    def ---(label: String) = SemiEdge(Label(label), v)

    def ---(label: Label, atoms: Atom*) = SemiEdge(label, v, atoms: _*)
  }

  implicit class SemiEdgeFunctionsWithString(label: String) {
    def ---(from: Vertex) = SemiEdge(Label(label), from)

    def -->(right: Vertex) = SemiDoubleEdge(Label(label), right)
  }

  implicit class SemiEdgeFunctionsWithLabel(label: Label) {
    def ---(from: Vertex) = SemiEdge(label, from)

    def -->(right: Vertex) = SemiDoubleEdge(label, right)
  }

  implicit class SemiEdgeProductFunctionsWithProduct[A <: Product](t: A) {
    private val elements = t.productIterator.toList
    require(elements.exists(a ⇒ classOf[Label].isAssignableFrom(a.getClass)), "Semi Edge Product must contain a Label")
    require(elements.forall(a ⇒ classOf[Atom].isAssignableFrom(a.getClass)), "Semi Edge Product must contain only Atom instances")
    private lazy val atoms = elements.filterNot(a ⇒ classOf[Label].isAssignableFrom(a.getClass)).asInstanceOf[List[Atom]]
    private lazy val label = elements.filter(a ⇒ classOf[Label].isAssignableFrom(a.getClass)).head.asInstanceOf[Label]

    def ---(from: Vertex) = SemiEdge(label, from, atoms: _*)

    def -->(right: Vertex) = SemiDoubleEdge(label, right, atoms: _*)
  }

  case class SemiEdge(label: Label, from: Vertex, atoms: Atom*) {
    def -->(to: Vertex) = from.addEdge(label.value.asInstanceOf[String], to, atoms.flatMap { atom ⇒ Seq(atom.key, atom.value) }: _*)
  }

  case class SemiDoubleEdge(label: Label, right: Vertex, atoms: Atom*)

  implicit class GraphTraversalSourcesOps(t: GraphTraversalSource) {
    def V: GraphTraversal[Vertex, Vertex] = t.V()

    def E: GraphTraversal[Edge, Edge] = t.E()
  }

  implicit class GraphTraversalOps[S, E](t: GraphTraversal[S, E]) {
    def hasKeyValue(k: KeyValue): GraphTraversal[S, E] = t.has(k.key.asInstanceOf[String], P.eq(k.value))
  }
}
