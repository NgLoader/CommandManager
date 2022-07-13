#!/usr/bin/env sh

build () {
	echo Building $1 ...
    mkdir $1
    cd $1

    curl -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
    java -jar BuildTools.jar --rev $1 --remapped

    cd ..
}

checkVersion () {
	echo Checking version $1

	if [ ! -d ~/.m2/repository/org/spigotmc/spigot/$1-R0.1-SNAPSHOT ]; then
		build $1
	fi
}

if [ $1 = "java-17" ]; then
	checkVersion 1.18.1
	checkVersion 1.18.2
	checkVersion 1.19
fi