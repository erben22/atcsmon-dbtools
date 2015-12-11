package com.mtnaircomputer.atcsmon_dbtools;

import org.apache.commons.cli.CommandLine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CmdLineParserTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CmdLineParserTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(CmdLineParserTest.class);
    }

    /**
     * 
     */
    public void testParseOptionsNullInputFails()
    {
    	assertNotNull( CmdLineParser.ParseOptions(null));
    }

    /**
     * 
     */
    public void testParseOptionsValidInputOutputFiles()
    {
    	String[] cmdLineOptions = {
    			"-sourceDBPath", "up_boone_15_1025.mdb",
    			"-masterDBPath", "ATCSdb.mdb"
    	};
    	
    	CommandLine commandLine = CmdLineParser.ParseOptions(cmdLineOptions);
    	assertNotNull(commandLine);
    	
    	assertTrue(commandLine.getOptionValue(
    			CmdLineParser.SOURCE_DB_PATH).equals(
    					"up_boone_15_1025.mbd"));
       }
}
