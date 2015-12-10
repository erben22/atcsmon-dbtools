package com.mtnaircomputer.atcsmon_dbtools;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigorous Test :-)
     */
    public void testApp()
    {
    	App.main(null);
        assertTrue(true);
    }
    
    /**
     * Rigorous Test :-)
     */
    public void testAppEmptyOptions()
    {	
    	String[] emptyOptions = {""};
    	
    	App.main(emptyOptions);
        assertTrue(true);
    }
    
    /**
     * Testing main with some parameters.
     */
    public void testAppWithArguments()
    {	
    	String[] cmdLineOptions = {
    			"-sourceDBPath", "up_boone_15_1025.mbd",
    			"-masterDBPath", "ATCSdb.mdb"
    	};
    	
    	App.main(cmdLineOptions);
    	assertTrue(true);
    }
}
