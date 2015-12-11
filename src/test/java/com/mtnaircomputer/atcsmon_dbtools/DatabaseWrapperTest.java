package com.mtnaircomputer.atcsmon_dbtools;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the DatabaseWrapper class.
 */
public class DatabaseWrapperTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DatabaseWrapperTest(String testName)
    {
        super(testName);
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(DatabaseWrapperTest.class);
    }

    /**
     * 
     */
    public void testOpenDatabase()
    {
    	assertNotNull(null);
    }


}
