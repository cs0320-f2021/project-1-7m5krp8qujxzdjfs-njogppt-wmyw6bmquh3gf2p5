package edu.brown.cs.student.main.ORM;

import java.sql.SQLException;

public class SQLFunction implements FunctionHolder {
    @Override
    public void implementFunction(String[] arguments) {
        Database database = null;
        try {
            database = new Database(arguments[1]);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL command is not recognized");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Database was not found.");
        }
        if (database != null) {
            database.sql(arguments[1]);
        } else {
            throw new RuntimeException("ERROR: There is not database connected.");
        }
    }
}
