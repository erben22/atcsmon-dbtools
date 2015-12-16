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

    public boolean MergeDatabases() {
        if (!ValidateDatabaseExists()) {
            return false;
        }

        // Start simple...add a dummy row

        try {
            Database masterDB = DatabaseBuilder.open(new File(database_path));

            try {
                System.out.println("****************************");
                System.out.println("BEFORE database modification>");
                DumpDatabase();
                System.out.println("AFTER database modification<");
                System.out.println("****************************");

                Table table = masterDB.getTable("MCP");
                Date insertDate = new Date();
                
                table.addRow("8802837160015", "Cody W", "422.2", "0.0.252", 16,
                    ",EGZ,,WGZ,,RWZ,,NWZ,,,,,,,,", "0.0.242", "16", ",EGK,EAK,WGK,WAK,RWK,TK,NWK,,,,,,,,",
                    "UP Nampa" ,"ID Owyhee/Owyhee", "928.18125", "Genisys RFL", 0, "1161318W", "432518N",
                    insertDate);
                    //"Sun Jul 24 06:08:43 MDT 2011");

                
//                    06:08:43 MDT 2011
//Column MCPUpdated(SHORT_DATE_TIME): null (null)
//Column MCPActivityI(TEXT): null (null)
//Column MCPActivityC(TEXT): null (null)
 //

                for(Row row : table) {
                    System.out.println("Look ma, a row: " + row);

//                    for(Column column : table.getColumns()) {
//                        String columnName = column.getName();
//                        Object value = row.get(columnName);
//                        System.out.println("Column " + columnName + "(" + column.getType() + "): "
//                            + (value != null ? value : "null") + " ("
//                            + (value != null ? value.getClass() : "null") + ")");
//                    }
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
        if (database_path == null) {
            System.out.println("ERROR: database_path is null");
            return false;
        } else {
            File dbFile = new File(database_path);
            if (dbFile.exists() && !dbFile.isDirectory()) {
                //System.out.println("INFO: database_path exists");
                return true;
            } else {
                System.out.println("ERROR: database_path does not exist");
                return false;
            }
        }
    }
}
