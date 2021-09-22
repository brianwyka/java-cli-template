#!/bin/bash

set -e

#########################################################################
#
# Update the version of picocli
#
# https://github.com/remkop/picocli
#
#########################################################################

# Retrieve Latest Version
VERSION=$(curl -sI https://github.com/remkop/picocli/releases/latest | grep -i location: | awk -F"/" '{ printf "%s", $NF }' | tr -d 'v' | tr -d '\r\n')

# Global Variables
OS=$(uname -s | tr '[:upper:]' '[:lower:]')
DIR="$( cd "$( dirname "$( dirname "${BASH_SOURCE[0]}" )")" && pwd )"
ROOT_DIR="$( cd "$( dirname "$( dirname "$( dirname "${BASH_SOURCE[0]}" )")")" && pwd )"
BASE_URL="https://raw.githubusercontent.com/remkop/picocli"
LICENSE_URL="$BASE_URL/v$VERSION/LICENSE"
LICENSE_FILE_PATH="$DIR/src/main/resources/META-INF/licenses/picocli.txt"
RELATIVE_SOURCE_FILE_PATH="src/main/java/picocli/CommandLine.java"
SOURCE_URL="$BASE_URL/v$VERSION/$RELATIVE_SOURCE_FILE_PATH"
SOURCE_FILE_PATH="$DIR/$RELATIVE_SOURCE_FILE_PATH"

# Download the license and source file
curl -ksf "$LICENSE_URL" > "$LICENSE_FILE_PATH"
curl -ksf "$SOURCE_URL" > "$SOURCE_FILE_PATH"

if [[ "$OS" == "linux" ]]
then

  # Add some warning suppression to the java source file
  sed -i 's/public\sclass\sCommandLine/@SuppressWarnings({"rawtypes", "deprecation" })\npublic class CommandLine/g' "$SOURCE_FILE_PATH"

  # Replace the version in build files
   sed -i "s/<picocli.version>[-[:alnum:]./]\{1,\}<\/picocli.version>/<picocli.version>$VERSION<\/picocli.version>/" "$DIR/pom.xml"
   sed -i "/picocliVersion( )?=( )?/ s/=.*/= $VERSION/" gradle.properties

   # Remove TODOs so not highlighted in editor
   sed -i 's/TODO/TIDO/g' "$SOURCE_FILE_PATH"

elif [[ "$OS" == "darwin" ]]
then

  echo "TODO: mac"

else
  die
fi

echo "Picocli updated to version: $VERSION"