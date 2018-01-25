#!/bin/bash
# 10,000 string test
echo "Running Test2 $1"

executable=$1
test_param_file='Test2.txt'


#java -jar $executable < $test_param_file | grep "Group 2"
java -jar $executable < $test_param_file
if [[ "$?" -eq "0" ]]; then 
	echo "success"
else
	echo "failure"
fi
