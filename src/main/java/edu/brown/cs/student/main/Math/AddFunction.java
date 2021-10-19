package edu.brown.cs.student.main.Math;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.KDTree.KDTree;
import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.Star.StarData;

public class AddFunction implements FunctionHolder {

  @Override
  public void implementFunction(String[]arguments) {
    MathBot mbot = new MathBot();
    double num1 = Double.parseDouble(arguments[1]);
    double num2 = Double.parseDouble(arguments[2]);
    System.out.println(mbot.add(num1, num2));
  }

  @Override
  public void setStarData(StarData sd) {

  }

  @Override
  public void setDatabase(Database db) {

  }

  @Override
  public void setKDTree(KDTree<Integer> kdTree) {

  }
}
