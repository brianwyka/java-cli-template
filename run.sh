#!/bin/bash

# First check for native image
if [ -f target/app ];
then
  ./target/app "$@"
  exit 0
fi

# Then check for executable jar
if compgen -G "target/java-maven-cli-template-*-shaded.jar" > /dev/null;
then
  java -jar target/java-maven-cli-template-*-shaded.jar "$@"
  exit 0
fi

>&2 echo "Please run a build first..."
exit 1