# swagger-codegen
Dockerized swagger-codegen with extra targets

# Build docker image
    sbt buildDocker
    
# Generate TypeScript Client
  The client is backed by [superagent](https://github.com/visionmedia/superagent), hence it works on both browsers and node

    docker run -it --rm -v ${PWD}:/var/app -w /var/app nktpro/swagger-codegen generate -i test.json -l nktpro.SwaggerTypeScriptClientCodegen -o temp

