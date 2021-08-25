#!/bin/bash

CLI_NAME=$(./mvnw help:evaluate -Dexpression=cli.name -q -D forceStdout | tail -1 | tr -d '\r\n')

# First check for native image
if [ -f "target/$CLI_NAME" ];
then
  ./target/"$CLI_NAME" "$@"
  exit 0
fi

# Then check for executable jar
if compgen -G "target/*-shaded.jar" > /dev/null;
then
  java -jar target/*-shaded.jar "$@"
  exit 0
fi

>&2 echo "Please run a build first..."
exit 1