#!/bin/bash
if [ ! "$1" ];then
        URL=http://localhost:8080
else
        URL=$1
fi

curl -X POST $URL/shutdown
