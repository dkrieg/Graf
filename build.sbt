name := """Graf"""
version := "1.0"
scalaVersion := "2.11.7"
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
libraryDependencies ++= {
  val scalaV = scalaVersion.value
  val gremlinV = "3.0.0-incubating"
//  val gremlinV = "3.1.0-SNAPSHOT"
  val scalazV = "7.1.3"
  val scalazStreamV = "0.7.2a"
  val scalaTestV = "2.2.4"
  val shaplessV = "2.2.4"
  Seq(
    "org.scala-lang"        % "scala-reflect"       % scalaV,
    "org.apache.tinkerpop"  % "gremlin-core"        % gremlinV,
    "org.apache.tinkerpop"  % "tinkergraph-gremlin" % gremlinV,
    "org.scalaz"           %% "scalaz-core"         % scalazV,
    "org.scalaz"           %% "scalaz-concurrent"   % scalazV,
    "org.scalaz.stream"    %% "scalaz-stream"       % scalazStreamV,
    "com.chuusai"          %% "shapeless"           % shaplessV,
    "org.scalatest"        %% "scalatest"           % scalaTestV % Test
  )
}
fork in run := true
unmanagedSourceDirectories in Compile += baseDirectory.value / "src" / "example" / "scala"
resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("snapshots")
)