package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		runTests(LocationTester.class);
		runTests(DriverTester.class);
		runTests(TripTester.class);
		runTests(UberIOTester.class);
		runTests(UberRunnerTester.class);
	}
	
	public static void runTests(Class<?> tester) {
		System.out.println("----------------------------------");
		System.out.println("Running " + tester.getName() + "...");
		Result result = JUnitCore.runClasses(tester);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println(result.wasSuccessful());
		
	}
}