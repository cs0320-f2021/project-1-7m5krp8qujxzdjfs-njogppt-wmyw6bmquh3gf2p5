package edu.brown.cs.student.main.ORM;

import java.sql.SQLException;

public class DatabaseFunction implements FunctionHolder {

    public DatabaseFunction(){

    }
    @Override
    public void implementFunction(String[] arguments) {
        try {
            Database database = new Database(arguments[1]);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL command is not recognized");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Database was not found.");
        }
        System.out.println("Database at " + arguments[1] + " loaded in.");
    }
}
