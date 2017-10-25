# Uber Project
## Tim Lee
## CPE 305

# The **uber** package:
1. Reads a set of Passengers from an input file in JSON format
2. Assigns them all to random locations in a grid
3. Gives each Passenger a random destination
4. Assigns each Passenger the nearest available Driver to create a Trip
5. Executes a transaction based on the Trip's distance, canceling it if the Passenger has insufficient funds
6. Uses timers to simulate the time taken for each trip
7. Saves data from every trip in a JSON file
8. Saves the final state of the components in an output file

## Running the program
1. download all files in **src/uber**
2. download **in/initializationJSON.txt** and either
	* place it in **initializationJSON.txt** in a folder called **in** in the same directory as **src** OR
	* specify the location of the file in the **UberRunner** class (See Modifications below)
3. Run the package using the *main* method in the **UberRunner** class

# Modifications
Different file locations for the input and output can be specified in **UberRunner**
* The location of the input file is specified by the String *initialFile*
	- should consist of a JSON file containing a JSONObject with 2 fields: "passengers" and "drivers"
	- "passengers" and "drivers" are both JSONArrays
	- JSONObjects in "passengers" contain a "name"(String) and a "balance"(double)
	- JSONObjects in "drivers" contain the same fields as those in "passengers", with the addition of "make", "model", and "plate" Strings and the "year" int
* The location of the trip log is specified by the String *tripLog*
* The location of the output of the final state is specified by the String *outputFile*

The running conditions can be altered from the **UberApp** class
* *GRIDSIZE* represents the number of units per side of the square grid the actors are bound to
* *PRICE* represents the price per unit distance traveled (including the Driver going to the Passenger)
* *TIME* is the number of milliseconds it takes to travel one unit distance
The package contains methods that support assigning non-random Locations for intial placement and/or destinationsations
However, utilizing them in the *main* method would require modifying the input file, **UberIO**, **UberRunner**, and **UberApp**
A later version of this project may overload the relevant **UberApp** methods so only the input file and **UberIO** would require change

# Testing
Unit tests are contained in **src/tests**

Run *main* in **TestRunner** to run all tests

Add or modify tests in the various **\*Tester** classes
* **DriverTester**, **LocationTester**, **TripTester**, and **UberIOTester** test low-level methods
	- **UberIOTester**'s *outputTester* does not use JUnit to check the output. The output files must be checked manually
* **UberRunnerTester** tests higher-level methods such as control flow that require lower-level ones to function 

 