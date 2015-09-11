package graf.gremlin
package process.traversal.dsl.graph

import java.util.{ List ⇒ JList, Optional }

import graf.gremlin.structure.convert.wrapAll._
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.{ TraversalSource, TraversalStrategy }
import org.apache.tinkerpop.gremlin.structure._

case class GrafGraphTraversalSource private[graph] (private val source: GraphTraversalSource, private val builder: GrafTraversalSourceBuilder) extends TraversalSource {

  def addV: GrafGraphTraversal[Vertex, Vertex] = source.addV()

  def addV(kv: (Any, AnyRef)*): GrafGraphTraversal[Vertex, Vertex] =
    source.addV(kv.flatMap(p ⇒ Seq(p._1, p._2)).map(_.asInstanceOf[Object]): _*)

  def E: GrafEdgeTraversal[Edge] = source.E()

  def E(edgesId: Any, edgesIds: Any*): GrafEdgeTraversal[Edge] =
    source.E((edgesId +: edgesIds).map(_.asInstanceOf[Object]): _*)

  def tx: Transaction = source.tx()

  def V: GrafVertexTraversal[Vertex] = source.V()

  def V(vertexId: Any, vertexIds: Any*): GrafVertexTraversal[Vertex] =
    source.V((vertexId +: vertexIds).map(_.asInstanceOf[Object]): _*)

  def withPath[S]: GrafGraphTraversalSourceStub =
    GrafGraphTraversalSourceStub(source.withPath[S]())

  def withSack[A](initialValue: A): GrafGraphTraversalSourceStub =
    GrafGraphTraversalSourceStub(source.withSack(initialValue))

  def withSack[A](initialValue: A, splitOperator: A ⇒ A): GrafGraphTraversalSourceStub =
    GrafGraphTraversalSourceStub(source.withSack(initialValue, splitOperator))

  def withSack[A](initialValue: ⇒ A): GrafGraphTraversalSourceStub =
    GrafGraphTraversalSourceStub(source.withSack(initialValue))

  def withSack[A](initialValue: ⇒ A, splitOperator: A ⇒ A): GrafGraphTraversalSourceStub =
    GrafGraphTraversalSourceStub(source.withSack(initialValue, splitOperator))

  def withSideEffect(key: String, supplier: ⇒ Any): GrafGraphTraversalSourceStub =
    GrafGraphTraversalSourceStub(source.withSideEffect(key, asJavaSupplier[Any](Unit ⇒ supplier)))

  def asBuilder(): GrafTraversalSourceBuilder = builder

  def getGraph: Optional[Graph] = source.getGraph

  def getGraphComputer: Optional[GraphComputer] = source.getGraphComputer

  def getStrategies: JList[TraversalStrategy[_]] = source.getStrategies

  override def toString: String = source.toString

}
