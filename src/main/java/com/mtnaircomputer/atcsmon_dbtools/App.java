package com.mtnaircomputer.atcsmon_dbtools;

import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.Date;

import org.apache.commons.cli.*;

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
    public static void main( String[] args )
    {
    	Options options = createOptions();
        CommandLineParser parser = new BasicParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("f")) {
            
	        } else {
	            System.out.println("[ERROR] No file parameter given!");
	
	        }
	    } catch (ParseException e) {
	        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	    }
        
		System.out.println ("Hello World, packages and such");
		
		try {
			Database db = DatabaseBuilder.open(new File("ATCSdb.mdb"));

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

    private static Options createOptions(){
        // create Options object
        Options options = new Options();

        // add t option
        options.addOption("f", true, "input mdb file path");
        options.addOption("s", false, "show file stats");
        options.addOption("e", false, "export file");
        options.addOption("db", true, "export to database");
        options.addOption("u", true, "database username");
        options.addOption("p", true, "database password");
        options.addOption("o", false, "overwrite existing tables");
        options.addOption("tp", true, "table prefix");

        return options;
    }
}
