# Java CLI Template

![Maven CI](https://github.com/brianwyka/java-cli-template/actions/workflows/maven-ci.yml/badge.svg) 
![Gradle CI](https://github.com/brianwyka/java-cli-template/actions/workflows/gradle-ci.yml/badge.svg)

This is a template repository for building CLI applications with Java.  It leverages Picocli for 
bootstrapping the CLI execution.

**Java Version**: 11

## Compile and Run Tests

### Without Native Image

#### Maven
```sh
./mvnw clean install
```

#### Gradle
```sh
./gradlew clean build
```

### With Native Image

#### Maven
```sh
./mvnw clean install -D native.image
```

#### Gradle
```sh
./gradlew clean build nativeImage
```

#### Native Image Prerequisites
- GraalVM CE
- GraalVM CE `native-image`
- `zlib` / `xcode`

##### GraalVM Installation
Maven will require that GraalVM be installed separately.

```sh
./install-graalvm.sh
```

If you are using Gradle, you do not need to perform this installation.

##### GraalVM and Native Image Installation Instructions
- Getting Started
  - https://www.graalvm.org/docs/getting-started/macos/
  - https://www.graalvm.org/docs/getting-started/linux/
- https://www.graalvm.org/reference-manual/native-image/#install-native-image
- https://www.graalvm.org/reference-manual/ruby/Installingzlib/

## Run The Application

### Built With Maven

#### Executable JAR
```sh
java -jar target/*-shaded.jar --help
java -jar target/*-shaded.jar hello-world
java -jar target/*-shaded.jar hello-world Brian
```

#### Native Image
```sh
./target/app --help
./target/app hello-world
./target/app hello-world Brian
```

### Built With Gradle

#### Executable JAR
```sh
java -jar build/libs/java-cli-template-*.jar --help
java -jar build/libs/java-cli-template-*.jar hello-world
java -jar build/libs/java-cli-template-*.jar hello-world Brian
```

#### Native Image
```sh
./build/graal/app --help
./build/graal/app hello-world
./build/graal/app hello-world Brian
```
