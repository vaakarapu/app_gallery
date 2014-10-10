package org.gal.test.appgallery.util;


import org.apache.log4j.Logger;
import org.testng.TestNG;



public class TestSuite {

	private static Logger logger = org.apache.log4j.Logger.getLogger( TestSuite.class );


	public static void main(String[] args) throws Exception{
		logger.info("Starting test suite: AppGallery");

		// A list of all classes that are in the test suite.

		Class[] testClasses = { 

				// An array of classes.
				// TODO:  Put the test classes in here.
				
		};

		TestNG tng = new TestNG();
		tng.setTestClasses(testClasses);
		tng.run();

		logger.info("Finished test suite: AppGallery");
	}
}



