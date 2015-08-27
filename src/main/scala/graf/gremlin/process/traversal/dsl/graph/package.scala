package graf.gremlin
package process.traversal.dsl

import org.apache.tinkerpop.gremlin.process.traversal.Traversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.structure.{Edge, Vertex}

import scala.language.implicitConversions

package object graph {

  implicit def toGrafTraversal[Start, End](t: Traversal[Start, End]): GrafTraversal[Start, End] =
    new GrafTraversal[Start, End](t)

  implicit def toTraversal[Start, End](t: GrafTraversal[Start, End]): Traversal[Start, End] =
    t.traversal

  implicit def toGrafVertexGraphTraversal[Start](t: GraphTraversal[Start, Vertex]): GrafVertexTraversal[Start] =
    new GrafVertexTraversal(t)

  implicit def toGrafEdgeGraphTraversal[Start](t: GraphTraversal[Start, Edge]): GrafEdgeTraversal[Start] =
    new GrafEdgeTraversal(t)

  implicit def toTraversalAdmin[Start, End](g: GrafTraversalAdmin[Start, End]): Traversal.Admin[Start, End] =
    g.admin

  implicit def toGrafTraversalAdmin[Start, End](t: Traversal.Admin[Start, End]): GrafTraversalAdmin[Start, End] =
    new GrafTraversalAdmin(t)

  implicit def toGrafElementGraphTraversal[Start, End](t: GraphTraversal[Start, End]): GrafElementTraversal[Start, End] =
    new GrafElementTraversal(t)
}
