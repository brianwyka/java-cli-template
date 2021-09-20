# Java CLI Template

This is a template repository for building CLI applications with Java.  It leverages Picocli for 
bootstrapping the CLI execution.

## Compile and Run Tests

### Without Native Image

#### Maven
```sh
./mvnw clean install
```

#### Gradle
```sh
./gradlew clean build # TODO
```

### With Native Image

#### Maven
```sh
./mvnw clean install -D native.image
```

#### Gradle
```sh
./gradlew clean build -D native.image # TODO
```

#### Native Image Prerequisites
- GraalVM CE
- GraalVM CE `native-image`
- `zlib` / `xcode`

##### Auto Setup
```sh
./setup.sh
```

##### GraalVM and Native Image Installation Instructions
- Getting Started
  - https://www.graalvm.org/docs/getting-started/macos/
  - https://www.graalvm.org/docs/getting-started/linux/
- https://www.graalvm.org/reference-manual/native-image/#install-native-image
- https://www.graalvm.org/reference-manual/ruby/Installingzlib/

## Run The Application

### Print Help and usage
```sh
./run.sh --help
./run.sh hello-world --help
```
### Usage

Hello World!
```sh
./run.sh hello-world
```

Hello {name}!
```sh
./run.sh hello-world Brian
```