FROM anapsix/alpine-java:jre8

RUN \
    mkdir /var/deps && \
    cd /var/deps && \
    echo "Downloading swagger-codegen-cli-2.1.3.jar" && \
    curl -L -o swagger-codegen-cli.jar http://search.maven.org/remotecontent?filepath=io/swagger/swagger-codegen-cli/2.1.3/swagger-codegen-cli-2.1.3.jar && \
    echo "Downloading scala-library-2.11.7.jar" && \
    curl -L -o scala-library.jar http://search.maven.org/remotecontent?filepath=org/typelevel/scala-library/2.11.7/scala-library-2.11.7.jar

ADD ./target/scala-2.11/*.jar /var/deps/

ENTRYPOINT ["java", "-cp", "/var/deps/*", "io.swagger.codegen.SwaggerCodegen"]