#!/bin/bash
set -e
dir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn () {
    echo "WARNING: $1"
}

error () {
    echo "ERROR: $1"
    exit 1
}

export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export JDK_JAVA_OPTIONS="-Xmx2G"
