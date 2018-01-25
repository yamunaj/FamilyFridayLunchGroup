#!/bin/bash

function RunTest()
{
	executable=$1
	test_name=$2

	echo "RunTest($executable, $test_name)"
	cp -Rfa $executable ./$test_name/
	pushd ./$test_name/

	rm EmployeeTable

	if [[ "$test_name" == 'Test6' ]]; then
		cp ../Test5/EmployeeTable .
	fi
	sh $test_name.sh $executable
	rm $executable
	popd
}

executable=$1

if [[ ! -f $executable ]]; then
	echo "$executable not found. Skipping runAllTests.sh"
fi

RunTest $executable Test1
RunTest $executable Test2
RunTest $executable Test3
RunTest $executable Test4
RunTest $executable Test5
RunTest $executable Test6
RunTest $executable Test7
