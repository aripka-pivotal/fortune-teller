#!/bin/bash
COUNTER=0
TOP=$1

if [ ! "$2"];then
	URL=http://localhost:9090
else
	URL=$2
fi

echo $URL

while [ $COUNTER -lt $TOP ]; do
	curl $URL/random
	let COUNTER=COUNTER+1
	sleep .25
done

