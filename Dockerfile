FROM keynmol/sn-vcpkg:latest as dev

# Install NGINX Unit
RUN apt-get update && \
    apt-get install -y curl

ARG unit_version=1.31.1
ENV UNIT_VERSION=${unit_version}

# Compile minimal NGINX Unit
RUN apt-get update && apt-get install -y curl build-essential
RUN curl -O https://unit.nginx.org/download/unit-$UNIT_VERSION.tar.gz && tar xzf unit-$UNIT_VERSION.tar.gz
RUN mv unit-$UNIT_VERSION unit
RUN cd unit && \
    ./configure --no-ipv6 --no-regex --log=/dev/stderr --user=unit --group=unit --statedir=statedir && \
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

COPY . .

ARG scalanative_mode=release-fast
ARG scalanative_lto=thin

ENV SCALANATIVE_MODE=${scalanative_mode}
ENV SCALANATIVE_LTO=${scalanative_lto}
ENV CI=true

RUN sbt clean buildApp

RUN mkdir empty_dir
RUN groupadd --gid 999 unit && \
    useradd --uid 999 --gid unit --no-create-home --shell /bin/false unit
RUN cat /etc/passwd | grep unit > passwd
RUN cat /etc/group | grep unit > group

FROM scratch

WORKDIR /workdir

COPY --from=dev /workdir/build/statedir /workdir/statedir
COPY --from=dev /workdir/build/server /workdir/server
COPY --from=dev /workdir/build/web-server /workdir/web-server
COPY --from=dev /workdir/build/worker /workdir/worker
COPY --from=dev /workdir/build/static /workdir/static

# unitd dependencies
COPY --from=dev /usr/sbin/unitd /usr/sbin/unitd
COPY --from=dev /workdir/passwd /etc/passwd
COPY --from=dev /workdir/group /etc/group
COPY --from=dev /workdir/empty_dir /var/run

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

ENV WORKER_HOST=http://localhost:8888
ENV DB_PATH=/var/data/bindgen-web/data.db

ENTRYPOINT [ "unitd", "--statedir", "statedir", "--log", "/dev/stdout", "--no-daemon" ]

