package com.mtnaircomputer.atcsmon_dbtools;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.*;

public class CmdLineParser {

    public static final String SOURCE_DB_PATH = "sourceDBPath";
    public static final String MASTER_DB_PATH = "masterDBPath";
    public static final String OPERATION = "operation";
    public static final String OPERATION_DUMP = "dump";
    public static final String OPERATION_MERGE = "merge";

    public static CommandLine ParseOptions(String[] args) {

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

    protected static Options createOptions() {

        Options options = new Options();

        // Add all the possible options we support:

        options.addOption(SOURCE_DB_PATH, true, "Source MDB file path.");
        options.addOption(MASTER_DB_PATH, true, "MDB file path to update.");
        options.addOption(OPERATION, true, "Operation to perform.  Supported values:  dump, merge");

        return options;
    }
}
