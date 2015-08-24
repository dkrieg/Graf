package graf.gremlin

import java.util.{ Iterator ⇒ JIterator }

import scala.collection.JavaConversions._
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
  Direction ⇒ JDirection,
  T => JT
}
import scala.language.implicitConversions

package object structure {
  type Edge = JEdge
  type Element = JElement
  type Graph = JGraph
  type Property[A] = JProperty[A]
  type Transaction = JTransaction
  type Vertex = JVertex
  type VertexProperty[A] = JVertexProperty[A]
  type Direction = JDirection
  type Cardinality = JCardinality
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
  type T = JT

  implicit def toGrafGraph(g: Graph): GrafGraph = GrafGraph(g)
  implicit def toGraph(g: GrafGraph): Graph = g.graph

  implicit class ElementAsScala[A](e: Element) {
    def asScala = e match {
      case v: Vertex ⇒ GrafVertex(v)
      case e: Edge ⇒ GrafEdge(e)
      case p: VertexProperty[A] ⇒ GrafVertexProperty(p)
    }
  }

  implicit def toGrafProperty[A](p: Property[A]): GrafProperty[A] = GrafProperty(p)
  implicit def toProperty[A](p: GrafProperty[A]): Property[A] = p.property

  implicit def toGrafVertex(v: Vertex): GrafVertex = GrafVertex(v)
  implicit def toVertex(v: GrafVertex): Vertex = v.e

  implicit def toGrafVertexProperty[A](p: VertexProperty[A]): GrafVertexProperty[A] = GrafVertexProperty(p)
  implicit def toVertexProperty[A](p: GrafVertexProperty[A]): VertexProperty[A] = p.e

  implicit def toGrafEdge(e: Edge): GrafEdge = GrafEdge(e)
  implicit def toEdge(e: GrafEdge): Edge = e.e

  implicit def toGrafVariables(v: Variables): GrafVariables = GrafVariables(v)
  implicit def toGrafFeatures(f: Features): GrafFeatures = GrafFeatures(f)
  implicit def toGrafTransaction(tx: Transaction): GrafTransaction = GrafTransaction(tx)

  implicit def toIteratorGrafVertex(it: Iterator[Vertex]): Iterator[GrafVertex] = it map toGrafVertex
  implicit def toIteratorGrafEdge(it: Iterator[Edge]): Iterator[GrafEdge] = it map toGrafEdge
  implicit def toIteratorGrafProperty[A](it: Iterator[Property[A]]): Iterator[GrafProperty[A]] = it map toGrafProperty
  implicit def toIteratorGrafVertexProperty[A](it: Iterator[VertexProperty[A]]): Iterator[GrafVertexProperty[A]] = it map toGrafVertexProperty

  implicit def toIteratorVertex(it: Iterator[GrafVertex]): JIterator[Vertex] = it map toVertex
  implicit def toIteratorEdge(it: Iterator[GrafEdge]): JIterator[Edge] = it map toEdge
  implicit def toIteratorProperty[A](it: Iterator[GrafProperty[A]]): JIterator[Property[A]] = it map toProperty
  implicit def toIteratorVertexProperty[A](it: Iterator[GrafVertexProperty[A]]): JIterator[VertexProperty[A]] = it map toVertexProperty
}