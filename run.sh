#!/bin/bash

# First check for native image
if [ -f target/interview ];
then
  ./target/interview "$@"
  exit 0
fi

# Then check for executable jar
if compgen -G "target/interview-*-shaded.jar" > /dev/null;
then
  java -jar target/interview-*-shaded.jar "$@"
  exit 0
fi

>&2 echo "Please run a build first..."
exit 1