package com.mtnaircomputer.atcsmon_dbtools;

import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.Date;

import org.apache.commons.cli.CommandLine;

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
    	CommandLine commandLine = CmdLineParser.ParseOptions(args);

		System.out.println("Hello World, packages and such");

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
}
