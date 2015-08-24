package graf.gremlin
package structure
package io

import scala.language.existentials

case class GrafIO[I <: IO](private[io] val io: I) {
  trait ReaderBuilder[A] {
    def create(): A
  }

  trait WriterBuilder[A] {
    def create(): A
  }

  def readGraph(file: String): Unit = io.readGraph(file)

  def writeGraph(file: String): Unit = io.writeGraph(file)

  def reader: ReaderBuilder[GrafGraphReader] = new ReaderBuilder[GrafGraphReader] {
    override def create(): GrafGraphReader = GrafGraphReader(io.reader().create())
  }

  def writer: WriterBuilder[GrafGraphWriter] = new WriterBuilder[GrafGraphWriter] {
    override def create() = GrafGraphWriter(io.writer().create)
  }
}

object GrafIO {
  import org.apache.tinkerpop.gremlin.structure.io._
  import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLIo
  import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONIo
  import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoIo

  sealed trait Builder

  case object GraphM extends Builder
  case object GraphSON extends Builder
  case object Gryo extends Builder

  implicit class BuilderAsJava(b: Builder) {
    def asJava[I <: Io[_,_,_]]: Io.Builder[I] = b match {
      case GraphM ⇒ GraphMLIo.build.asInstanceOf[Io.Builder[I]]
      case GraphSON ⇒ GraphSONIo.build.asInstanceOf[Io.Builder[I]]
      case Gryo ⇒ GryoIo.build.asInstanceOf[Io.Builder[I]]
    }
  }
}
