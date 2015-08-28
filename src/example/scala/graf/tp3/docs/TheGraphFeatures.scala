package graf.tp3.docs

import graf.gremlin.structure.util._
import graf.gremlin.structure.util.show._

import scala.language.postfixOps
import scalaz.Scalaz._

object TheGraphFeatures extends App {
 val graph = TinkerGraphFactory.open()
  graph.println

  graph.features.println
  graph.features.graph.supportsTransactions().println
  if(graph.features.graph.supportsTransactions()) graph.tx.commit()
}
