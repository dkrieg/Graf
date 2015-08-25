package graf.gremlin
package structure.util

import graf.gremlin.structure._

import scalaz.Show.showFromToString

object show {
  implicit val EdgeShow = showFromToString[Edge]
  implicit val GraphShow = showFromToString[Graph]
  implicit def PropertyShow[A] = showFromToString[Property[A]]
  implicit val TransactionShow = showFromToString[Transaction]
  implicit val VertexShow = showFromToString[Vertex]
  implicit def VertexPropertyShow[A] = showFromToString[VertexProperty[A]]
}
