package graf

import java.{ lang, util }

import graf.gremlin.process.traversal.dsl.graph.{
  GrafGraphTraversalSource,
  GrafTraversalSourceBuilder ⇒ JGrafTraversalSourceBuilder
}
import org.apache.commons.configuration.{
  Configuration ⇒ JConfiguration
}
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.{
  GraphTraversal ⇒ JGraphTraversal
}
import org.apache.tinkerpop.gremlin.process.traversal.step.util.{
  BulkSet ⇒ JBulkSet,
  Tree ⇒ JTree
}
import org.apache.tinkerpop.gremlin.process.traversal.{
  Order ⇒ JOrder,
  P ⇒ JP,
  Pop ⇒ JPop,
  Scope ⇒ JScope,
  Traversal ⇒ JTraversal,
  TraversalSource ⇒ JTraversalSource
}
import org.apache.tinkerpop.gremlin.structure.{
  Direction ⇒ JDirection,
  Edge ⇒ JEdge,
  Element ⇒ JElement,
  Graph ⇒ JGraph,
  Property ⇒ JProperty,
  T ⇒ JT,
  Transaction ⇒ JTransaction,
  Vertex ⇒ JVertex,
  VertexProperty ⇒ JVertexProperty
}
import org.apache.tinkerpop.gremlin.process.traversal.{Traverser => JTraverser}
import java.util.stream
import org.apache.tinkerpop.gremlin.process.traversal.{Path => JPath}
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.{GraphTraversalSource => JGraphTraversalSource}
import org.apache.tinkerpop.gremlin.process.traversal.{Traversal => JTraversal}
import org.apache.tinkerpop.gremlin.process.traversal.{TraversalSideEffects => JTraversalSideEffects}

package object gremlin {
  type Edge = JEdge
  type Element = JElement
  type Graph = JGraph
  type Property[A] = JProperty[A]
  type T = JT
  type Transaction = JTransaction
  type Vertex = JVertex
  type VertexProperty[A] = JVertexProperty[A]
  type P[A] = JP[A]
  type Direction = JDirection
  type Cardinality = JVertexProperty.Cardinality

  type TraversalSource = JTraversalSource
  type Traversal[Start, End] = JTraversal[Start, End]
  type TraversalAdmin[Start, End] = JTraversal.Admin[Start, End]
  type GraphTraversal[Start, End] = JGraphTraversal[Start, End]
  type Scope = JScope
  type Pop = JPop
  type Order = JOrder
  type Traverser[A] = JTraverser[A]
  type Path = JPath
  type GraphTraversalSource = JGraphTraversalSource
  type TraversalSideEffects = JTraversalSideEffects

  type BulkSet[A] = JBulkSet[A]
  type Tree[A] = JTree[A]

  type Configuration = JConfiguration
  type Optional[A] = util.Optional[A]
  type JMap[A, B] = util.Map[A, B]
  type JList[A] = util.List[A]
  type JStream[A] = stream.Stream[A]

  type JLong = lang.Long
  type JDouble = lang.Double

  val GRAPH = JGraph.GRAPH

  def grafBuilder: JTraversalSource.Builder[GrafGraphTraversalSource] = new JGrafTraversalSourceBuilder

}
