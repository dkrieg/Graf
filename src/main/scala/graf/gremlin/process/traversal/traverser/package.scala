package graf.gremlin
package process.traversal

import scala.language.implicitConversions

import org.apache.tinkerpop.gremlin.process.traversal.traverser.{
  TraverserRequirement ⇒ JTraverserRequirement
}

package object traverser {
  type TraverserRequirement = JTraverserRequirement
}
