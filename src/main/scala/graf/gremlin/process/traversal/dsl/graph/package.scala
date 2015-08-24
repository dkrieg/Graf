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
  type Admin[Start, End] = JTraversal.Admin[Start, End]
  type GraphTraversalSource = JGraphTraversalSource
  type GraphTraversal[Start, End] = JGraphTraversal[Start, End]

  implicit def toGrafGraphTraversalSource(gt: GraphTraversalSource): GrafGraphTraversalSource =
    GrafGraphTraversalSource(gt)

  implicit def toGrafTraversal[Start, End](t: Traversal[Start, End]): GrafTraversal[Start, End] =
    new GrafTraversal[Start, End](t)

  implicit def toGrafVertexGraphTraversal[Start](t: GraphTraversal[Start, Vertex]): GrafVertexGraphTraversal[Start] =
    GrafVertexGraphTraversal[Start](t)

  implicit def toGrafEdgeGraphTraversal[Start](t: GraphTraversal[Start, Edge]): GrafEdgeGraphTraversal[Start] =
    GrafEdgeGraphTraversal[Start](t)
  
  implicit def toTraversalAdmin[Start, End](g: GrafTraversalAdmin[Start, End]): TraversalAdmin[Start, End] =
    g.admin
  
  implicit def toGrafTraversalAdmin[Start, End](t: TraversalAdmin[Start, End]): GrafTraversalAdmin[Start, End] =
    new GrafTraversalAdmin(t)
}
