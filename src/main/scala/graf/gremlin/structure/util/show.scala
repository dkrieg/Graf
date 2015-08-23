package graf.gremlin.structure.util

import graf.gremlin.structure._

import scalaz.Show.showFromToString

object show {
  implicit val GrafEdgeShow = showFromToString[GrafEdge]
  implicit val GrafFeaturesShow = showFromToString[GrafFeatures]
  implicit val GrafGraphShow = showFromToString[GrafGraph]
  implicit def GrafPropertyShow[A] = showFromToString[GrafProperty[A]]
  implicit val GrafTransactionShow = showFromToString[GrafTransaction]
  implicit val GrafVariablesShow = showFromToString[GrafVariables]
  implicit val GrafVertexShow = showFromToString[GrafVertex]
  implicit def GrafVertexPropertyShow[A] = showFromToString[GrafVertexProperty[A]]
}
