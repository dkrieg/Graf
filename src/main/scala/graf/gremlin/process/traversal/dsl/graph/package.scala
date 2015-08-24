package graf.gremlin
package process.traversal.dsl

import graf.gremlin.structure._
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.{
  GraphTraversal ⇒ JGraphTraversal,
  GraphTraversalSource ⇒ JGraphTraversalSource
}
import org.apache.tinkerpop.gremlin.process.traversal.{ Traversal ⇒ JTraversal }

import scala.language.implicitConversions

package object graph {
  type Traversal[Start, End] = JTraversal[Start, End]
  type TraversalAdmin[Start, End] = JTraversal.Admin[Start, End]
  type GraphTraversalSource = JGraphTraversalSource
  type GraphTraversal[Start, End] = JGraphTraversal[Start, End]

  implicit def toGrafGraphTraversalSource(gt: GraphTraversalSource): GrafGraphTraversalSource =
    GrafGraphTraversalSource(gt)

  implicit def toGrafTraversal[Start, End](t: Traversal[Start, End]): GrafTraversal[Start, End] =
    new GrafTraversal[Start, End](t)

  implicit def toTraversal[Start, End](t: GrafTraversal[Start, End]): Traversal[Start, End] =
    t.traversal

  implicit def toGrafVertexGraphTraversal[Start](t: GraphTraversal[Start, GrafVertex]): GrafVertexGraphTraversal[Start] =
    GrafVertexGraphTraversal(t)

  implicit def toGrafEdgeGraphTraversal[Start](t: GraphTraversal[Start, GrafEdge]): GrafEdgeGraphTraversal[Start] =
    GrafEdgeGraphTraversal(t)
  
  implicit def toTraversalAdmin[Start, End](g: GrafTraversalAdmin[Start, End]): TraversalAdmin[Start, End] =
    g.admin
  
  implicit def toGrafTraversalAdmin[Start, End](t: TraversalAdmin[Start, End]): GrafTraversalAdmin[Start, End] =
    new GrafTraversalAdmin(t)

  implicit def toGrafElementGraphTraversal[Start, End](t: GraphTraversal[Start, End]): GrafElementGraphTraversal[Start, End] =
    new GrafElementGraphTraversal(t)
}
