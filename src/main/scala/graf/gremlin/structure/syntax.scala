package graf.gremlin.structure

import graf.gremlin.structure.schema.{Atom, Label}

object syntax {

  implicit class GrafGraphWithPlus(g: GrafGraph) {

    def +(label: String): GrafVertex = g.addVertex(label)

    def +(label: Label, atoms: Atom*): GrafVertex = g.addVertex(label, atoms: _*)
  }

  implicit class GrafVertexWithArrows(v: GrafVertex) {

    def <--(se: SemiEdge) = se.from.addEdge(se.label, v, se.atoms:_*)

    def <--(de: SemiDoubleEdge): (GrafEdge, GrafEdge) =
      v.addEdge(de.label, de.right, de.atoms:_*) -> de.right.addEdge(de.label, v, de.atoms:_*)

    def ---(label: String) = SemiEdge(Label(label), v)

    def ---(label: Label, atoms: Atom*) = SemiEdge(label, v, atoms:_*)
  }

  implicit class SemiEdgeFunctionsWithString(label: String) {
    def ---(from: GrafVertex) = SemiEdge(Label(label), from)

    def -->(right: GrafVertex) = SemiDoubleEdge(Label(label), right)
  }

  implicit class SemiEdgeFunctionsWithLabel(label: Label) {
    def ---(from: GrafVertex) = SemiEdge(label, from)

    def -->(right: GrafVertex) = SemiDoubleEdge(label, right)
  }

  implicit class SemiEdgeProductFunctionsWithProduct[A <: Product](t: A) {
    private val elements = t.productIterator.toList
    require(elements.exists(a => classOf[Label].isAssignableFrom(a.getClass)), "Semi Edge Product must contain a Label")
    require(elements.forall(a => classOf[Atom].isAssignableFrom(a.getClass)), "Semi Edge Product must contain only Atom instances")
    private lazy val atoms = elements.filterNot(a => classOf[Label].isAssignableFrom(a.getClass)).asInstanceOf[List[Atom]]
    private lazy val label = elements.filter(a => classOf[Label].isAssignableFrom(a.getClass)).head.asInstanceOf[Label]

    def ---(from: GrafVertex) = SemiEdge(label, from, atoms: _*)

    def -->(right: GrafVertex) = SemiDoubleEdge(label, right, atoms: _*)
  }

  case class SemiEdge(label: Label, from: GrafVertex, atoms: Atom*) {
    def -->(to: GrafVertex) = from.addEdge(label, to, atoms: _*)
  }

  case class SemiDoubleEdge(label: Label, right: GrafVertex, atoms: Atom*)
}
