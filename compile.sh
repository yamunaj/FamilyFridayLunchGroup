#!/bin/bash

if [[ -f LunchGroup.java ]]; then
	javac LunchGroup.java
	if [[ -f LunchGroup.class  ]] && [[ -f SerializeDeserialize.class ]]; then
		if [[ -f Manifest ]]; then
			jar cvfm LunchGroup.jar Manifest *.class
		else
			echo "Manifest file not found"
		fi
	else 
		echo "class file not found"
	fi
else 
	echo "LunchGroup.java not found"
fi

