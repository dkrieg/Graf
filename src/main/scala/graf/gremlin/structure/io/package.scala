package graf.gremlin
package structure

import graf.gremlin.structure.util._
import org.apache.tinkerpop.gremlin.structure.io.{
  GraphReader ⇒ JGraphReader,
  GraphWriter ⇒ JGraphWriter,
  Io,
  Mapper
}
import java.util.function.{ Function ⇒ JFunction}
import scala.language.implicitConversions

package object io {
  type GraphReader = JGraphReader
  type GraphWriter = JGraphWriter
  type IO = Io[_ <: JGraphReader.ReaderBuilder[_ <: GraphReader], _ <: JGraphWriter.WriterBuilder[_ <: GraphWriter], _ <: Mapper.Builder[_]]

  implicit def toGrafIO[I <: IO](io: I): GrafIO[I] = GrafIO(io)

  implicit def toVertexAttachableFn(f: Attachable[GrafVertex] ⇒ GrafVertex): JFunction[Attachable[Vertex], Vertex] = ???

  implicit def toEdgeAttachableFn(f: Attachable[GrafEdge] ⇒ GrafEdge): JFunction[Attachable[Edge], Edge] = ???

  implicit def toPropertyAttachableFn(f: Attachable[GrafProperty[_]] ⇒ GrafProperty[_]): JFunction[Attachable[Property[_]], Property[_]] = ???

  implicit def toVertexPropertyAttachableFn(f: Attachable[GrafVertexProperty[_]] ⇒ GrafVertexProperty[_]): JFunction[Attachable[VertexProperty[_]], VertexProperty[_]] = ???
}
