addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.12")
addSbtPlugin("com.indoorvivants.vcpkg" % "sbt-vcpkg-native" % "0.0.11")
addSbtPlugin("com.disneystreaming.smithy4s" % "smithy4s-sbt-codegen" % "0.17.6")
resolvers += Resolver.sonatypeRepo("snapshots")
addSbtPlugin("com.indoorvivants" % "bindgen-sbt-plugin" % "0.0.17")
addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.9.0")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.13.1")

libraryDependencies += "com.indoorvivants.detective" %% "platform" % "0.0.2"
