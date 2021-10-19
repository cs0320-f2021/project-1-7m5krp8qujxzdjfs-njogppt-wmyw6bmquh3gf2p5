package edu.brown.cs.student.main.KDTree;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.Star.StarData;
import edu.brown.cs.student.main.dataTypes.Users;
import java.util.ArrayList;
import java.util.List;

/**
 * A FunctionHolder for the classify command.
 */
public class ClassifyFunction implements FunctionHolder {
  private KDTree<Integer> kdt;

  @Override
  public void implementFunction(String[] arguments) {
    if (arguments.length == (3)) {
      NodeValue<Integer> target = kdt.getTargetFromId(Integer.parseInt(arguments[2]));
      List<NodeValue<Integer>> neighbors = kdt.getKNN(Integer.parseInt(arguments[1]), target);
      List<Integer> counts = this.getCounts(neighbors);
      System.out.println("Aries: " + counts.get(0) + "\n" + "Taurus: " + counts.get(1) + "\n"
          + "Gemini: " + counts.get(2) + "\n" + "Cancer: " + counts.get(3) + "\n" + "Leo: "
          + counts.get(4) + "\n" + "Virgo: " + counts.get(5) + "\n" + "Libra: " + counts.get(6)
          + "\n" + "Scorpio: " + counts.get(7) + "\n" + "Sagittarius: " + counts.get(8) + "\n"
          + "Capricorn: " + counts.get(9) + "\n" + "Aquarius: " + counts.get(10) + "\n"
          + "Pisces: " + counts.get(11));
    } else if (arguments.length == 5) {
      String height = Integer.parseInt(arguments[3]) / 12 + "'"
          + Integer.parseInt(arguments[3]) % 12 + "\"";
      // make a temp user to store the coordinates in
      NodeValue<Integer> target = new Users("0", arguments[2], "0", height,
          arguments[4], "bodyType", "horoscope");
      List<NodeValue<Integer>> neighbors = kdt.getKNN(Integer.parseInt(arguments[1]), target);
      List<Integer> counts = this.getCounts(neighbors);
      System.out.println("Aries: " + counts.get(0) + "\n" + "Taurus: " + counts.get(1) + "\n"
          + "Gemini: " + counts.get(2) + "\n" + "Cancer: " + counts.get(3) + "\n" + "Leo: "
          + counts.get(4) + "\n" + "Virgo: " + counts.get(5) + "\n" + "Libra: " + counts.get(6)
          + "\n" + "Scorpio: " + counts.get(7) + "\n" + "Sagittarius: " + counts.get(8) + "\n"
          + "Capricorn: " + counts.get(9) + "\n" + "Aquarius: " + counts.get(10) + "\n"
          + "Pisces: " + counts.get(11));
    }
  }

  /**
   * Gets the counts of the different horoscopes from the neighbors.
   * @param neighbors - The neighbors found in the KDTree.
   * @return - The list of counts.
   */
  private List<Integer> getCounts(List<NodeValue<Integer>> neighbors) {
    List<Integer> counts = new ArrayList<>();
    int aries = 0;
    int taurus = 0;
    int gemini = 0;
    int cancer = 0;
    int leo = 0;
    int virgo = 0;
    int libra = 0;
    int scorpio = 0;
    int sagittarius = 0;
    int capricorn = 0;
    int aquarius = 0;
    int pisces = 0;
    for (NodeValue<Integer> neighbor : neighbors) {
      String horoscope = neighbor.getSeventhAttribute();
      switch (horoscope) {
        case "Aries" :
          aries += 1;
          break;
        case "Taurus" :
          taurus += 1;
          break;
        case "Gemini" :
          gemini += 1;
          break;
        case "Cancer" :
          cancer += 1;
          break;
        case "Leo" :
          leo += 1;
          break;
        case "Virgo" :
          virgo += 1;
          break;
        case "Libra" :
          libra += 1;
          break;
        case "Scorpio" :
          scorpio += 1;
          break;
        case "Sagittarius" :
          sagittarius += 1;
          break;
        case "Capricorn" :
          capricorn += 1;
          break;
        case "Aquarius" :
          aquarius += 1;
          break;
        case "Pisces" :
          pisces += 1;
          break;
        default: throw new RuntimeException("ERROR: THe horoscope of some user is not "
            + "in the correct format.");
      }
    }
    counts.add(aries);
    counts.add(taurus);
    counts.add(gemini);
    counts.add(cancer);
    counts.add(leo);
    counts.add(virgo);
    counts.add(libra);
    counts.add(scorpio);
    counts.add(sagittarius);
    counts.add(capricorn);
    counts.add(aquarius);
    counts.add(pisces);
    return counts;
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
