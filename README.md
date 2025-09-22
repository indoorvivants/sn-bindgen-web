## Web app to generate Scala 3 Native bindings for C

Try it on **https://sn-bindgen-web.indoorvivants.com**

<img width="2432" height="1636" alt="CleanShot 2025-09-18 at 12 14 56@2x" src="https://github.com/user-attachments/assets/5b5d56a8-ac76-4ecc-828e-5b7267412e20" />

<!--toc:start-->
- [Web app to generate Scala 3 Native bindings for C](#web-app-to-generate-scala-3-native-bindings-for-c)
- [Development](#development)
  - [Architecture](#architecture)
  - [Libraries](#libraries)
  - [Forks](#forks)
  - [Working on Backend](#working-on-backend)
  - [Working on Frontend](#working-on-frontend)
  - [Working with mprocs](#working-with-mprocs)
  - [Building docker container](#building-docker-container)
<!--toc:end-->

## Development

### Architecture

The entire service is deployed as an [NGINX Unit](https://unit.nginx.org/) application, with two processes (see [conf.json](conf.json) for Unit configuration):

1. `web` – processes incoming HTTP requests from frontend, and internally communicates with the worker process, scheduling and retrieving bindings:
  - Code is in [./modules/http-server](./modules/http-server)
  - SBT project is `httpServer`
  - Useful SBT commands: `httpServer/buildBinaryDebug`

2. `worker` – exposes an internal HTTP API, and constantly polls the database table for unprocessed bindings. Worker is horizontally scalable and different instances will steal work that has become stale. 
  - Code is in [./modules/queue-processor](./modules/queue-processor)
  - SBT project is `queueProcessor`
  - Useful SBT commands: `queueProcessor/buildBinaryDebug`

Both the external and internal APIs are generated using [Smithy4s](https://disneystreaming.github.io/smithy4s/).

- Specs are in [./modules/protocols/src/main/smithy](./modules/protocols/src/main/smithy)
- SBT projects are `protocolsJS` and `protocolsNative`, to serve the frontend and backend respectively.


The frontend is built into an optimised static site and is served by NGINX Unit using the built-in file server.

  - Code is in [./modules/frontend](./modules/frontend)
  - SBT project is `frontend`
  - Useful SBT commands: `frontend/fastLinkJS`, `buildWebapp`

The bindings are stored in a Postgres database, with schema broken into [individual migrations](./modules/queue-processor/src/main/resources/db/migration).

### Libraries

This app is a testing bed for [Cats Effect](https://typelevel.org/cats-effect/) ecosystem running on [Scala Native](https://scala-native.org). The HTTP layer is provided by [http4s](https://http4s.org/), NGINX Unit interaction is provided by [snunit](https://github.com/lolgab/snunit), database access is via [Skunk](https://typelevel.org/skunk) with [Dumbo](https://github.com/rolang/dumbo) providing migrations, background processing is done using [fs2](https://fs2.io/), the API layer is generated using [Smithy4s](https://disneystreaming.github.io/smithy4s/), and we use [Scribe](https://github.com/outr/scribe) for logging.

Frontend is entirely [Scala.js](https://scala-js.org), we use Smithy4s definitions with a [smithy4s-fetch](https://github.com/neandertech/smithy4s-fetch) client based on browser [Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) – this provides a ~40% reduction in the frontend bundle size. Main UI library is [Laminar](https://laminar.dev/), with [Waypoint](https://github.com/raquo/waypoint) providing the routing for [SPA](https://en.wikipedia.org/wiki/Single-page_application).

The bindings are generated using [sn-bindgen](https://github.com/indoorvivants/sn-bindgen).

### Forks

**IMPORTANT**: as of September 18th, the project works with a lot of unpublished versions, so you HAVE TO run the [script](./publish-forks.sh) to publish locally the versions required by this app.

This will gradually become unnecessary as the dependencies get published.


### Working on Backend

**Important – see [Forks](#forks) section above.**

1. Install NGINX Unit: https://unit.nginx.org/installation/
2. Install LLVM: https://releases.llvm.org/
3. Set `LLVM_BIN` env variable to the location of `bin` folder in LLVM installation
    - e.g. `/opt/homebrew/opt/llvm@19/bin` on MacOS with LLVM 19 installed via Homebrew
    - e.g. /usr/lib/llvm-19/bin on Ubuntu with LLVM 19 installed via apt 
4. Install native dependencies by running `sbt vcpkgInstall` - this will install [vcpkg](https://vcpkg.io/) and then libraries used by the backend
5. Run `sbt devServer/reStart`

   **Warning: first run will be very slow. Subsequent ones will be somewhat slow.**

6. The web frontend API will be available at http://localhost:8080/api, and the internal worker API will be available on http://localhost:8081

### Working on Frontend

The frontend module is set up using [Vite.js](https://vitejs.dev/).
It's using [Scala.js](https://www.scala-js.org/) and [Laminar](https://laminar.dev/).

- `cd modules/frontend`
- `npm install`
- Run the backend (see above), it has to be running on port 8080 (default) 
  as Vite is configured to proxy the `/api/*` requests to `http://localhost:8080/api/*`. If you used the `devServer/reStart` SBT command, then it's configured correctly already.
- In a separate SBT shell, run `~frontend/fastLinkJS` - this continuously rebuilds the Scala.js frontend
- `npm run dev` - will run the Vite server 
- Open http://localhost:5173 and you can now edit frontend without restarting the backend, with live reload

### Working with mprocs

If you want, you can run [`mprocs`](https://github.com/pvolok/mprocs) tool in the root of the project and you will get backend and frontend running at the same time with auto-reload.

### Building docker container

`docker build . -t sn-bindgen-web`

The docker container is designed to be entirely self-contained, and will install all dependencies from scratch – so it will take a long time.

It's designed in a layered fashion to ensure caching of intermediate builds, but it still takes a long time.

We also publish the Docker container built on CI to GHA registry: https://github.com/indoorvivants/sn-bindgen-web/pkgs/container/sn-bindgen-web
