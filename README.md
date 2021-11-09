# Java CLI Template

![Maven CI](https://github.com/brianwyka/java-cli-template/actions/workflows/maven-ci.yml/badge.svg) 
![Gradle CI](https://github.com/brianwyka/java-cli-template/actions/workflows/gradle-ci.yml/badge.svg)

This is a template repository for building CLI applications with Java.  It leverages
[Picocli](https://picocli.info/) for bootstrapping the CLI execution and 
[GraalVM native-image](https://www.graalvm.org/reference-manual/native-image/) for building 
a native image executable.

* **JDK Version**: `17`
* **GraalVM Version**: `21.3.0` 

## Build

### Maven
For the `native-image` build, see instructions at bottom for tooling pre-requisites.  Your 
`JAVA_HOME` will need to be set with `GraalVM` installation.

```sh
./mvnw clean install # Build executable JAR
./mvnw clean install -D nativeImage # Build native image
```

### Gradle
```sh
./gradlew clean build # Build executable JAR
./gradlew clean nativeImage # Build native image
```

### Docker
An external build is first required since the `Dockerfile` needs to `ADD` the executable JAR.  
By default, the JAR used is from maven build.

```sh
docker build -t java-cli-template .
```

To use the gradle build output:
```sh
docker build -t java-cli-template --build-arg "JAR=build/libs/java-cli-template-*.jar" .
```

## Run

### Maven

#### Executable JAR
```sh
java -jar target/java-cli-template-*.jar --help
java -jar target/java-cli-template-*.jar hello-world
java -jar target/java-cli-template-*.jar hello-world Brian
echo "Brian" | java -jar target/java-cli-template-*.jar hello-world -
```

#### Native Image
```sh
./target/app --help
./target/app hello-world
./target/app hello-world Brian
echo "Brian" | ./target/app hello-world -
```

### Gradle

#### Executable JAR
```sh
java -jar build/libs/java-cli-template-*.jar --help
java -jar build/libs/java-cli-template-*.jar hello-world
java -jar build/libs/java-cli-template-*.jar hello-world Brian
echo "Brian" | java -jar build/libs/java-cli-template-*.jar hello-world -
```

#### Native Image
```sh
./build/graal/app --help
./build/graal/app hello-world
./build/graal/app hello-world Brian
echo "Brian" | ./build/graal/app hello-world -
```

### Docker
```sh
docker run java-cli-template --help
docker run java-cli-template hello-world
docker run java-cli-template hello-world Brian
echo "Brian" | docker run -i java-cli-template hello-world -
```

## Reflection in Native Image

Runtime reflection in a native image is tricky.  GraalVM can detect basic usage of class loading with reflection, 
however it cannot determine classes loaded dynamically. To allow it to work, some configuration needs to be done.

See `Reflect.java` for how the class is loaded "dynamically".

### This Will Work
```sh
./app reflect java.lang.String
```
This is because it has been configured in `reflect-config.json`.

### This Will Not Work
```sh
./app reflect java.util.List
```
This is because it is not included in `reflect-config.json`

## Native Image Prerequisites When Using Maven
The maven build requires that GraalVM and `native-image` tooling already be available on the machine.

- GraalVM CE
- GraalVM CE `native-image`
- `zlib` / `xcode`

### GraalVM Installation

```sh
./install-graalvm.sh
```

If you are using Gradle or Docker, you do not need to perform this installation.

#### GraalVM and Native Image Installation Instructions
- Getting Started
  - https://www.graalvm.org/docs/getting-started/macos/
  - https://www.graalvm.org/docs/getting-started/linux/
- https://www.graalvm.org/reference-manual/native-image/#install-native-image
- https://www.graalvm.org/reference-manual/ruby/Installingzlib/
