package graf
package example

import graf.gremlin._
import graf.gremlin.structure.schema._
import graf.gremlin.structure.syntax._
import graf.gremlin.structure.util.show._
import graf.gremlin.structure.util.TinkerGraphFactory
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.apache.tinkerpop.gremlin.structure.io.IoCore

import scalaz.Scalaz._

object GrafApp5 extends App {
  type Name = KeyValue
  type SongType = KeyValue
  type Weight = KeyValue
  type Performances = KeyValue

  val Song = Label("song")
  val Artist = Label("artist")
  val SungBy = Label("sung_by")
  val WrittenBy = Label("written_by")
  val FollowedBy = Label("followed_by")

  val Name = Key[String]("name")
  val SongType = Key[String]("song_type")
  val Weight = Key[Int]("weight")
  val Performances = Key[Int]("performances")

  def addSong(name: Name, performances: Performances, songType: SongType,
              sungBy: Name, writtenBy: Name): Graf[Option[Vertex]] = Graf {
    for {
      s ← findArtist(sungBy)
      w ← findArtist(writtenBy)
      v ← createSong(name, performances, songType)
    } yield {
      v --- SungBy --> s.get
      v --- WrittenBy --> w.get
      v.point[Option]
    }
  }

  def findArtist(name: Name): Graf[Option[Vertex]] = Graf {
    for {
      g ← G
      v = g.traversal(grafStandard).V.hasLabel(Artist).hasKeyValue(name).headOption
    } yield v
  }

  def createSong(name: Name, performances: Performances, songType: SongType): Graf[Vertex] = Graf {
    for {
      g ← G
      v = g + (Song, name, performances, songType)
    } yield v
  }

  def createArtist(name: Name): Graf[Vertex] = Graf {
    for {
      g <- G
      a = g + (Artist, name)
    } yield a
  }

  val script: Graf[Unit] = Graf {
    for {
      a <- createArtist(Name("Jerry Garcia"))
      b <- createArtist(Name("Robert Hunter"))
      s <- addSong(Name("Dark Start"), Performances(85), SongType("Psychedelic rock"),
                   Name(a.value(Name.key)), Name(b.value(Name.key)))
    } yield ()
  }

  val graph = TinkerGraphFactory.open()
  script(graph).run
  graph.println
  graph.io(IoCore.graphson()).writer.create().writeGraph(Console.out, graph)
  graph.close()
}
