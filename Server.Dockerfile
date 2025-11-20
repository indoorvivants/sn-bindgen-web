FROM keynmol/sn-vcpkg:latest as dev

# Install all native dependencies
RUN apt-get update && apt-get install -y curl build-essential libpcre2-dev && \
    apt install -y lsb-release wget software-properties-common gnupg

WORKDIR /workdir

# pre-download SBT
COPY project/build.properties project/
RUN sbt --sbt-create version

# install vcpkg dependencies
RUN sn-vcpkg bootstrap
COPY vcpkg.json .
ENV VCPKG_FORCE_SYSTEM_BINARIES=1
RUN sn-vcpkg install -v --manifest vcpkg.json

COPY publish-forks.sh .forks/publish-forks.sh
ENV LC_CTYPE=en_US.UTF-8
ENV LANG=en_US.UTF-8
RUN cd .forks && ./publish-forks.sh

# build app
ENV CI=true
ENV LLVM_BIN=/usr/lib/llvm-17/bin
RUN if [ "$(uname -m)" = "x86_64" ]; then \
    curl -Lo node-install.tar.xz https://nodejs.org/dist/v22.18.0/node-v22.18.0-linux-x64.tar.xz; \
    else \
    curl -Lo node-install.tar.xz https://nodejs.org/dist/v22.18.0/node-v22.18.0-linux-arm64.tar.xz; \
    fi && \
    tar -xf node-install.tar.xz && rm *.tar.xz && mv node-v22* node-install
ENV PATH /workdir/node-install/bin:$PATH
COPY project/plugins.sbt project/
COPY project/*.scala project/
COPY build.sbt .
COPY .jvmopts .
COPY modules/bindings/src modules/bindings/src
COPY modules/http-server/src modules/http-server/src
COPY modules/protocols/src modules/protocols/src
COPY modules/frontend modules/frontend
RUN curl -Lo llvm.sh https://apt.llvm.org/llvm.sh && \
    chmod +x llvm.sh && \
    ./llvm.sh 17
ENV LLVM_BIN=/usr/lib/llvm-17/bin
RUN sbt httpServer/buildBinaryRelease buildWebappRelease

FROM ubuntu:jammy

WORKDIR /workdir

RUN apt update && apt install -y nginx

COPY --from=dev /workdir/out/release/bindgen-web-http-server /app/web
COPY --from=dev /workdir/out/release/frontend /app/static/

COPY modules/nginx/nginx.conf /etc/nginx/sites-available/default
COPY modules/nginx/docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh

EXPOSE 80

CMD [ "/usr/local/bin/docker-entrypoint.sh" ]
