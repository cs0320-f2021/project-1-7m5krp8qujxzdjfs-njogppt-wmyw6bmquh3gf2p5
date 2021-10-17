package edu.brown.cs.student.main;

import edu.brown.cs.student.main.KDTree.KDTree;
import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.Star.StarData;

public interface FunctionHolder {
  /**
   * A method that stores the actual implementation of a given command.
   * @param arguments - The parameters necessary to call the command implementation.
   */
  void implementFunction(String[] arguments);

  /**
   * Sets the star data for functions that use it.
   * @param sd - The loaded in star data from the REPL.
   */
  void setStarData(StarData sd);

  /**
   * Sets the database for the functions that use it.
   * @param db - The database connected to the REPL.
   */
  void setDatabase(Database db);

  /**
   * Sets the KDTree for the functions that use it.
   * @param kdTree - The KDTree made in the REPL.
   */
  void setKDTree(KDTree<Integer> kdTree);
}
