#!/bin/bash

BUILD_DIRECTORY="target"
CLI_NAME=$(./mvnw help:evaluate -D expression=cli.name -q -D forceStdout | tail -1 | tr -d '\r\n')

# First check for native image
if [ -f "$BUILD_DIRECTORY/$CLI_NAME" ];
then
  ./$BUILD_DIRECTORY/"$CLI_NAME" "$@"
  exit 0
fi

# Then check for executable jar
if compgen -G "$BUILD_DIRECTORY/*-shaded.jar" > /dev/null;
then
  java -jar "$BUILD_DIRECTORY"/*-shaded.jar "$@"
  exit 0
fi

>&2 echo "Please run a build first..."
exit 1