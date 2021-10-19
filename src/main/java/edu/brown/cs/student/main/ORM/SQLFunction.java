package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.KDTree.KDTree;
import edu.brown.cs.student.main.Star.StarData;

public class SQLFunction implements FunctionHolder {

  private Database database = null;

  @Override
  public void implementFunction(String[] arguments) {
    if (database != null) {
      database.sql(arguments[1]);
    } else {
      throw new RuntimeException("ERROR: There is not database connected.");
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
