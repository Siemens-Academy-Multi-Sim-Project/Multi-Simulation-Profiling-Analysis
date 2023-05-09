#!/bin/sh
# sudo ./gradlew clean build -x test
# apt-get update && apt-get install -y --no-install-recommends \
#   && apt-get install -y maildev 
cp build/libs/Multi-Simulation-Profiling-Analysis-0.0.1-SNAPSHOT.war backend.war
source ~/.bashrc
java17 -jar backend.war
nohup maildev