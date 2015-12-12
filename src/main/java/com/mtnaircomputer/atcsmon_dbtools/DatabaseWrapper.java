/**
 * 
 */
package com.mtnaircomputer.atcsmon_dbtools;

import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

/**
 * @author erben22
 *
 */
public class DatabaseWrapper {
	
	protected String database_path = "";
	
	public DatabaseWrapper(String dbPath) {
		database_path = dbPath;
	}

    public void DumpDatabase() {
		try {
		    System.out.println("Dumping database: " + database_path);
	    	
			Database db = DatabaseBuilder.open(new File(database_path));

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
