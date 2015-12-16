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
        CommandLine commandLine = CmdLineParser.ParseOptions(args);
        String sourceDBPath = commandLine.getOptionValue(
            CmdLineParser.SOURCE_DB_PATH);
        String masterDBPath = commandLine.getOptionValue(
            CmdLineParser.MASTER_DB_PATH);
        String operation = commandLine.getOptionValue(
            CmdLineParser.OPERATION);

        if ((sourceDBPath != null) && (masterDBPath != null) &&
            (operation != null)) {

            if (operation.equals(CmdLineParser.OPERATION_DUMP)) {
                DatabaseWrapper masterDB = new DatabaseWrapper(masterDBPath);
                DatabaseWrapper sourceDB = new DatabaseWrapper(sourceDBPath);

                masterDB.DumpDatabase();
                sourceDB.DumpDatabase();
            } else if (operation.equals(CmdLineParser.OPERATION_MERGE)) {
                System.out.println("Operation MERGE partially implemented.");
                
                DatabaseWrapper masterDB = new DatabaseWrapper(masterDBPath);
                
                masterDB.MergeDatabases();
                
            } else {
                System.out.println("ERROR:  Unknown operation.");
            }
        }
    }
}
