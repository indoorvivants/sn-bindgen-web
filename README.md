## Web app to generate Scala 3 Native bindings for C

Try it on **https://sn-bindgen-web.indoorvivants.com**

<img width="2784" height="1834" alt="CleanShot 2026-06-02 at 10 16 58@2x" src="https://github.com/user-attachments/assets/1d929536-9c94-46e0-a0d6-183cb1c4edbc" />

<!--toc:start-->
- [Web app to generate Scala 3 Native bindings for C](#web-app-to-generate-scala-3-native-bindings-for-c)
- [Introduction](#introduction)
- [Development](#development)
  - [Architecture](#architecture)
  - [External dependencies](#external-dependencies)
  - [Libraries](#libraries)
  - [Working on Backend](#working-on-backend)
  - [Working on Frontend](#working-on-frontend)
  - [Building docker containers](#building-docker-containers)
<!--toc:end-->

## Introduction

This web application is both a useful demonstrator for [sn-bindgen](https://sn-bindgen.indoorvivants.com/), a test bed for the Typelevel stack on Scala Native 0.5, and an experimentation ground for structuring full stack applications in Scala Native.

Quickest way to run it locally is to use Docker Compose: `$ docker compose up`, then go to http://localhost:8080.

It uses images published to [GHA registry](https://github.com/indoorvivants/sn-bindgen-web/packages) - those images are published on every commit to main.

## Development

Most of the commands mentioned here are intended to be run in a single SBT shell – start it once with `sbt` and put commands in there. Do not run commands as individual sbt invocations, life is too short for that.

Shell commands are prefixed with `$`

### Architecture

1. `web` – processes incoming HTTP requests from frontend, and internally communicates with the worker process, scheduling and retrieving bindings:
  - Code is in [./modules/http-server](./modules/http-server)
  - SBT project is `httpServer`
  - Useful SBT commands: `httpServer/buildBinaryDebug` to build the binary, or `httpServer/reStart` to start it with default port and worker settings.

2. `worker` – exposes an internal HTTP API, and constantly polls the database table for unprocessed bindings. Worker is horizontally scalable and different instances will steal work that has become stale. 
  - Code is in [./modules/queue-processor](./modules/queue-processor)
  - SBT project is `queueProcessor`
  - Useful SBT commands: `queueProcessor/buildBinaryDebug` to build the binary, or `queueProcessor/reStart` to start it with default port and postgres settings

3. `frontend` - a single-page application written using Scala.js.
  - Code is in [./modules/frontend/](./modules/frontend)
  - SBT project is `frontend`
  - Useful SBT commands: `frontend/fastLinkJS` (to produce unoptimised bundle, run it on a loop with `~` during development), `buildWebapp` - builds a fully static assets bundle in `out/release/frontend`, `frontend/reStart` will start Vite development server in the background.

Both the external and internal APIs are generated using [Smithy4s](https://disneystreaming.github.io/smithy4s/).

- Specs are in [./modules/protocols/src/main/smithy](./modules/protocols/src/main/smithy)
- SBT projects are `protocolsJS` and `protocolsNative`, to serve the frontend and backend respectively.

The bindings are stored in a Postgres database, with schema broken into [individual migrations](./modules/queue-processor/src/main/resources/db/migration). 

### External dependencies

1. LLVM - it's a runtime dependency of sn-bindgen, and therefore needs to be installed on your machine if you want to run this project
2. NPM & Vite - used for live-reload and bundling of the frontend
3. Postgres – when running the services directly from sbt build, Postgres is assumed to be running on localhost:5432, with `postgres` user and empty password. Default database name is assumed to be `sn_bindgen_web` (it will be auto-migrated on startup). See `PgCredentials.scala` for configuration env variables.
4. `s2n` - required by http4s, `zstd` - required for in-database code compression. Both of these are installed using bootstrapped `vcpkg` - usually you don't need to do anything.
5. NGINX (only in docker) - serves the static frontend bundle and proxies api requests to web frontend API


### Libraries

This app is a testing bed for [Cats Effect](https://typelevel.org/cats-effect/) ecosystem running on [Scala Native](https://scala-native.org). The HTTP layer is provided by [http4s](https://http4s.org/), database access is via [Skunk](https://typelevel.org/skunk) with [Dumbo](https://github.com/rolang/dumbo) providing migrations, background processing is done using [fs2](https://fs2.io/), the API layer is generated using [Smithy4s](https://disneystreaming.github.io/smithy4s/), and we use [Scribe](https://github.com/outr/scribe) for logging.

Frontend is entirely [Scala.js](https://scala-js.org), we use Smithy4s definitions with a [smithy4s-fetch](https://github.com/neandertech/smithy4s-fetch) client based on browser [Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) – this provides a ~40% reduction in the frontend bundle size. Main UI library is [Laminar](https://laminar.dev/), with [Waypoint](https://github.com/raquo/waypoint) providing the routing for [SPA](https://en.wikipedia.org/wiki/Single-page_application).

The bindings are generated using [sn-bindgen](https://github.com/indoorvivants/sn-bindgen).

### Working on Backend

1. Install LLVM: https://releases.llvm.org/
2. Set `LLVM_BIN` env variable to the location of `bin` folder in LLVM installation
    - e.g. `/opt/homebrew/opt/llvm@19/bin` on MacOS with LLVM 19 installed via Homebrew
    - e.g. `/usr/lib/llvm-19/bin` on Ubuntu with LLVM 19 installed via apt 
3. Install native dependencies by running `vcpkgInstall` - this will install [vcpkg](https://vcpkg.io/) and then libraries used by the backend
4. Run `httpServer/reStart` - (re)starts the web frontend API in the background
5. Run `queueProcessor/reStart` - (re)starts the worker API in the background
6. The web frontend API will be available at http://localhost:8080/api, and the internal worker API will be available on http://localhost:8081

### Working on Frontend

The frontend module is set up using [Vite.js](https://vitejs.dev/).
It's using [Scala.js](https://www.scala-js.org/) and [Laminar](https://laminar.dev/).

- `$ cd modules/frontend && npm install`

- Run the backend (see above), it has to be running on port 8080 (default) 
  as Vite is configured to proxy the `/api/*` requests to `http://localhost:8080/api/*`. If you used the `httpServer/reStart` SBT command, then it's configured correctly already.

- Run Vite dev server with `frontend/reStart`

- Run `~frontend/fastLinkJS` - this continuously rebuilds the Scala.js frontend

- Open http://localhost:5173 and you can now edit frontend without restarting the backend, with live reload

### Building docker containers

- Server (frontend and public API): 
    
    `$ docker build . -t sn-bindgen-web-server -f Server.Dockerfile`

- Worker (job processing): 

    `$ docker build . -t sn-bindgen-web-worker -f Worker.Dockerfile`

The docker containers are designed to be entirely self-contained, and will install all dependencies from scratch – so it will take a long time. It's designed in a layered fashion to ensure caching of intermediate builds but it can only go so far.

We also publish the Docker containers built on CI to GHA registry: https://github.com/indoorvivants/sn-bindgen-web/packages
