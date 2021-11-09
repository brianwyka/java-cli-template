#!/bin/bash

set -e

OS=$(uname -s | tr '[:upper:]' '[:lower:]')

die() {
  echo "$*" 1>&2 ;
  exit 1;
}

DIRECTORY=$(dirname $0)

# Install libz
if [[ "$OS" == "linux" ]]
then
  sudo apt install libz-dev
elif [[ "$OS" == "darwin" ]]
then
  xcode-select --install || true
else
  die "Unsupported operating system: [$OS]"
fi

# Install GraalVM
JAVA_VERSION="17"
GRAALVM_VERSION="21.3.0"
BUILD_NAME="graalvm-ce-java$JAVA_VERSION-$GRAALVM_VERSION"
FILE_NAME="graalvm-ce-java$JAVA_VERSION-$OS-amd64-$GRAALVM_VERSION.tar.gz"

## Create and Move to Setup Directory
mkdir -p "$DIRECTORY"/.setup
cd "$DIRECTORY"/.setup || die "Failed to switch to setup directory"

## Download
if [[ ! -f "$FILE_NAME" ]]
then
  echo "Downloading $FILE_NAME from GraalVM CE Builds..."
  curl -ksL -o "$FILE_NAME" https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-"$GRAALVM_VERSION"/"$FILE_NAME" \
      || die "Failed to download GraalVM"
else
  echo "Skipping download"
fi

## Unzip
if [[ ! -d "$BUILD_NAME" ]]
then
  echo "Unzipping $FILE_NAME ..."
  tar -xzf "$FILE_NAME" || die "Failed to unzip GraalVM tarball"
else
  echo "Skipping unzip"
fi

## Install
if [[ "$OS" == "linux" ]]
then
  if [[ ! -d "/opt/graalvm/$BUILD_NAME" ]]
  then
    echo "Installing $FILE_NAME to /opt/graalvm ..."
    sudo mkdir -p /opt/graalvm
    sudo mv "$BUILD_NAME" /opt/graalvm || die "Failed to move GraalVM to /opt/graalvm"
  else
    echo "Skipping GraalVM install"
  fi
elif [[ "$OS" == "darwin" ]]
then
  if [[ ! -d "/Library/Java/JavaVirtualMachines/$BUILD_NAME" ]]
    then
      echo "Installing $FILE_NAME to /Library/Java/JavaVirtualMachines ..."
      sudo mkdir -p /Library/Java/JavaVirtualMachines
      sudo mv "$BUILD_NAME" /Library/Java/JavaVirtualMachines || die "Failed to move GraalVM to /Library/Java/JavaVirtualMachines"
    else
      echo "Skipping GraalVM install"
    fi
fi

# TODO: install native-image

## Show User Further Instructions
echo "Please complete the following manual tasks:"
if [[ "$OS" == "linux" ]]
then
  echo " > Set JAVA_HOME environment variable to \"/opt/graalvm/$BUILD_NAME\""
elif [[ "$OS" == "darwin" ]]
then
  echo " > Set JAVA_HOME environment variable to \"/Library/Java/JavaVirtualMachines/$BUILD_NAME\""
fi
echo " > Add \"\$JAVA_HOME/bin\" to your \$PATH variable if not already added"