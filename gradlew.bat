@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%" == "Windows_NT" setlocal

set DIR_CURRENT=%~dp0
if "%DIR_CURRENT%" == "" set DIR_CURRENT=.
set "DIR_CURRENT=%DIR_CURRENT:\=/%"

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME (
    set "JAVA_EXE=%JAVA_HOME%/bin/java.exe"
) else (
    set "JAVA_EXE=java.exe"
)

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=gradlew" -classpath "%DIR_CURRENT%/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*

endlocal