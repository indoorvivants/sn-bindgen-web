FROM keynmol/sn-vcpkg:latest as dev

# Install NGINX Unit
RUN apt-get update && \
    apt-get install -y curl

ARG unit_version=1.31.1
ENV UNIT_VERSION=${unit_version}

# Compile minimal NGINX Unit
RUN apt-get update && apt-get install -y curl build-essential libpcre2-dev
RUN curl -O https://unit.nginx.org/download/unit-$UNIT_VERSION.tar.gz && tar xzf unit-$UNIT_VERSION.tar.gz
RUN mv unit-$UNIT_VERSION unit
RUN cd unit && \
    ./configure --no-ipv6 --log=/dev/stderr --user=unit --group=unit --statedir=statedir && \
    make build/sbin/unitd && \
    make build/lib/libunit.a && \
    install -p build/lib/libunit.a /usr/local/lib/libunit.a && \
    mv build/sbin/unitd /usr/sbin/unitd

WORKDIR /workdir

# pre-download SBT
RUN sbt --sbt-create version

COPY vcpkg.json .

ENV VCPKG_FORCE_SYSTEM_BINARIES=1
RUN sn-vcpkg install -v --manifest vcpkg.json


ARG scalanative_mode=release-fast
ARG scalanative_lto=thin

ENV SCALANATIVE_MODE=${scalanative_mode}
ENV SCALANATIVE_LTO=${scalanative_lto}
ENV CI=true
ENV LLVM_BIN=/usr/lib/llvm-14/bin

RUN apt update && apt install -y lsb-release wget software-properties-common gnupg &&\
  # install LLVM and libclang
  curl -Lo llvm.sh https://apt.llvm.org/llvm.sh && \
  chmod +x llvm.sh && \
  ./llvm.sh 14 && \
  apt update && \
  apt install -y libclang-14-dev

COPY . .
RUN sbt clean buildApp

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
RUN ldd /usr/lib/llvm-14/bin/clang


FROM scratch

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
COPY --from=dev /workdir/empty_tmp_dir/ /tmp/

## x86_64 specific files
COPY --from=dev */lib/x86_64-linux-gnu/libm.so.6 /lib/x86_64-linux-gnu/libm.so.6
COPY --from=dev */lib/x86_64-linux-gnu/libpcre2-8.so.0 /lib/x86_64-linux-gnu/libpcre2-8.so.0
COPY --from=dev */lib/x86_64-linux-gnu/libcrypto.so.3 /lib/x86_64-linux-gnu/libcrypto.so.3
COPY --from=dev */lib/x86_64-linux-gnu/libssl.so.3 /lib/x86_64-linux-gnu/libssl.so.3
COPY --from=dev */lib/x86_64-linux-gnu/libc.so.6 /lib/x86_64-linux-gnu/libc.so.6
COPY --from=dev */lib64/ld-linux-x86-64.so.2 /lib64/ld-linux-x86-64.so.2

## aarch64 speicific files
COPY --from=dev */lib/aarch64-linux-gnu/libm.so.6 /lib/aarch64-linux-gnu/libm.so.6
COPY --from=dev */lib/aarch64-linux-gnu/libpcre2-8.so.0 /lib/aarch64-linux-gnu/libpcre2-8.so.0
COPY --from=dev */lib/aarch64-linux-gnu/libcrypto.so.3 /lib/aarch64-linux-gnu/libcrypto.so.3
COPY --from=dev */lib/aarch64-linux-gnu/libssl.so.3 /lib/aarch64-linux-gnu/libssl.so.3
COPY --from=dev */lib/aarch64-linux-gnu/libc.so.6 /lib/aarch64-linux-gnu/libc.so.6
COPY --from=dev */lib/ld-linux-aarch64.so.1 /lib/ld-linux-aarch64.so.1

# scala native dependencies

## x86_64 specific files
COPY --from=dev */lib/x86_64-linux-gnu/libstdc++.so.6 /lib/x86_64-linux-gnu/libstdc++.so.6
COPY --from=dev */lib/x86_64-linux-gnu/libgcc_s.so.1 /lib/x86_64-linux-gnu/libgcc_s.so.1

## aarch64 speicific files
COPY --from=dev */lib/aarch64-linux-gnu/libstdc++.so.6 /lib/aarch64-linux-gnu/libstdc++.so.6
COPY --from=dev */lib/aarch64-linux-gnu/libgcc_s.so.1 /lib/aarch64-linux-gnu/libgcc_s.so.1

# LLVM shared libraries
COPY --from=dev */lib/aarch64-linux-gnu/libclang-14.so.13 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libclang-cpp.so.14 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libLLVM-14.so.1 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libffi.so.8 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libedit.so.2 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libz.so.1 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libtinfo.so.6 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libxml2.so.2 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libbsd.so.0 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libicuuc.so.70 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/liblzma.so.5 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libmd.so.0 /lib/aarch64-linux-gnu/
COPY --from=dev */lib/aarch64-linux-gnu/libicudata.so.70 /lib/aarch64-linux-gnu/

COPY --from=dev */lib/x86_64-linux-gnu/libclang-14.so.13 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libclang-cpp.so.14 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libLLVM-14.so.1 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libffi.so.8 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libedit.so.2 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libz.so.1 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libtinfo.so.6 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libxml2.so.2 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libbsd.so.0 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libicuuc.so.70 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/liblzma.so.5 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libmd.so.0 /lib/x86_64-linux-gnu/
COPY --from=dev */lib/x86_64-linux-gnu/libicudata.so.70 /lib/x86_64-linux-gnu/

COPY --from=dev /usr/lib/llvm-14/bin/ /usr/lib/llvm-14/bin/


ENV WORKER_HOST=http://localhost:8888
ENV DB_PATH=/var/data/bindgen-web/data.db
ENV LLVM_BIN=/usr/lib/llvm-14/bin
ENV TEMP_PATH=/tmp

ENTRYPOINT [ "unitd", "--statedir", "statedir", "--log", "/dev/stdout", "--no-daemon" ]

