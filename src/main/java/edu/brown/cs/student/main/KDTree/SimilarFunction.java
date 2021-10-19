package edu.brown.cs.student.main.KDTree;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.Star.StarData;
import edu.brown.cs.student.main.dataTypes.Users;

import java.util.List;

/**
 * A FunctionHolder for the similar command.
 */
public class SimilarFunction implements FunctionHolder {
  private KDTree<Integer> kdt;

  @Override
  public void implementFunction(String[] arguments) {
    if (arguments.length == (3)) {
      NodeValue<Integer> target = kdt.getTargetFromId(Integer.parseInt(arguments[2]));
      List<NodeValue<Integer>> neighbors = kdt.getKNN(Integer.parseInt(arguments[1]), target);
      for (NodeValue<Integer> neighbor : neighbors) {
        System.out.println(neighbor);
      }
    } else if (arguments.length == 5) {
      String height = Integer.parseInt(arguments[3]) / 12 + "'"
          + Integer.parseInt(arguments[3]) % 12 + "\"";
      // make a temp user to store the coordinates in
      NodeValue<Integer> target = new Users("0", arguments[2], "0", height,
          arguments[4], "bodyType", "horoscope");
      List<NodeValue<Integer>> neighbors = kdt.getKNN(Integer.parseInt(arguments[1]), target);
      for (NodeValue<Integer> neighbor : neighbors) {
        System.out.println(neighbor);
      }
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
