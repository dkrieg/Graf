package graf.gremlin
package process.traversal.dsl.graph

import graf.gremlin.structure.convert.wrapAll._
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource.GraphTraversalSourceStub
import org.apache.tinkerpop.gremlin.structure.{Edge, Vertex}

case class GrafGraphTraversalSourceStub(private[graph] val stub: GraphTraversalSourceStub) {

  def addV: GrafGraphTraversal[Vertex, Vertex] = stub.addV()

  def addV(kv: (Any, AnyRef)*): GrafGraphTraversal[Vertex, Vertex] =
    stub.addV(kv.flatMap(p ⇒ Seq(p._1, p._2)).map(_.asInstanceOf[Object]): _*)

  def E: GrafEdgeTraversal[Edge] = stub.E()

  def E(edgesId: Any, edgesIds: Any*): GrafEdgeTraversal[Edge] =
    stub.E((edgesId +: edgesIds).map(_.asInstanceOf[Object]): _*)

  def V: GrafVertexTraversal[Vertex] = stub.V()

  def V(vertexId: Any, vertexIds: Any*): GrafVertexTraversal[Vertex] =
    stub.V((vertexId +: vertexIds).map(_.asInstanceOf[Object]): _*)

  def withPath: GrafGraphTraversalSourceStub = {
    stub.withPath()
    this
  }

  def withSack[A](initialValue: A): GrafGraphTraversalSourceStub = {
    stub.withSack(initialValue)
    this
  }

  def withSack[A](initialValue: A, splitOperator: A => A): GrafGraphTraversalSourceStub = {
    stub.withSack(initialValue, splitOperator)
    this
  }

  def withSack[A](initialValue: => A): GrafGraphTraversalSourceStub = {
    stub.withSack(initialValue)
    this
  }

  def withSack[A](initialValue: ⇒ A, splitOperator: A ⇒ A): GrafGraphTraversalSourceStub = {
    stub.withSack(initialValue, splitOperator)
    this
  }

  def withSideEffect(key: String, supplier: ⇒ Any): GrafGraphTraversalSourceStub = {
    stub.withSideEffect(key, supplier)
    this
  }
}
