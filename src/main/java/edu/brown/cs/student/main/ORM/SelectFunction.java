package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.dataTypes.DataTypes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SelectFunction implements FunctionHolder {
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
            List<DataTypes> results = null;
            try {
                results = database.where(arguments[1], arguments[2], arguments[3]);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL command is not recognized");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ERROR: The given command is not recognized.");
            }
            System.out.println(results);
        } else {
            throw new RuntimeException("ERROR: There is no database connected.");
        }

    }
}
