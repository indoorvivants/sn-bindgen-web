FROM keynmol/sn-vcpkg:latest as dev

ARG unit_version=1.31.1
ENV UNIT_VERSION=${unit_version}

# Install all native dependencies
RUN apt-get update && apt-get install -y curl build-essential libpcre2-dev && \
    curl -O https://unit.nginx.org/download/unit-$UNIT_VERSION.tar.gz && tar xzf unit-$UNIT_VERSION.tar.gz && \
    mv unit-$UNIT_VERSION unit && \
    cd unit && \
    ./configure --no-ipv6 --log=/dev/stderr --user=unit --group=unit --statedir=statedir && \
    make build/sbin/unitd && \
    make build/lib/libunit.a && \
    install -p build/lib/libunit.a /usr/local/lib/libunit.a && \
    mv build/sbin/unitd /usr/sbin/unitd && \
    apt install -y lsb-release wget software-properties-common gnupg && \
    # install LLVM and libclang
    curl -Lo llvm.sh https://apt.llvm.org/llvm.sh && \
    chmod +x llvm.sh && \
    ./llvm.sh 17 && \
    apt update && \
    apt install -y libclang-17-dev libz-dev libutf8proc-dev

WORKDIR /workdir

# pre-download SBT
COPY project/build.properties project/
RUN sbt --sbt-create version

# install vcpkg dependencies
COPY vcpkg.json .
ENV VCPKG_FORCE_SYSTEM_BINARIES=1
RUN sn-vcpkg install -v --manifest vcpkg.json

# build app
ARG scalanative_mode=release-fast
ARG scalanative_lto=thin
ENV SCALANATIVE_MODE=${scalanative_mode}
ENV SCALANATIVE_LTO=${scalanative_lto}
ENV CI=true
ENV LLVM_BIN=/usr/lib/llvm-17/bin
COPY project/plugins.sbt project/
COPY project/*.scala project/
COPY build.sbt .
COPY conf.json .
COPY .jvmopts .
COPY modules/bindings/src modules/bindings/src
COPY modules/http-server/src modules/http-server/src
COPY modules/protocols/src modules/protocols/src
COPY modules/frontend/src modules/frontend/src
COPY modules/queue-processor/src modules/queue-processor/src
RUN sbt buildApp

RUN mkdir empty_dir
RUN mkdir empty_tmp_dir && chmod 0777 empty_tmp_dir
RUN groupadd --gid 999 unit && \
    useradd --uid 999 --gid unit --no-create-home --shell /bin/false unit
RUN cat /etc/passwd | grep unit > passwd
RUN cat /etc/group | grep unit > group

RUN chown unit:unit build/web-server
RUN chown unit:unit build/worker
RUN chown unit:unit empty_tmp_dir

RUN ldd build/worker
RUN ldd /usr/lib/llvm-17/bin/clang


FROM ubuntu:focal

WORKDIR /workdir

COPY --from=dev /workdir/build/statedir /workdir/statedir
COPY --from=dev /workdir/build/web-server /workdir/web-server
COPY --from=dev /workdir/build/worker /workdir/worker
COPY --from=dev /workdir/build/static /workdir/static


# unitd dependencies
COPY --from=dev /usr/sbin/unitd /usr/sbin/unitd
COPY --from=dev /workdir/passwd /etc/passwd
COPY --from=dev /workdir/group /etc/group
COPY --from=dev /workdir/empty_dir /usr/local/var/run/
COPY --from=dev /workdir/empty_tmp_dir /tmp/

# needed for fly.io hack to change owner of volume
COPY --from=dev /usr/bin/bash /usr/bin/bash
COPY --from=dev /bin/chown /bin/chown

## x86_64 specific files
COPY --from=dev */lib/x86_64-linux-gnu/libm.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libpcre2-8.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libcrypto.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libssl.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libc.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib64/ld-linux-x86-64.so.* /lib64/

# ## aarch64 speicific files
COPY --from=dev */lib/aarch64-linux-gnu/libm.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libpcre2-8.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libcrypto.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libssl.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libc.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/ld-linux-aarch64.so.* /lib/

# # scala native dependencies

## x86_64 specific files
COPY --from=dev */lib/x86_64-linux-gnu/libstdc++.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libgcc_s.so.* /lib/x86_64-linux-gnu/

# ## aarch64 speicific files
COPY --from=dev */lib/aarch64-linux-gnu/libstdc++.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libgcc_s.so.* /lib/aarch64-linux-gnu/

# LLVM shared libraries
COPY --from=dev */lib/aarch64-linux-gnu/libclang-17.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libclang-cpp.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libLLVM-17.so.1 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libffi.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libedit.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libz.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libutf8proc.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libtinfo.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libxml2.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libbsd.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libicuuc.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/liblzma.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libmd.so.* /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libicudata.so.* /lib/aarch64-linux-gnu/

COPY --from=dev */lib/x86_64-linux-gnu/libclang-17.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libclang-cpp.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libLLVM-17.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libffi.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libedit.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libz.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libutf8proc.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libtinfo.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libxml2.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libbsd.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libicuuc.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/liblzma.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libmd.so.* /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libicudata.so.* /lib/x86_64-linux-gnu/

COPY --from=dev /usr/lib/llvm-17/bin/clang /usr/lib/llvm-17/bin/


ENV WORKER_HOST=http://localhost:8081
ENV DB_PATH=/var/data/bindgen-web/data.db
ENV LLVM_BIN=/usr/lib/llvm-17/bin
ENV TEMP_PATH=/var/data/bindgen-web/tmp

ENTRYPOINT [ "bash", "-c", "chown -R unit:unit /var/data/bindgen-web/ && unitd --no-daemon --log /dev/stdout" ]

