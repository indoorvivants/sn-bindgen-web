## Web app to generate Scala 3 Native bindings for C

<!--toc:start-->
- [Web app to generate Scala 3 Native bindings for C](#web-app-to-generate-scala-3-native-bindings-for-c)
  - [Quick start](#quick-start)
  - [Local development](#local-development)
<!--toc:end-->

_Warning: unfortunately the app doesn't seem to currently work on Linux x86, which is where I want to deploy it on Fly.io. I've only made the repository public to invite some folks to take a look and see what am I missing._

_For context, what doesn't work is the service started at port 9999 - you can verify that by hitting /api/health in the built container. On the other hand, the other service starts fine - you can verify that by hitting /health endpoint on the service responding at :8888_

### Quick start

1. Install [Earthly](https://docs.earthly.dev) 

2. Build a Docker Container
    ```
    earthly +docker
    ```
    **Warning: this will take some time, as we need to build NGINX Unit from scratch, and install LLVM. Sit back, have a beverage**
3. Run it 

    ```
    docker run -p 9999:9999 bindgen-web:latest
    ```
4. Open `http://localhost:9999` and enjoy.

### Local development

1. Install NGINX Unit: https://unit.nginx.org/installation/
2. Install LLVM: https://releases.llvm.org/
3. Set `LLVM_BIN` env variable to the location of `bin` folder in LLVM installation
4. Run `sbt deployLocally`

   **Warning: first run will be very slow. Subsequent ones will be somewhat slow.**

5. Open `http://localhost:9999` and enjoy.

