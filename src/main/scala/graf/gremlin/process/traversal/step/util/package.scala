package graf.gremlin.process.traversal.step

import org.apache.tinkerpop.gremlin.process.traversal.step.util.{ BulkSet ⇒ JBulkSet }
import scala.language.implicitConversions

package object util {
  type BulkSet[S] = JBulkSet[S]

  implicit def toGrafBulkSet[A](bs: BulkSet[A]): GrafBulkSet[A] = GrafBulkSet[A](bs)
}
