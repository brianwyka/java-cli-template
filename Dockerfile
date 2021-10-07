# Stage 0: Build the Native Image
FROM ghcr.io/graalvm/graalvm-ce:java11-21.2.0 AS builder

# Install native-image tooling
RUN gu install native-image

# Add the built JAR
ARG JAR="target/java-cli-template-*.jar"
#ARG JAR="build/libs/java-cli-template-*.jar"
ADD ${JAR} /build/app.jar

WORKDIR /build

RUN native-image -jar app.jar --allow-incomplete-classpath --static

# Stage 1: Final Image
FROM scratch

COPY --from=builder /build/app /opt/app

ENTRYPOINT ["/opt/app"]