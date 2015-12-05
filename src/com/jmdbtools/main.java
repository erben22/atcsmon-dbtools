package com.jmdbtools;

import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.*;

public class main {
	public static void main (String args[]) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
