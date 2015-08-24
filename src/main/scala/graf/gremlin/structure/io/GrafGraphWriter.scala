package graf.gremlin
package structure
package io

import java.io.OutputStream

case class GrafGraphWriter(private[io] val graphReader: GraphWriter) {
  def writeProperty(os: OutputStream, p: GrafProperty[_]): Unit =
    graphReader.writeProperty(os, p)

  def writeVertex(os: OutputStream, v: GrafVertex): Unit =
    graphReader.writeVertex(os, v)

  def writeVertices(os: OutputStream, it: Iterator[GrafVertex]): Unit =
    graphReader.writeVertices(os, it)

  def writeVertex(os: OutputStream, v: GrafVertex, d: Direction): Unit =
    graphReader.writeVertex(os, v, d)

  def writeGraph(os: OutputStream, g: GrafGraph): Unit =
    graphReader.writeGraph(os, g)

  def writeVertexProperty(os: OutputStream, vp: GrafVertexProperty[_]): Unit =
    graphReader.writeVertexProperty(os, vp)

  def writeEdge(os: OutputStream, e: GrafEdge): Unit =
    graphReader.writeEdge(os, e)

  def writeObject(os: OutputStream, a: Any): Unit =
    graphReader.writeObject(os, a)

  def writeVertices(os: OutputStream, it: Iterator[GrafVertex], d: Direction): Unit = 
    graphReader.writeVertices(os, it, d)

}
