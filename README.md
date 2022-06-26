## --------------------------------------------------------------------------------------
# REQUIREMENTS
## --------------------------------------------------------------------------------------
# Virgin Atlantic ~ Flight Information Display
## Rules
You can write the application in any language you see fit, bearing in mind that its sole intention is to showcase your
skills for the applied-for position. 

In addition you should adhere to the following conditions:

1) It must be easy to run using libraries & tools commonly available on a development machine. 
1) Clear instructions for how to build and run the application should be included within the code, e.g. `README.md`
1) The code must be your own work. If you have a strong case to use a small code snippet of someone else's e.g. a
boilerplate function, it must be clearly commented and attributed to the original author.
1) The flight data cannot be changed, and must be loaded from the CSV file, so it can easily be replaced with another file.
1) You must include any unit tests you think are appropriate.
1) Give consideration to performance - particuarly under load.

## What it should do
The application should allow the user to select or input any date, of any year, resulting in the display of flights on
that day, displayed in chronological order -- a Flight Information Display.

The interface can be web-based, command-line or otherwise, but as already mentioned, you should choose the approach you
feel demonstrates your expertise and suitability for the position.

## Supplying your code
Please **create and commit your code into a public Github repository** and supply the link to the recruiter for review.  Your code should compile and run in one step.

## Supporting Data
The [flight data](flights.csv) is a simple comma-separated file containing the following:

| Departure Time | Destination | Destination Airport IATA | Flight No | Sun | Mon | Tue | Wed | Thu | Fri | Sat
| :--- | :--- | :--- | :--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 09:00 | Antigua | ANU | VS033 |  |  | `x` |  |  |  | 
| 10:00 | Antigua | ANU | VS033 |  |  |  |  | `x` |  | `x`
| 11:05 | Barbados | BGI | VS029 | `x` | `x` | `x` | `x` | `x` | `x` | `x`
| 12:20 | Cancun | CUN | VS093 |  |  | `x` |  |  |  | 
| 09:00 | Grenada | GND | VS089 |  | `x` |  |  |  |  | 
| 10:10 | Grenada | GND | VS089 |  |  |  |  |  | `x` | 
| 10:15 | Havana | HAV | VS063 |  |  | `x` |  |  |  | 
| 10:15 | Havana | HAV | VS063 |  | `x` |  |  | `x` |  | 
| 10:15 | Las Vegas | LAS | VS043 | `x` |  |  |  |  | `x` | `x`
| 10:25 | Las Vegas | LAS | VS043 |  |  |  |  | `x` |  | 
| 10:35 | Las Vegas | LAS | VS043 |  | `x` | `x` | `x` |  |  | 
| 15:35 | Las Vegas | LAS | VS044 | `x` | `x` | `x` | `x` | `x` | `x` | `x`
| 12:25  | Montego Bay | MBJ | VS065 |  |  |  | `x` |  |  | 
| 12:40 | Montego Bay | MBJ | VS065 | `x` |  |  |  |  |  | 
| 10:10 | Orlando | MCO | VS049 | `x` |  |  |  |  |  | 
| 10:15 | Orlando | MCO | VS027 |  |  |  | `x` |  |  | 
| 11:00 | Orlando | MCO | VS027 |  | `x` |  |  |  |  | 
| 11:10 | Orlando | MCO | VS049 |  | `x` |  |  |  |  | 
| 11:20 | Orlando | MCO | VS027 |  |  |  |  |  | `x` | `x`
| 11:35 | Orlando | MCO | VS027 |  |  |  |  | `x` |  | 
| 11:45 | Orlando | MCO | VS027 | `x` |  | `x` |  |  |  | 
| 11:45 | Orlando | MCO | VS049 |  |  |  | `x` |  |  | 
| 13:00 | Orlando | MCO | VS015 | `x` | `x` | `x` | `x` | `x` | `x` | `x`
| 09:00 | St Lucia | UVF | VS089 |  | `x` |  |  |  |  | 
| 09:00 | St Lucia | UVF | VS097 | `x` |  |  |  |  |  | 
| 10:10 | St Lucia | UVF | VS089 |  |  |  |  |  | `x` | 
| 09:00 | Tobago | TAB | VS097 | `x` |  |  |  |  |  |

The ``x`` denotes days that the flight operates. 

## --------------------------------------------------------------------------------------
# Solution
## --------------------------------------------------------------------------------------

## Project Description

This is a Multi-Maven module Project it uses SpringBoot Framework version 2.3.6.RELEASE.
The Project uses Spring Batch to Read multiple data sources like csv files to load Flight data, And insert into an in memory List Data Structure so that we can serve the data as a REST API.

Everytime we start the APP, it re-loads the data from all the csv files form app resources (airport-app-dao/resources/flights/) and it can be fetched from our API


## Prerequisites: 
To build and run this project below are the prerequisites
	
	|-Apache Maven Version: 3.*
	|-Java 1.8 (JDK, JRE)
	
## Development Environemt Used:

	Spring Boot version: 2.3.6.RELEASE	
	Java 8
	Junit 5
	Lombok
	
## Project Structure:
	airport-app-parent
		|-airport-app-dao
			-src/main/java 			## java code for dao
			-src/main/resource		## resources like (application.properties, .csv files)
			-src/test/java			## unit test java code 
			-src/test/resource		## unit test resources
		|-airport-app-rest-api 
			-src/main/java 			## java code for rest api
			-src/main/resource		## resources like (application.properties)
			-src/test/java			## unit test java code 
			-src/test/resource		## unit test resources
			-src/int-test			## postman integration tests
			
## Building the project:

The packaging of the parent maven module (airport-app-parent) is POM, so if we want to run test cases from all modules and build all modules together, we can do it with command:
	
	mvn install
It will generate artifacts for all sub modules in their respective target directories

The packaging of the sub maven module (airport-app-dao) is JAR, so if we want to run test cases and build the Jar, we can do it with command:
	
	mvn install
It will generate artifact Jar file for this sub modules in target directory, Which can be used as a library dependency in other spring app	
	
The packaging of the sub maven module (airport-app-rest-api) is JAR, so if we want to run test cases and build the Jar, we can do it with command:
	
	mvn install
It will generate artifact Jar file for this sub modules in target directory, Which can be used to deploy the REST API on any machine with JRE installed.	
 
## airport-app-dao Module

- The airport-app-dao module contains the code which reads the data from CSV files and loads it into the in memory data structure.
- This module does not have any SpringBoot Application class, and so it can not be started as a stand alone app.
- This module can be used as a library in a Spring Boot app for getting data from DAO (we are using it from airport-app-rest-api Module)

## airport-app-rest-api Module

- The airport-app-rest-api module contains the code which uses airport-app-dao module internally to fetch flight data and serve it as a REST API
- This module have a SpringBoot Application class, and so it can be started as a stand alone REST app.


## Running the project:
Once the airport-app-rest-api module is built using maven command, a Jar artifact will be generated in airport-app-rest-api/taget/ directory.

To start the REST API, we can run below command inside airport-app-rest-api/taget/ directory
	
	java -jar airport-app-rest-api-1.0.jar

## Testing

- Unit test cases are implemented to shocase the concept of unit testing in Springboot using mockito and mock mvc.
- Unit test cases are available under below directory structure 

	|-airport-app-dao
		-src/test/java			## unit test java code 
	|-airport-app-rest-api 
		-src/test/java			## unit test java code 
	
- Postman collection is available to test the APIs under below directory structure 

	|-airport-app-rest-api 
		-src/int-test			## postman integration tests

## Running the tests:

	mvn test
	
## Running the REST API To Visualize the data in browser:

Below URls can be opened in browser or postman once the app is up and running (to run the app see Running the project section):

	http://localhost:8080/flights?for=2022-06-29
	
## Desclaimer:

- Completed Project is implemented solely by:

	Shridutt Kothari 
	shriduttkothari@gmail.com
	+91-9713740276
