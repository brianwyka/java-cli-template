# Stage 0: Build the Native Image
FROM ghcr.io/graalvm/native-image:ol8-java17-22.0.0.2-b2 AS builder

# JAR (Maven)
ARG JAR="target/java-cli-template-*.jar"

# JAR (Gradle)
#ARG JAR="build/libs/java-cli-template-*.jar"

# Add the built JAR
ADD ${JAR} /build/app.jar

# Setting up working directory
WORKDIR /build

# Build the native image
RUN native-image -jar app.jar --allow-incomplete-classpath --static

# Stage 1: Final Image
FROM scratch

# Copy native image from builder
COPY --from=builder /build/app /opt/app

# Setup cli entrypoint
ENTRYPOINT ["/opt/app"]