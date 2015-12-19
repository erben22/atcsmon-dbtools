/**
 *
 */
package com.mtnaircomputer.atcsmon_dbtools;

import java.io.File;
import java.io.IOException;

//import com.healthmarketscience.jackcess.Column;
//import com.healthmarketscience.jackcess.Database;
//import com.healthmarketscience.jackcess.DatabaseBuilder;
//import com.healthmarketscience.jackcess.Row;
//import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import com.healthmarketscience.jackcess.util.ExportUtil;

/**
 * @author erben22
 *
 */
public class DatabaseWrapper {

    protected String database_path = "";

    public DatabaseWrapper(String dbPath) {
        if (dbPath != null) {
            database_path = dbPath;
        }
    }

    public void DumpDatabase() {
        try {
            if (!ValidateDatabaseExists()) {
                return;
            }

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
                        //System.out.println("Column " + columnName + "(" + column.getType() + "): "
                        //    + (value != null ? value : "null") + " ("
                        //    + (value != null ? value.getClass() : "null") + ")");
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

    public boolean MergeDatabases(String source_database_path) {
        if (!ValidateDatabaseExists() ||
            !ValidateDatabaseExists(source_database_path)) {
            return false;
        }

        // Start simple...add a dummy row

        try {
            Database masterDB = DatabaseBuilder.open(new File(database_path));
            Database sourceDB = DatabaseBuilder.open(new File(source_database_path));

            try {
                System.out.println("****************************");
                System.out.println("BEFORE database modification>");
                DumpDatabase();
                System.out.println("AFTER database modification<");
                System.out.println("****************************");

                Table masterMCPTable = masterDB.getTable("MCP");
                Table sourceMCPTable = sourceDB.getTable("MCP");

//                for(Row row : sourceMCPTable) {
//                    for (Map.Entry<String, Object> col : row.entrySet()) {
//                        System.out.println("col.getKey()" + col.getKey());
//                        System.out.println("col.getValue()" + col.getValue());
//                    }
//                }

                //Date insertDate = new Date("Sun Jul 24 06:08:43 MDT 2011");

                //masterMCPTable.addRow("8802837160016", "Cody W", "422.2", "0.0.252", 16,
                //    ",EGZ,,WGZ,,RWZ,,NWZ,,,,,,,,", "0.0.242", "16", ",EGK,EAK,WGK,WAK,RWK,TK,NWK,,,,,,,,",
                //    "UP Nampa" ,"ID Owyhee/Owyhee", "928.18125", "Genisys RFL", 0, "1161318W", "432518N",
                //    insertDate, null, null);


                for(Row row : sourceMCPTable) {
                    System.out.println("Look ma, a row: " + row);
                    Map<String,Object> columnMap = new HashMap<String, Object>();
                    
                    for(Column column : sourceMCPTable.getColumns()) {
                        Object value = row.get(column.getName());
                        if (value != null) {
                            columnMap.put(column.getName(), value);
                        }
                    }
                    
                    masterMCPTable.addRow(columnMap.get("MCPAddress"));
                    
                }

                System.out.println("****************************");
            }
            finally {
                masterDB.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    protected boolean ValidateDatabaseExists() {
        return ValidateDatabaseExists(database_path);
    }

    protected boolean ValidateDatabaseExists(String db_path) {
        if (db_path == null) {
            System.out.println("ERROR: db_path is null");
            return false;
        } else {
            File dbFile = new File(db_path);
            if (dbFile.exists() && !dbFile.isDirectory()) {
                //System.out.println("INFO: db_path exists");
                return true;
            } else {
                System.out.println("ERROR: database_path does not exist");
                return false;
            }
        }
    }
}
