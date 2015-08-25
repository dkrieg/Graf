package graf.gremlin

import org.apache.commons.configuration.{ Configuration ⇒ JConfiguration }
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.{ GraphTraversalSource ⇒ JGraphTraversalSource }
import org.apache.tinkerpop.gremlin.structure.{
  Edge ⇒ JEdge,
  Element ⇒ JElement,
  Graph ⇒ JGraph,
  Property ⇒ JProperty,
  Transaction ⇒ JTransaction,
  Vertex ⇒ JVertex,
  VertexProperty ⇒ JVertexProperty,
  T ⇒ JT
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
  type Configuration = JConfiguration
  type GraphTraversalSource = JGraphTraversalSource
  type T = JT
}