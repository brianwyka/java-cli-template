# Datadog Interview

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
./run.sh monitor --help
```

### Monitor the HTTP access logs

The below example monitors the file `src/test/resources/http_access_log.csv` and configures alerts to be 
triggered if sections hits exceed `120`

.Read from File
```sh
./run.sh monitor -t 120 src/test/resources/http_access_log.csv
```

.Read from Standard Input
```sh
cat src/test/resources/http_access_log.csv | ./run.sh monitor -t 120 -
```

## Synopsis

### Total Time Spent
- 3 hours 30 mins (not including some external interruptions).
- Spent a little extra time troubleshooting IDE issues in addition to native image compilation

### With More Time I would have...

- refactored the code to be a bit more modular, and testable 
- improved the test coverage to be between 90% and 100%
- expanded upon the documentation and Javadocs
- run some benchmarks and larger file scenarios to gauge performance under load