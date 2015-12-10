package com.mtnaircomputer.atcsmon_dbtools;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.*;


public class CmdLineParser {

	public static CommandLine ParseOptions( String[] args ) {
		
		CommandLine commandLine = null;
	    
	    try {
			Options options = CmdLineParser.createOptions();
		    CommandLineParser parser = new BasicParser();
		    
		    commandLine = parser.parse(options, args);
	
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    
	    return commandLine;
	}

	public static Options createOptions() {
        
		Options options = new Options();

        // Add all the possible options we support:
        
        options.addOption("sourceDBPath", true, "Source MDB file path.");
        options.addOption("masterDBPath", true, "MDB file path to update.");

        return options;
    }
}
