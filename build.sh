#!/bin/sh

TOOL="${1:-maven}"
NATIVE_IMAGE="${2:-true}"

if [ "$TOOL" = "maven" ]
then
  if [ "$NATIVE_IMAGE" = "true" ]
  then
    ./mvnw clean install -D nativeImage
  else
    ./mvnw clean install
  fi
elif [ "$TOOL" = "gradle" ]
then
  if [ "$NATIVE_IMAGE" = "true" ]
  then
    ./gradlew clean nativeImage
  else
    ./gradlew clean build
  fi
else
  echo "Unsupported built tool: [$TOOL], only maven and gradle are supported..."
fi