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
	    	
	    	DumpDatabase(masterDBPath);
	    	DumpDatabase(sourceDBPath);
    	}
    }
    
    protected static void DumpDatabase(String dbPath){
		try {
		    System.out.println("Dumping database: " + dbPath);
	    	
			Database db = DatabaseBuilder.open(new File(dbPath));

			try {
				for(String tableName : db.getTableNames()) {
					System.out.println("Table name: " + tableName);
				}

				Table table = db.getTable("MCP");
				for(Row row : table) {
				    System.out.println("Look ma, a row: " + row);

				    for(Column column : table.getColumns()) {
						    String columnName = column.getName();
						    Object value = row.get(columnName);
						    System.out.println("Column " + columnName + "(" + column.getType() + "): "
						    		+ (value != null ? value : "null") + " (" 
						    		+ (value != null ? value.getClass() : "null") + ")");
				    }
				}
			}
			finally {
				db.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
