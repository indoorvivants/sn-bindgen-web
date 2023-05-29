VERSION 0.7


llvm-base:
    FROM eclipse-temurin:17-jammy
    RUN apt update && apt install -y curl && \
      # install coursier
      curl -fL -o /bin/cs https://github.com/coursier/launchers/raw/master/coursier && \
      chmod +x /bin/cs && \
      # install LLVM and libclang
      curl -Lo llvm.sh https://apt.llvm.org/llvm.sh && \
      chmod +x llvm.sh && \
      apt install -y lsb-release wget software-properties-common gnupg autopoint libtool && \
      ./llvm.sh 14 && \
      apt update && \
      apt install -y zip unzip tar make cmake autoconf ninja-build pkg-config libclang-14-dev git && \
      # install Unit development headers
      curl --output /usr/share/keyrings/nginx-keyring.gpg  \
        https://unit.nginx.org/keys/nginx-keyring.gpg && \
      echo "deb [signed-by=/usr/share/keyrings/nginx-keyring.gpg] https://packages.nginx.org/unit/ubuntu/ $(lsb_release -c -s) unit" >> /etc/apt/sources.list.d/unit.list && \
      echo "deb-src [signed-by=/usr/share/keyrings/nginx-keyring.gpg] https://packages.nginx.org/unit/ubuntu/ $(lsb_release -c -s) unit" >> /etc/apt/sources.list.d/unit.list && \
      apt update && \
      apt install -y unit-dev    
    # Install and cache SBT
    COPY project/build.properties /sources/project/build.properties
    RUN curl -Lo /bin/sbt https://raw.githubusercontent.com/sbt/sbt/1.9.x/sbt && \
      chmod +x /bin/sbt && \
      cd /sources && sbt --sbt-create version

    SAVE IMAGE --push bindgen-web-base:14

deps:
    FROM +llvm-base
    WORKDIR /sources

    RUN cs bootstrap com.indoorvivants.vcpkg:sn-vcpkg_3:0.0.11 -f -o /bin/sn-vcpkg

    # VCPKG dependencies
    COPY vcpkg.json /sources/
    ENV CC=/usr/lib/llvm-14/bin/clang
    ENV CXX=/usr/lib/llvm-14/bin/clang++
    ENV VCPKG_FORCE_SYSTEM_BINARIES "true"
    RUN sn-vcpkg install-manifest vcpkg.json -l -c

    # SBT dependencies
    COPY build.sbt /sources
    COPY project/*.sbt /sources/project/
    COPY project/*.scala /sources/project/
    COPY project/build.properties /sources/project/
    RUN sbt update

    SAVE IMAGE --push bindgen-web-build

app:
    FROM +deps
    WORKDIR /sources
    COPY . /sources
    ARG mode=debug

    ENV LLVM_BIN=/usr/lib/llvm-14/bin
    ENV CC=/usr/lib/llvm-14/bin/clang
    ENV CXX=/usr/lib/llvm-14/bin/clang++

    ENV SN_RELEASE $mode
    #ENV CI "true"

    RUN sbt --client 'clean; buildApp'
    SAVE ARTIFACT build/bindgen-web /build/bindgen-web AS LOCAL .docker-build/bindgen-web
    SAVE ARTIFACT build/bindgen-worker /build/bindgen-worker AS LOCAL .docker-build/bindgen-worker
    SAVE ARTIFACT build/static /build/static AS LOCAL .docker-build/static

    ENV BINDGEN_WEB_HTTP_APP_PATH=/usr/bin/bindgen-web
    ENV BINDGEN_WEB_WORKER_APP_PATH=/usr/bin/bindgen-worker
    ENV BINDGEN_WEB_STATIC_PATH=/www/static
    ENV BINDGEN_WEB_CONFIG_PATH=build/config.json
    ENV BINDGEN_WEB_STORAGE_PATH=/var/data/bindgen-web


    RUN sbt --client writeConfig
    SAVE ARTIFACT build/config.json /build/config.json AS LOCAL .docker-build/config.json

    SAVE IMAGE --push bindgen-web-app

docker:
    FROM +unit

    RUN apt update && apt install -y curl && \
      # install LLVM and libclang
      curl -Lo llvm.sh https://apt.llvm.org/llvm.sh && \
      chmod +x llvm.sh && \
      apt install -y lsb-release wget software-properties-common gnupg autopoint libtool && \
      ./llvm.sh 14 && \
      apt update && \
      apt install -y zip unzip tar make cmake autoconf ninja-build pkg-config libclang-14-dev git

    ARG ver=latest

    COPY +app/build/bindgen-web /usr/bin/bindgen-web
    COPY +app/build/bindgen-worker /usr/bin/bindgen-worker
    COPY +app/build/static/* /www/static/
    COPY +app/build/config.json /docker-entrypoint.d/config.json

    RUN chown -R unit /usr/bin/bindgen-web && chmod +x /usr/bin/bindgen-web && chmod 0777 /usr/bin/bindgen-web
    RUN chown -R unit /usr/bin/bindgen-worker && chmod +x /usr/bin/bindgen-worker && chmod 0777 /usr/bin/bindgen-worker
    RUN chown -R unit /www/static

    ENV LLVM_BIN=/usr/lib/llvm-14/bin

    RUN ldd /usr/bin/bindgen-web || echo "runnable"
    RUN mkdir -p /var/data/bindgen-web && chown -R unit /var/data/bindgen-web

    EXPOSE 9999
    CMD ["unitd", "--no-daemon", "--control", "unix:/var/run/control.unit.sock", "--log", "/dev/stderr"]
    SAVE IMAGE --push bindgen-web:$ver

smoke-test:
  FROM earthly/dind:ubuntu
  RUN apt update && apt install -y curl
  WORKDIR /test
  WITH DOCKER --load service:latest=+docker
    RUN docker run -d -p 9999:9999 service:latest && sleep 5 && \
        curl -v http://localhost:9999/api/status/hello
  END


unit:
  FROM ubuntu:22.04


  LABEL org.opencontainers.image.title="Unit"
  LABEL org.opencontainers.image.description="Official build of Unit for Docker."
  LABEL org.opencontainers.image.url="https://unit.nginx.org"
  LABEL org.opencontainers.image.source="https://github.com/nginx/unit"
  LABEL org.opencontainers.image.documentation="https://unit.nginx.org/installation/#docker-images"
  LABEL org.opencontainers.image.vendor="NGINX Docker Maintainers <docker-maint@nginx.com>"
  LABEL org.opencontainers.image.version="1.30.0"

  RUN set -ex \
      && savedAptMark="$(apt-mark showmanual)" \
      && apt-get update \
      && apt-get install --no-install-recommends --no-install-suggests -y ca-certificates mercurial build-essential libssl-dev libpcre2-dev curl pkg-config \
      && mkdir -p /usr/lib/unit/modules /usr/lib/unit/debug-modules \
      && hg clone -u 1.30.0-1 https://hg.nginx.org/unit \
      && cd unit \
      && NCPU="$(getconf _NPROCESSORS_ONLN)" \
      && DEB_HOST_MULTIARCH="$(dpkg-architecture -q DEB_HOST_MULTIARCH)" \
      && CC_OPT="$(DEB_BUILD_MAINT_OPTIONS="hardening=+all,-pie" DEB_CFLAGS_MAINT_APPEND="-Wp,-D_FORTIFY_SOURCE=2 -fPIC" dpkg-buildflags --get CFLAGS)" \
      && LD_OPT="$(DEB_BUILD_MAINT_OPTIONS="hardening=+all,-pie" DEB_LDFLAGS_MAINT_APPEND="-Wl,--as-needed -pie" dpkg-buildflags --get LDFLAGS)" \
      && CONFIGURE_ARGS_MODULES="--prefix=/usr \
                  --statedir=/var/lib/unit \
                  --control=unix:/var/run/control.unit.sock \
                  --pid=/var/run/unit.pid \
                  --log=/var/log/unit.log \
                  --tmpdir=/var/tmp \
                  --user=unit \
                  --group=unit \
                  --openssl \
                  --libdir=/usr/lib/$DEB_HOST_MULTIARCH" \
      && CONFIGURE_ARGS="$CONFIGURE_ARGS_MODULES \
                  --njs" \
      && make -j $NCPU -C pkg/contrib .njs \
      && export PKG_CONFIG_PATH=$(pwd)/pkg/contrib/njs/build \
      && ./configure $CONFIGURE_ARGS --cc-opt="$CC_OPT" --ld-opt="$LD_OPT" --modulesdir=/usr/lib/unit/debug-modules --debug \
      && make -j $NCPU unitd \
      && install -pm755 build/sbin/unitd /usr/sbin/unitd-debug \
      && make clean \
      && ./configure $CONFIGURE_ARGS --cc-opt="$CC_OPT" --ld-opt="$LD_OPT" --modulesdir=/usr/lib/unit/modules \
      && make -j $NCPU unitd \
      && install -pm755 build/sbin/unitd /usr/sbin/unitd \
      && make clean \
      && ./configure $CONFIGURE_ARGS_MODULES --cc-opt="$CC_OPT" --modulesdir=/usr/lib/unit/debug-modules --debug \
      && ./configure  \
      && make -j $NCPU version \
      && make clean \
      && ./configure $CONFIGURE_ARGS_MODULES --cc-opt="$CC_OPT" --modulesdir=/usr/lib/unit/modules \
      && ./configure  \
      && make -j $NCPU version \
      && cd \
      && rm -rf unit \
      && for f in /usr/sbin/unitd /usr/lib/unit/modules/*.unit.so; do \
          ldd $f | awk '/=>/{print $(NF-1)}' | while read n; do dpkg-query -S $n; done | sed 's/^\([^:]\+\):.*$/\1/' | sort | uniq >> /requirements.apt; \
         done \
      && apt-mark showmanual | xargs apt-mark auto > /dev/null \
      && { [ -z "$savedAptMark" ] || apt-mark manual $savedAptMark; } \
      && /bin/true \
      && mkdir -p /var/lib/unit/ \
      && mkdir /docker-entrypoint.d/ \
      && groupadd --gid 999 unit \
      && useradd \
           --uid 999 \
           --gid unit \
           --no-create-home \
           --home /nonexistent \
           --comment "unit user" \
           --shell /bin/false \
           unit \
      && apt-get update \
      && apt-get --no-install-recommends --no-install-suggests -y install curl $(cat /requirements.apt) \
      && apt-get purge -y --auto-remove \
      && rm -rf /var/lib/apt/lists/* \
      && rm -f /requirements.apt \
      && ln -sf /dev/stdout /var/log/unit.log

  COPY ./modules/nginx-unit-custom-build/docker-entrypoint.sh /usr/local/bin/

  ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]

  SAVE IMAGE --push bindgen-web-unit:latest
