# Stage 0: Build the Native Image
FROM ghcr.io/graalvm/graalvm-ce:java17-21.3.0 AS builder

# Install native-image tooling
RUN gu install native-image

# Add the built JAR
ARG JAR="target/java-cli-template-*.jar"
#ARG JAR="build/libs/java-cli-template-*.jar"
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