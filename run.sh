#!/bin/bash

if [[ -f LunchGroup.jar ]]; then
	java -jar LunchGroup.jar
else
	echo "LunchGroup.jar executable not found"
fi
