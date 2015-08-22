package com.graf.gremlin
package structure
package io

import java.io.OutputStream
import scala.collection.JavaConverters._

case class GrafGraphWriter(private[io] val graphReader: GraphWriter) {
  def writeProperty(
    outputStream: OutputStream,
    p: GrafProperty[_]): Unit =
    graphReader.writeProperty(outputStream, p.asJava)

  def writeVertex(
    outputStream: OutputStream,
    v: GrafVertex): Unit =
    graphReader.writeVertex(outputStream, v.asJava)

  def writeVertices(
    outputStream: OutputStream,
    vertexIterator: Iterator[GrafVertex]): Unit =
    graphReader.writeVertices(outputStream, vertexIterator.asJava.asJava)

  def writeVertex(
     outputStream: OutputStream,
     v: GrafVertex,
     direction: Direction): Unit =
    graphReader.writeVertex(outputStream, v.asJava, direction.asJava)

  def writeGraph(
    outputStream: OutputStream, g: GrafGraph): Unit =
    graphReader.writeGraph(outputStream, g.asJava)

  def writeVertexProperty(
    outputStream: OutputStream,
    vp: GrafVertexProperty[_]): Unit =
    graphReader.writeVertexProperty(outputStream, vp.asJava)

  def writeEdge(
    outputStream: OutputStream, e: GrafEdge): Unit =
    graphReader.writeEdge(outputStream, e.asJava)

  def writeObject(
    outputStream: OutputStream,
    `object`: Any): Unit =
    graphReader.writeObject(outputStream, `object`)

  def writeVertices(
    outputStream: OutputStream,
    vertexIterator: Iterator[GrafVertex],
    direction: Direction): Unit =
    graphReader.writeVertices(
      outputStream,
      vertexIterator.asJava.asJava,
      direction.asJava)

}
