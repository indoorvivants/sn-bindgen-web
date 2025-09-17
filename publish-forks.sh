#!/usr/bin/env bash
set -e

OLDDIR=$(pwd)
cd $(mktemp -d)

echo "Working in $(pwd)"

export JAVA_OPTS="-Xmx4G"

echo "===SOURCEPOS==="
git clone https://github.com/tpolecat/SourcePos && \
  cd SourcePos && \
  git checkout v1.2.0 && \
  git checkout -- . && \
  GITHUB_REF_TYPE=tag sbt "++3.x sourceposNative/publishLocal"  && \
  cd ../  


echo "===SMITHY4S==="
git clone https://github.com/majk-p/smithy4s && \
  cd smithy4s && \
  git checkout 43c7e948a3b0d4eecbc04091f7c4182e8d4068e9 && \
  git fetch --tags https://github.com/disneystreaming/smithy4s &&
  sbt "jsonNative3/publishLocal; catsNative3/publishLocal; coreNative3/publishLocal; http4s-kernelNative3/publishLocal; http4sNative3/publishLocal; jsonJS3/publishLocal; coreJS3/publishLocal; catsJS3/publishLocal; http4s-kernelJS3/publishLocal; http4sJS3/publishLocal; codegenPlugin2_12/publishLocal"  && \
  cd ../

echo "===SKUNK==="
git clone https://github.com/keynmol/skunk && \
  cd skunk && \
  git checkout 7f46fa88a8f0e467cc84375bc8fad605859522a9 && \
  git fetch --tags https://github.com/typelevel/skunk && \
  sbt "++3.x coreNative/publishLocal" && \
  cd ../ 

echo "===DUMBO==="
git clone https://github.com/keynmol/dumbo && \
  cd dumbo && \
  git checkout f0f2e0ffbc50c069a7bf5815ea9c8ba385ab3c98 && \
  git fetch --tags https://github.com/rolang/dumbo && \
  sbt "++3.x coreNative/publishLocal" && \
  cd ../  

echo "===SCRIBE==="
git clone https://github.com/keynmol/scribe && \
  cd scribe && \
  git checkout a2a5fbd2aed33a0c34d8693318b68fda7352379c && \
  git fetch --tags https://github.com/outr/scribe && \
  sbt "++3.x; coreNative/publishLocal; catsNative/publishLocal" && \
  cd ../

echo "===SNUNIT==="
git clone https://github.com/keynmol/snunit && \
  cd snunit && \
  git checkout 4585f379fc7ad399fb3ad1d8a5c6c5ad70ce6e83 && \
  git fetch --tags https://github.com/lolgab/snunit && \
  ./mill "{snunit-http4s,snunit,snunit-async-cats-effect}.publishLocal" && \
  cd ../

cd $OLDDIR
