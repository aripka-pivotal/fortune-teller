#!/bin/bash
if [ ! "$1" ];then
        URL=http://localhost:9090
else
        URL=$1
fi

	curl -X POST $URL/refresh
