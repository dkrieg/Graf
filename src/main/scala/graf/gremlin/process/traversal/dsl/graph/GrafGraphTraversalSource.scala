package graf.gremlin
package process.traversal.dsl.graph

import graf.gremlin.structure._

case class GrafGraphTraversalSource(private[graph] val gt: GraphTraversalSource) {

  def getGraph: Option[GrafGraph] = gt.getGraph.asScala map toGrafGraph

  def E: GrafEdgeGraphTraversal[GrafEdge] = gt.E()

  def E(edgesIds: Seq[AnyRef]): GrafEdgeGraphTraversal[GrafEdge] = gt.E(edgesIds)

  def V: GrafVertexGraphTraversal[GrafVertex] = gt.V()

  def V(vertexIds: Seq[AnyRef]): GrafVertexGraphTraversal[GrafVertex] = gt.V.asAdmin()

  def tx(): GrafTransaction = gt.tx()

//  def asBuilder(): Builder = gt.asBuilder()

//  def addV(keyValues: AnyRef*): GraphTraversal[Vertex, Vertex] = gt.addV(keyValues)

//  def withSack[A](initialValue: A, splitOperator: UnaryOperator[A]): GraphTraversalSourceStub = gt.withSack(initialValue, splitOperator)

//  def getGraphComputer: Option[GraphComputer] = gt.getGraphComputer.asScala

//  def getStrategies: List[TraversalStrategy[_]] = gt.getStrategies.toList

//  def withSack[A](initialValue: A): GraphTraversalSourceStub = gt.withSack(initialValue)

//  def withPath[S](): GraphTraversalSourceStub = gt.withPath[S]()

//  def withSideEffect(key: String, `object`: scala.Any): GraphTraversalSourceStub = gt.withSideEffect(key, `object`)
//
//  def withSideEffect(key: String, supplier: Supplier[_]): GraphTraversalSourceStub = gt.withSideEffect(key, supplier)
//
//  def withSack[A](initialValue: Supplier[A], splitOperator: UnaryOperator[A]): GraphTraversalSourceStub = gt.withSack(initialValue, splitOperator)
//
//  def withSack[A](initialValue: Supplier[A]): GraphTraversalSourceStub = gt.withSack(initialValue)
}
