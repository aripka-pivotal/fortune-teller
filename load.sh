#!/bin/bash
COUNTER=0
TOP=$1
while [ $COUNTER -lt $TOP ]; do
	curl http://localhost:9090/random
	let COUNTER=COUNTER+1
	sleep .45
done
