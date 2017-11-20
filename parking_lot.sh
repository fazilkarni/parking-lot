#!/bin/bash 
mvn install
if [ -n "$1" ]
 then
    echo "Processing file $1"
    java -cp parking-lot-1.0.jar com.gojek.parking.client.CommandExecutor $1
 else
  #echo "Please type command to execute (exit to quit)"
  #read INPUT_COMMAND
  java -cp parking-lot-1.0.jar com.gojek.parking.client.CommandExecutor
  echo "You typed: $INPUT_COMMAND"
fi 
