package edu.brown.cs.student.main.KDTree;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.Star.StarData;

import java.util.List;

public class SimilarFunction implements FunctionHolder {
  private KDTree<Integer> kdt;

  @Override
  public void implementFunction(String[] arguments) {
    NodeValue<Integer> target = kdt.getTarget(Integer.parseInt(arguments[2]));
    List<NodeValue<Integer>> neighbors = kdt.getKNN(Integer.parseInt(arguments[1]), target);
    for (NodeValue<Integer> neighbor : neighbors) {
      System.out.println(neighbor);
    }
  }

  @Override
  public void setStarData(StarData sd) {

  }

  @Override
  public void setDatabase(Database db) {

  }

  @Override
  public void setKDTree(KDTree<Integer> kdTree) {
    this.kdt = kdTree;
  }
}
