package graf.gremlin

import graf.gremlin.structure.schema.{ Label, Atom }
import org.apache.commons.configuration.{ Configuration ⇒ JConfiguration }
import org.apache.tinkerpop.gremlin.structure.Graph.{
  Variables ⇒ JVariables,
  Features ⇒ JFeatures
}
import JFeatures.{
  DataTypeFeatures ⇒ JDataTypeFeatures,
  EdgeFeatures ⇒ JEdgeFeatures,
  EdgePropertyFeatures ⇒ JEdgePropertyFeatures,
  ElementFeatures ⇒ JElementFeatures,
  FeatureSet ⇒ JFeatureSet,
  GraphFeatures ⇒ JGraphFeatures,
  PropertyFeatures ⇒ JPropertyFeatures,
  VariableFeatures ⇒ JVariableFeatures,
  VertexFeatures ⇒ JVertexFeatures,
  VertexPropertyFeatures ⇒ JVertexPropertyFeatures
}
import org.apache.tinkerpop.gremlin.structure.VertexProperty.{ Cardinality ⇒ JCardinality }
import org.apache.tinkerpop.gremlin.structure.{
  Edge ⇒ JEdge,
  Element ⇒ JElement,
  Graph ⇒ JGraph,
  Property ⇒ JProperty,
  Transaction ⇒ JTransaction,
  Vertex ⇒ JVertex,
  VertexProperty ⇒ JVertexProperty,
  Direction ⇒ JDirection
}
import scala.language.implicitConversions
import scala.reflect.runtime.universe._

package object structure {
  type Edge = JEdge
  type Element = JElement
  type Graph = JGraph
  type Property[A] = JProperty[A]
  type Transaction = JTransaction
  type Vertex = JVertex
  type VertexProperty[A] = JVertexProperty[A]
  type Direction = Direction.Direction
  type Cardinality = Cardinality.Cardinality
  type Variables = JVariables
  type Features = JFeatures
  type DataTypeFeatures = JDataTypeFeatures
  type EdgeFeatures = JEdgeFeatures
  type EdgePropertyFeatures = JEdgePropertyFeatures
  type ElementFeatures = JElementFeatures
  type FeatureSet = JFeatureSet
  type GraphFeatures = JGraphFeatures
  type PropertyFeatures = JPropertyFeatures
  type VariableFeatures = JVariableFeatures
  type VertexFeatures = JVertexFeatures
  type VertexPropertyFeatures = JVertexPropertyFeatures
  type Configuration = JConfiguration

  object Direction extends Enumeration {
    type Direction = Value
    val OUT, IN, BOTH = Value

    def opposite(current: Direction): Direction = current match {
      case IN ⇒ OUT
      case OUT ⇒ IN
      case BOTH ⇒ BOTH
    }
  }
  implicit class JDirectionAsScala(current: JDirection) {
    def asScala: Direction = {
      import JDirection._
      current match {
        case `IN` ⇒ Direction.IN
        case `OUT` ⇒ Direction.OUT
        case `BOTH` ⇒ Direction.BOTH
      }
    }
  }
  implicit class DirectionAsJava(current: Direction) {
    def asJava: JDirection = {
      import Direction._
      current match {
        case `IN` ⇒ JDirection.IN
        case `OUT` ⇒ JDirection.OUT
        case `BOTH` ⇒ JDirection.BOTH
      }
    }
  }

  object Cardinality extends Enumeration {
    type Cardinality = Value
    val single, list, set = Value
  }
  implicit class JCardinalityAsScala(c: JCardinality) {
    def asScala: Cardinality = {
      import JCardinality._
      c match {
        case `single` ⇒ Cardinality.single
        case `list` ⇒ Cardinality.list
        case `set` ⇒ Cardinality.set
      }
    }
  }
  implicit class CardinalityAsJava(c: Cardinality) {
    def asJava: JCardinality = {
      import Cardinality._
      c match {
        case `single` ⇒ JCardinality.single
        case `list` ⇒ JCardinality.list
        case `set` ⇒ JCardinality.set
      }
    }
  }

  implicit class GraphAsScala(g: Graph) {
    def asScala = GrafGraph(g)
  }
  implicit class GrafGraphAsJava(gg: GrafGraph) {
    def asJava: Graph = gg.graph
  }

  implicit class ElementAsScala[A](e: Element) {
    def asScala = e match {
      case v: Vertex ⇒ GrafVertex(v)
      case e: Edge ⇒ GrafEdge(e)
      case p: VertexProperty[A] ⇒ GrafVertexProperty(p)
    }
  }
  implicit class GrafElementAsJava[E <: Element](ge: GrafElement[E]) {
    def asJava = ge.e
  }

  implicit class PropertyAsScala[A](p: Property[A]) {
    def asScala = GrafProperty(p)
  }
  implicit class GrafPropertyAsJava[A](p: GrafProperty[A]) {
    def asJava = p.property
  }
  implicit class IteratorPropertyAsScala[A](it: Iterator[Property[A]]) {
    def asScala: Iterator[GrafProperty[A]] = it map GrafProperty.apply
  }

  implicit class VertexAsScala(v: Vertex) {
    def asScala = GrafVertex(v)
  }
  implicit class GrafVertexAsJava(v: GrafVertex) {
    def asJava = v.e
  }
  implicit class IteratorVertexAsScala(it: Iterator[Vertex]) {
    def asScala = it map GrafVertex.apply
  }
  implicit class IteratorGrafVertexAsJava(it: Iterator[GrafVertex]) {
    def asJava = it map (_.asJava)
  }

  implicit class VertexPropertyAsScala[A](v: VertexProperty[A]) {
    def asScala = GrafVertexProperty(v)
  }
  implicit class GrafVertexPropertyAsJava[A](v: GrafVertexProperty[A]) {
    def asJava = v.e
  }
  implicit class IteratorVertexPropertyAsScala[A](it: Iterator[VertexProperty[A]]) {
    def asScala = it map GrafVertexProperty.apply
  }

  implicit class EdgeAsScala(e: Edge) {
    def asScala = GrafEdge(e)
  }
  implicit class GrafEdgeAsJava(ge: GrafEdge) {
    def asJava = ge.e
  }
  implicit class IteratorEdgeAsScala(it: Iterator[Edge]) {
    def asScala = it map GrafEdge.apply
  }
  implicit class IteratorGrafEdgeAsJava(it: Iterator[GrafEdge]) {
    def asJava = it map (_.asJava)
  }

  implicit class VariablesAsScala(v: Variables) {
    def asScala = GrafVariables(v)
  }

  implicit class FeaturesAsScala(f: Features) {
    def asScala = GrafFeatures(f)
  }

  implicit class TransactionAsScala(tx: Transaction) {
    def asScala = GrafTransaction(tx)
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