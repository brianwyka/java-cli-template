#!/bin/bash

# First check for native image
if [ -f target/cli ];
then
  ./target/cli "$@"
  exit 0
fi

# Then check for executable jar
if compgen -G "target/cli-*-shaded.jar" > /dev/null;
then
  java -jar target/cli-*-shaded.jar "$@"
  exit 0
fi

>&2 echo "Please run a build first..."
exit 1