## Web app to generate Scala 3 Native bindings for C

_Warning: this is currently not working and really should be a private repo, but I've run out of private GHA minutes so... here we are_

<!--toc:start-->
- [Web app to generate Scala 3 Native bindings for C](#web-app-to-generate-scala-3-native-bindings-for-c)
  - [Quick start (Docker)](#quick-start-docker)
  - [Local development (backend)](#local-development-backend)
  - [Local development (frontend)](#local-development-frontend)
<!--toc:end-->

![2023-05-29 12 01 15](https://github.com/indoorvivants/sn-bindgen-web/assets/1052965/85d0144c-f431-49e3-a45c-b644fc642cf7)

### Quick start (Docker)

**Warning: this will take some time, as we need to build NGINX Unit from scratch, and install LLVM. Sit back, have a beverage**

- Build a docker container

    Release version (slow):

    ```
    $ docker build . --build-arg scalanative_mode=release-fast --build-arg scalanative_lto=none -t sn-bindgen-web
    ```

    Debug version (slightly faster):

    ```
    $ docker build . --build-arg scalanative_mode=debug --build-arg scalanative_lto=none -t sn-bindgen-web
    ```
- Run it 

    ```
    docker run -p 9999:9999 sn-bindgen-web:latest
    ```
- Open `http://localhost:9999` and enjoy.

### Local development (backend)

1. Install NGINX Unit: https://unit.nginx.org/installation/
2. Install LLVM: https://releases.llvm.org/
3. Set `LLVM_BIN` env variable to the location of `bin` folder in LLVM installation
4. Run `sbt devServer/run`

   **Warning: first run will be very slow. Subsequent ones will be somewhat slow.**

   Alternatively, you can keep launch SBT and run `devServer/reStart` in there - this 
   will make sure that your SBT shell remains usable, and the server will be run in the background.

5. Open `http://localhost:9999` and enjoy.

### Local development (frontend)

The frontend module is set up using [Vite.js](https://vitejs.dev/).

- `cd modules/frontend`
- `npm install`
- Run the backend (see above), it has to be running on port 9999 (default) 
  as Vite is configured to proxy the `/api/*` requests to `http://localhost:9999/api/*`
- In a separate SBT shell, run `~frontend/fastLinkJS` - this continuously rebuilds the Scala.js frontend
- `npm run dev` - will run the Vite server 
- Open http://localhost:5173 and you can now edit frontend without restarting the backend, with live reload

