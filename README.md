## Web app to generate Scala 3 Native bindings for C

<!--toc:start-->
- [Web app to generate Scala 3 Native bindings for C](#web-app-to-generate-scala-3-native-bindings-for-c)
  - [Quick start](#quick-start)
  - [Local development](#local-development)
<!--toc:end-->

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

