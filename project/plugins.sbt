addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.16")
addSbtPlugin("com.indoorvivants.vcpkg" % "sbt-vcpkg-native" % "0.0.18")
addSbtPlugin("com.disneystreaming.smithy4s" % "smithy4s-sbt-codegen" % "0.18.3")
resolvers += Resolver.sonatypeRepo("snapshots")
addSbtPlugin("com.indoorvivants" % "bindgen-sbt-plugin" % "0.0.22")
addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.9.1")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.14.0")
addSbtPlugin("com.timushev.sbt" % "sbt-rewarn" % "0.1.3")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.10.0")

libraryDependencies += "com.indoorvivants.detective" %% "platform" % "0.0.2"
libraryDependencies += "com.indoorvivants" %% "yank" % "0.0.1"
