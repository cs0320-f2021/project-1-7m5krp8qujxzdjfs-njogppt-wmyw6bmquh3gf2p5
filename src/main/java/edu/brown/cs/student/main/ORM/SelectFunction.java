package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.KDTree.KDTree;
import edu.brown.cs.student.main.Star.StarData;
import edu.brown.cs.student.main.dataTypes.DataTypes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SelectFunction implements FunctionHolder {

  private Database database = null;

  @Override
  public void implementFunction(String[] arguments) {
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

  @Override
  public void setStarData(StarData sd) {
  }

  @Override
  public void setDatabase(Database db) {
    database = db;
  }

  @Override
  public void setKDTree(KDTree<Integer> kdTree) {

  }
}
