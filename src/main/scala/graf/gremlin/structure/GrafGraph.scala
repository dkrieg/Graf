package graf.gremlin
package structure

import graf.gremlin.structure.io._
import graf.gremlin.structure.schema._
import graf.gremlin.process.traversal.dsl.graph._

import scala.collection.JavaConversions._

case class GrafGraph(private[structure] val graph: Graph) {

  def edges: Iterator[GrafEdge] = graph.edges().toIterator

  def edges(edgeIds: Seq[AnyRef]): Iterator[GrafEdge] = graph.edges {
    edgeIds.map { a ⇒
      if (classOf[GrafEdge].isAssignableFrom(a.getClass)) a.asInstanceOf[GrafEdge].id
      else if (classOf[Edge].isAssignableFrom(a.getClass)) a.asInstanceOf[Edge].id
      else a
    }: _*
  }.toIterator

  def configuration = graph.configuration()

  def vertices: Iterator[GrafVertex] = graph.vertices().toIterator

  def vertices(vertexIds: Seq[AnyRef]): Iterator[GrafVertex] = graph.vertices {
    vertexIds.map { a ⇒
      if (classOf[GrafVertex].isAssignableFrom(a.getClass)) a.asInstanceOf[GrafVertex].id
      else if (classOf[Vertex].isAssignableFrom(a.getClass)) a.asInstanceOf[Vertex].id
      else a
    }: _*
  }.toIterator

  def variables: GrafVariables = graph.variables()

  def addVertex(label: String): GrafVertex = addVertex(Label(label))

  def addVertex(label: Label, atoms: Atom*): GrafVertex = graph.addVertex((label +: atoms) flatMap {
    atom ⇒ Seq(atom.key, atom.value)
  }: _*)

  def tx: GrafTransaction = graph.tx()

  def io[I <: IO](builder: GrafIO.Builder): GrafIO[I] = graph.io(builder.asJava).asInstanceOf[I]

  //  def traversal[C <: TraversalSource](sourceBuilder: Builder[C]): C = graph.traversal(sourceBuilder)

  def traversal: GrafGraphTraversalSource = graph.traversal()

  //  def compute[C <: GraphComputer](graphComputerClass: Class[C]): C = graph.compute(graphComputerClass)

  //  def compute(): GraphComputer = graph.compute()

  def features: GrafFeatures = graph.features()

  def close(): Unit = graph.close()
}
