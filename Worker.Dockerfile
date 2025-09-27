FROM keynmol/sn-vcpkg:latest as dev

# Install all native dependencies
RUN apt-get update && apt-get install -y curl build-essential libpcre2-dev && \
    apt install -y lsb-release wget software-properties-common gnupg && \
    # install LLVM and libclang
    curl -Lo llvm.sh https://apt.llvm.org/llvm.sh && \
    chmod +x llvm.sh && \
    ./llvm.sh 17 && \
    apt update && \
    apt install -y libclang-17-dev libz-dev

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
COPY project/plugins.sbt project/
COPY project/*.scala project/
COPY build.sbt .
COPY .jvmopts .
COPY modules/bindings/src modules/bindings/src
COPY modules/http-server/src modules/http-server/src
COPY modules/queue-processor/src modules/queue-processor/src
COPY modules/protocols/src modules/protocols/src
RUN sbt queueProcessor/buildBinaryRelease

FROM ubuntu:jammy

WORKDIR /workdir

COPY --from=dev /workdir/out/release/bindgen-web-queue-processor /app/worker

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

ENV LLVM_BIN=/usr/lib/llvm-17/bin

EXPOSE 8081

ENTRYPOINT [ "/app/worker", "--port", "8081", "--host", "0.0.0.0" ]
