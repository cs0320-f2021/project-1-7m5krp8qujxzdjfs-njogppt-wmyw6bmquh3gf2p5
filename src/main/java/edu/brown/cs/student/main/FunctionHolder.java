package edu.brown.cs.student.main;

import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.Star.StarData;

public interface FunctionHolder {
  void implementFunction(String[] arguments);
  void setStarData(StarData sd);
  void setDatabase(Database db);
}
