#!/usr/bin/env bash
set -e

OLDDIR=$(pwd)

export JAVA_OPTS="-Xmx4G"

echo "===HTTP4s==="
  cd forks/http4s && \
  git fetch --tags https://github.com/http4s/http4s &&
  sbt "++3.x; coreNative/publishLocal; clientNative/publishLocal; circeNative/publishLocal; jawnNative/publishLocal; ember-serverNative/publishLocal; ember-coreNative/publishLocal; serverNative/publishLocal; dslNative/publishLocal; ember-clientNative/publishLocal; circeJS/publishLocal; jawnJS/publishLocal; coreJS/publishLocal; clientJS/publishLocal; ember-coreJS/publishLocal; ember-serverJS/publishLocal; ember-clientJS/publishLocal; serverJS/publishLocal; dslJS/publishLocal; ++2.13.x; client/publishLocal; circe/publishLocal; core/publishLocal; jawn/publishLocal; ember-server/publishLocal; ember-core/publishLocal; server/publishLocal"  && \
  cd $OLDDIR

echo "===SMITHY4S==="
  cd forks/smithy4s && \
  git fetch --tags https://github.com/disneystreaming/smithy4s &&
  JAVA_OPTS="-Xmx4G" sbt "jsonNative3/publishLocal; catsNative3/publishLocal; coreNative3/publishLocal; http4s-kernelNative3/publishLocal; http4sNative3/publishLocal; jsonJS3/publishLocal; coreJS3/publishLocal; catsJS3/publishLocal; http4s-kernelJS3/publishLocal; http4sJS3/publishLocal; codegenPlugin2_12/publishLocal"  && \
  cd $OLDDIR

echo "===SKUNK==="
  cd forks/skunk && \
  git fetch --tags https://github.com/typelevel/skunk && \
  sbt "++3.x coreNative/publishLocal" && \
  cd $OLDDIR

echo "===DUMBO==="
  cd forks/dumbo && \
  git fetch --tags https://github.com/rolang/dumbo && \
  sbt "++3.x coreNative/publishLocal" && \
  cd $OLDDIR  

echo "===SCRIBE==="
  cd forks/scribe && \
  git fetch --tags https://github.com/outr/scribe && \
  sbt "++3.x; coreNative/publishLocal; catsNative/publishLocal" && \
  cd $OLDDIR

