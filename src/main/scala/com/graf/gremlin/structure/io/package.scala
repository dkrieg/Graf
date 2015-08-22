package com.graf.gremlin.structure

import org.apache.tinkerpop.gremlin.structure.io.{
  GraphReader ⇒ JGraphReader,
  GraphWriter ⇒ JGraphWriter,
  Io,
  Mapper
}

package object io {
  type GraphReader = JGraphReader
  type GraphWriter = JGraphWriter
  type IO = Io[_ <: JGraphReader.ReaderBuilder[_ <: GraphReader], _ <: JGraphWriter.WriterBuilder[_ <: GraphWriter], _ <: Mapper.Builder[_]]

  implicit class IoAsScala[I <: IO](io: I) {
    def asScala = GrafIO(io)
  }
}
