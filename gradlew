#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
PRG="$0"
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`/`expr "$link" : '.*/\*\(.*\)$'`
    fi
done
APP_HOME=`dirname "$PRG"`

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Collect all arguments for the java command, following the shell quoting and substitution rules
exec java $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS "-Dorg.gradle.appname=$0" -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" "$@"