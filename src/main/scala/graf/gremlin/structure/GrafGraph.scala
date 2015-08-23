package graf.gremlin
package structure

import graf.gremlin.structure.io._
//import org.apache.tinkerpop.gremlin.structure.io.GraphReader.ReaderBuilder
//import org.apache.tinkerpop.gremlin.structure.io.GraphWriter.WriterBuilder
//import org.apache.tinkerpop.gremlin.structure.io.{Io, Mapper}
import schema.{ Atom, Label }

import scala.collection.JavaConversions._

case class GrafGraph(private[structure] val graph: Graph) {

  def edges: Iterator[GrafEdge] = graph.edges().toIterator.asScala

  def edges(edgeIds: Seq[AnyRef]): Iterator[GrafEdge] = graph.edges {
    edgeIds.map { a ⇒
      if (classOf[GrafEdge].isAssignableFrom(a.getClass)) a.asInstanceOf[GrafEdge].id
      else a
    }: _*
  }.toIterator.asScala

  def configuration = graph.configuration()

  def vertices: Iterator[GrafVertex] = graph.vertices().toIterator.asScala

  def vertices(vertexIds: Seq[AnyRef]): Iterator[GrafVertex] = graph.vertices {
    vertexIds.map { a ⇒
      if (classOf[GrafVertex].isAssignableFrom(a.getClass)) a.asInstanceOf[GrafVertex].id
      else a
    }: _*
  }.toIterator.asScala

  def variables = graph.variables().asScala

  def addVertex(label: String): GrafVertex = addVertex(Label(label))

  def addVertex(label: Label, atoms: Atom*): GrafVertex = graph.addVertex((label +: atoms) flatMap {
    atom ⇒ Seq(atom.key, atom.value)
  }: _*).asScala

  def +(label: String): GrafVertex = addVertex(label)

  def +(label: Label, atoms: Atom*): GrafVertex = addVertex(label, atoms: _*)

  def tx = graph.tx().asScala

  def io[I <: IO](builder: GrafIO.Builder): GrafIO[I] = graph.io(builder.asJava).asInstanceOf[I].asScala

  //  def traversal[C <: TraversalSource](sourceBuilder: Builder[C]): C = graph.traversal(sourceBuilder)

  //  def traversal(): GraphTraversalSource = graph.traversal()

  //  def compute[C <: GraphComputer](graphComputerClass: Class[C]): C = graph.compute(graphComputerClass)

  //  def compute(): GraphComputer = graph.compute()

  def features: GrafFeatures = graph.features().asScala

  def close(): Unit = graph.close()
}
