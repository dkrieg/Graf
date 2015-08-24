package graf.gremlin
package structure
package io

import graf.gremlin.structure._
import structure.util._
import java.io.InputStream
import scala.collection.JavaConversions._

case class GrafGraphReader(private[io] val graphReader: GraphReader) {

  def readGraph(inputStream: InputStream, graphToWriteTo: GrafGraph): Unit =
    graphReader.readGraph(inputStream, graphToWriteTo)

  def readVertex(
    in: InputStream,
    vf: Attachable[GrafVertex] ⇒ GrafVertex,
    ef: Attachable[GrafEdge] ⇒ GrafEdge,
    d: Direction): GrafVertex = graphReader.readVertex(in, vf, ef, d)

  def readObject[C](in: InputStream, clazz: Class[_ <: C]): C = graphReader.readObject(in, clazz)

  def readVertex(in: InputStream, f: Attachable[GrafVertex] ⇒ GrafVertex): GrafVertex = graphReader.readVertex(in, f)

  def readVertices(
    in: InputStream,
    vf: Attachable[GrafVertex] ⇒ GrafVertex,
    ef: Attachable[GrafEdge] ⇒ GrafEdge,
    d: Direction): Iterator[GrafVertex] = graphReader.readVertices(in, vf, ef, d).toIterator

  def readProperty(
    in: InputStream,
    f: Attachable[GrafProperty[_]] ⇒ GrafProperty[_]): GrafProperty[_] = graphReader.readProperty(in, f)

  def readVertexProperty(
    in: InputStream,
    f: Attachable[GrafVertexProperty[_]] ⇒ GrafVertexProperty[_]): GrafVertexProperty[_] = 
    graphReader.readVertexProperty(in, f)

  def readEdge(
    in: InputStream,
    f: Attachable[GrafEdge] ⇒ GrafEdge): GrafEdge = graphReader.readEdge(in, f)
}
