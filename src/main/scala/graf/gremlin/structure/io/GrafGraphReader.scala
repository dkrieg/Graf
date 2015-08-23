package graf.gremlin
package structure
package io

import graf.gremlin.structure._
import structure.util._
import java.io.InputStream
import scala.collection.JavaConversions._

case class GrafGraphReader(private[io] val graphReader: GraphReader) {

  def readGraph(inputStream: InputStream, graphToWriteTo: GrafGraph): Unit =
    graphReader.readGraph(inputStream, graphToWriteTo.asJava)

  def readVertex(
    inputStream: InputStream,
    vertexAttachMethod: Attachable[GrafVertex] ⇒ GrafVertex,
    edgeAttachMethod: Attachable[GrafEdge] ⇒ GrafEdge,
    attachEdgesOfThisDirection: Direction): GrafVertex =
    graphReader.readVertex(
      inputStream,
      (a: Attachable[Vertex]) ⇒ vertexAttachMethod(a.map(_.asScala)).asJava,
      (a: Attachable[Edge]) ⇒ edgeAttachMethod(a.map(_.asScala)).asJava,
      attachEdgesOfThisDirection.asJava).asScala

  def readObject[C](inputStream: InputStream, clazz: Class[_ <: C]): C = graphReader.readObject(inputStream, clazz)

  def readVertex(
    inputStream: InputStream,
    vertexAttachMethod: Attachable[GrafVertex] ⇒ GrafVertex): GrafVertex =
    graphReader.readVertex(
      inputStream,
      (a: Attachable[Vertex]) ⇒ vertexAttachMethod(a.map(_.asScala)).asJava).asScala

  def readVertices(
    inputStream: InputStream,
    vertexAttachMethod: Attachable[GrafVertex] ⇒ GrafVertex,
    edgeAttachMethod: Attachable[GrafEdge] ⇒ GrafEdge,
    attachEdgesOfThisDirection: Direction): Iterator[GrafVertex] =
    graphReader.readVertices(
      inputStream,
      (a: Attachable[Vertex]) ⇒ vertexAttachMethod(a.map(_.asScala)).asJava,
      (a: Attachable[Edge]) ⇒ edgeAttachMethod(a.map(_.asScala)).asJava,
      attachEdgesOfThisDirection.asJava).toIterator.asScala

  def readProperty(
    inputStream: InputStream,
    propertyAttachMethod: Attachable[GrafProperty[_]] ⇒ GrafProperty[_]): GrafProperty[_] =
    graphReader.readProperty(
      inputStream,
      (a: Attachable[Property[_]]) ⇒ propertyAttachMethod(a.map(_.asScala)).asJava).asScala

  def readVertexProperty(
    inputStream: InputStream,
    vertexPropertyAttachMethod: Attachable[GrafVertexProperty[_]] ⇒ GrafVertexProperty[_]): GrafVertexProperty[_] =
    graphReader.readVertexProperty(
      inputStream,
      (a: Attachable[VertexProperty[_]]) ⇒ vertexPropertyAttachMethod(a.map(_.asScala)).asJava).asScala

  def readEdge(
    inputStream: InputStream,
    edgeAttachMethod: Attachable[GrafEdge] ⇒ GrafEdge): GrafEdge =
    graphReader.readEdge(
      inputStream,
      (a: Attachable[Edge]) ⇒ edgeAttachMethod(a.map(_.asScala)).asJava).asScala
}
