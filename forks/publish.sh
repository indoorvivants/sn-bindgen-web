#!/usr/bin/env bash
set -e

OLDDIR=$(pwd)

export JAVA_OPTS="-Xmx4G"

echo "===DUMBO==="
  cd forks/dumbo && \
  git fetch --tags https://github.com/rolang/dumbo && \
  sbt "++3.x coreNative/publishLocal" && \
  cd $OLDDIR
