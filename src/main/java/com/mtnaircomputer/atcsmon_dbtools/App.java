package com.mtnaircomputer.atcsmon_dbtools;

import org.apache.commons.cli.CommandLine;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args)
    {
        //if ((args != null) && (args.length > 2))
        {
            CommandLine commandLine = CmdLineParser.ParseOptions(args);
            String sourceDBPath = commandLine.getOptionValue(
                CmdLineParser.SOURCE_DB_PATH);
            String masterDBPath = commandLine.getOptionValue(
                CmdLineParser.MASTER_DB_PATH);

            DatabaseWrapper masterDB = new DatabaseWrapper(masterDBPath);
            DatabaseWrapper sourceDB = new DatabaseWrapper(sourceDBPath);

            masterDB.DumpDatabase();
            sourceDB.DumpDatabase();
        }
    }
}
