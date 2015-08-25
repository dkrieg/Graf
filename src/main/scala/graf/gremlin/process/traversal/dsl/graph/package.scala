package graf.gremlin
package process.traversal.dsl


import scala.language.implicitConversions

package object graph {
  implicit def toGrafTraversal[Start, End](t: Traversal[Start, End]): GrafTraversal[Start, End] =
    new GrafTraversal[Start, End](t)

  implicit def toTraversal[Start, End](t: GrafTraversal[Start, End]): Traversal[Start, End] =
    t.traversal

  implicit def toGrafVertexGraphTraversal[Start](t: GraphTraversal[Start, Vertex]): GrafVertexGraphTraversal[Start] =
    GrafVertexGraphTraversal(t)

  implicit def toGrafEdgeGraphTraversal[Start](t: GraphTraversal[Start, Edge]): GrafEdgeGraphTraversal[Start] =
    GrafEdgeGraphTraversal(t)

  implicit def toTraversalAdmin[Start, End](g: GrafTraversalAdmin[Start, End]): TraversalAdmin[Start, End] =
    g.admin

  implicit def toGrafTraversalAdmin[Start, End](t: TraversalAdmin[Start, End]): GrafTraversalAdmin[Start, End] =
    new GrafTraversalAdmin(t)

  implicit def toGrafElementGraphTraversal[Start, End](t: GraphTraversal[Start, End]): GrafElementGraphTraversal[Start, End] =
    new GrafElementGraphTraversal(t)
}
