package graf.gremlin
package process.traversal.dsl.graph

import graf.gremlin.structure.convert.wrapAll._
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer
import org.apache.tinkerpop.gremlin.process.traversal.TraversalStrategy
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource.GraphTraversalSourceStub
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.{ GraphTraversal, GraphTraversalSource }
import org.apache.tinkerpop.gremlin.structure._

case class GrafGraphTraversalSource private[graph] (private val source: GraphTraversalSource, private val builder: GrafTraversalSourceBuilder) extends TraversalSource {

  def addV(keyValues: AnyRef*): GraphTraversal[Vertex, Vertex] = source.addV(keyValues)

  def E: GrafEdgeGraphTraversal[Edge] = source.E()

  def E(edgesId: AnyRef, edgesIds: AnyRef*): GrafEdgeGraphTraversal[Edge] = source.E(edgesId +: edgesIds: _*)

  def tx: Transaction = source.tx()

  def V: GrafVertexGraphTraversal[Vertex] = source.V()

  def V(vertexId: AnyRef, vertexIds: AnyRef*): GrafVertexGraphTraversal[Vertex] = source.V(vertexId +: vertexIds: _*)

  def withPath[S](): GraphTraversalSourceStub = source.withPath[S]()

  def withSack[A](initialValue: A): GraphTraversalSourceStub = source.withSack(initialValue)

  def withSack[A](initialValue: A, splitOperator: A ⇒ A): GraphTraversalSourceStub = source.withSack(initialValue, splitOperator)

  def withSack[A](initialValue: ⇒ A): GraphTraversalSourceStub = source.withSack(initialValue)

  def withSack[A](initialValue: ⇒ A, splitOperator: A ⇒ A): GraphTraversalSourceStub = source.withSack(initialValue, splitOperator)

  def withSideEffect(key: String, supplier: ⇒ Any): GraphTraversalSourceStub = source.withSideEffect(key, supplier)

  def asBuilder(): GrafTraversalSourceBuilder = builder

  def getGraph: Optional[Graph] = source.getGraph

  def getGraphComputer: Optional[GraphComputer] = source.getGraphComputer

  def getStrategies: JList[TraversalStrategy[_]] = source.getStrategies

  override def toString: String = source.toString

}
