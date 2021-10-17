package edu.brown.cs.student.main.KDTree;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.Star.StarData;

public class SimilarFunction implements FunctionHolder {
  private KDTree<NodeValue> kdTree;

  @Override
  public void implementFunction(String[] arguments) {
    // TODO: Implement similar
    /*if (arguments.length == 3) {
      kdTree.getKNN(Integer.parseInt(arguments[1]), arguments[2])
    } else {

    }
     */
  }

  @Override
  public void setStarData(StarData sd) {

  }

  @Override
  public void setDatabase(Database db) {

  }
}
