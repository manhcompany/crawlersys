#!/usr/bin/env bash
source conf/service.config
export DATA_PATH=$DATA_PATH
JAVA_HOME=/home/ubuntu/workspace/jre1.8.0_121
$JAVA_HOME/bin/java -cp crawler-1.0.jar reishi.deamons.Write2FileDeamon