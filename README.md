# CLI Application

## Prerequisites
- GraalVM CE
- GraalVM CE `native-image`
- `zlib`

### Installation Instructions
- https://www.graalvm.org/docs/getting-started/linux/
- https://www.graalvm.org/reference-manual/native-image/#install-native-image
- https://www.graalvm.org/reference-manual/ruby/Installingzlib/

## Compile and Run Tests

### Without Native Image
```sh
./mvnw clean install
```

### With Native Image
```sh
./mvnw clean install -D native.image
```

## Run Executable JAR
```sh
./run.sh Brian
```
