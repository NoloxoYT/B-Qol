@rem
@rem Copyright (c) 2014-2024 Gradle, Inc. All rights reserved.
@rem
@rem This software is licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem     http://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.

setlocal enabledelayedexpansion

set GRADLE_USER_HOME=%USERPROFILE%\.gradle
set GRADLE_OPTS=%GRADLE_OPTS% "-Dorg.gradle.jvmargs=-Xmx2G"

if exist "%GRADLE_USER_HOME%\wrapper\dists\gradle-9.2.0-bin\*" (
    set GRADLE_HOME=%GRADLE_USER_HOME%\wrapper\dists\gradle-9.2.0-bin
) else (
    set GRADLE_HOME=%GRADLE_USER_HOME%\wrapper\dists\gradle-9.2.0-bin
)

java -Dorg.gradle.appname=gradlew -classpath "%GRADLE_HOME%\lib\gradle-launcher-9.2.0.jar" org.gradle.launcher.GradleMain %*
