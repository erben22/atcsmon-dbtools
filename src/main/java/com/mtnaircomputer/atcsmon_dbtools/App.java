package com.mtnaircomputer.atcsmon_dbtools;

import java.io.File;
import java.io.IOException;

//import java.util.*;
//import java.util.Date;

import org.apache.commons.cli.CommandLine;
//import org.apache.commons.cli.CommandLineParser;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Column;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    	if ((args != null) && (args.length > 2))
    	{
	    	CommandLine commandLine = CmdLineParser.ParseOptions(args);
	    	String sourceDBPath = commandLine.getOptionValue(
	    			CmdLineParser.SOURCE_DB_PATH);
	    	String masterDBPath = commandLine.getOptionValue(
	    			CmdLineParser.MASTER_DB_PATH);
	    	
	    	DatabaseWrapper db = new DatabaseWrapper();
	    	
	    	db.DumpDatabase(masterDBPath);
	    	db.DumpDatabase(sourceDBPath);
    	}
    }
}
