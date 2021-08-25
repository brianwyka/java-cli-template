# Java Maven CLI Template

This is a template repository for building Picolcli CLI applications with Java built with Maven.

## Compile and Run Tests

### Without Native Image
```sh
./mvnw clean install
```

### With Native Image
```sh
./mvnw clean install -D native.image
```

#### Native Image Prerequisites
- GraalVM CE
- GraalVM CE `native-image`
- `zlib`

##### GraalVM and Native Image Installation Instructions
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