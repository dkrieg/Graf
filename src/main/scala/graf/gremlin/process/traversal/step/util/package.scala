package graf.gremlin
package process.traversal.step

import org.apache.tinkerpop.gremlin.process.traversal.step.util.{
  BulkSet ⇒ JBulkSet,
  Tree => JTree
}
import scala.language.implicitConversions

package object util {
  type BulkSet[S] = JBulkSet[S]
  type Tree[A] = JTree[A]

  implicit def toGrafBulkSet[A](bs: BulkSet[A]): GrafBulkSet[A] = GrafBulkSet[A](bs)
}
