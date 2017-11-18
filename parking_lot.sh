#!/bin/bash 
if [ -n "$1" ]
 then
    echo "Processing file $1"
    java TestLoop $1
 else
  echo "Please type command to execute (exit to quit)"
  read INPUT_COMMAND
  java TestLoop $INPUT_COMMAND
  echo "You typed: $INPUT_COMMAND"
fi 
