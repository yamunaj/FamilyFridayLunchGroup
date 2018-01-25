#Lunch Group Generation

## Steps to Execute
### Execute Program
* A script is provided as a wrapper to invoke the executable
* To compile, run `sh compile.sh`
	- Compilation should not fail
	- If it fails rest of the steps will not execute
* From terminal, run `sh run.sh`

### Run Tests
* To run all tests do `sh runAllTests.sh LunchGroup.jar`


## Assumptions
The git repository is setup such that directories are downloaded in the way it should be structured.

1. The executable will be generated under `<project_dir>/...`
2. Test files and scripts (explained below) needs to be in the same directory where the executable is
3. Scripts assumes you are in bash environment

## Components
1. Java Files
	* `LunchGroup.java`
	* `SerializeDeserialize.java`

2. Executables
	* `LunchGroup.jar`
	* `run` (script)

3. Auto generated Files
	* `EmployeesTable`
		- Serialized File with list of all employees
		- This is where the persistent data is stored

	* `LunchGroup.txt`
		- Output file
		- List of all lunch groups generated

4. Test scripts
	* runAllTests.sh
		- Top level script
		- Invokes the following scripts
			* `Test[1-7]/Test[1-7].sh`
			* `Test[1-7]/Test[1-7].txt`

## Testcases Covered
### Test1
- Tests a basic file input and generation of lunch group from it

### Test2 
- Tests a very large input file

### Test3
- Tests adding new employees

### Test4
- Tests multiple new employees, listing all employees, deleting an employee

### Test5
- Tests no input file, input empty file
- Creation of lunch group when there is only a 1,2,3 users in the company

### Test6
- Tests persistence of data via existence of `EmployeesTable`

### Test7
- Tests wrong input file name or file path

## Environment Used for Testing
- OSX 10.13.2 with default shell
- Java version: 9.0.1