package edu.brown.cs.student.main.Star;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class stores star data and finds neighbors.
 */
public class StarData {

  private final List<Star> stars;

  /**
   * Constructor for a StarData object.
   * @param filename - The path to the CSV containing the star data.
   */
  public StarData(String filename) {
    if (filename.equals("")) {
      this.stars = new ArrayList<>();
    } else {
      this.stars = this.parseFile(filename);
    }
  }

  /**
   * A method that reads the CSV and parses the star info.
   * @param filename - The path to the CSV containing the star data.
   * @return - An array list of star objects.
   */
  private List<Star> parseFile(String filename) {
    List<Star> output = new ArrayList<>();
    int starCount = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line = br.readLine();
      if (line == null || !this.checkHeader(line)) {
        throw new IOException("Error: The header of the given file does not have the"
            + "correct columns.");
      } else {
        while (line != null) {
          try {
            String[] data = line.split(",");
            if (!data[0].equals("StarID")) {
              int id = Integer.parseInt(data[0]);
              Double x = Double.parseDouble(data[2]);
              Double y = Double.parseDouble(data[3]);
              Double z = Double.parseDouble(data[4]);
              Star star = new Star(id, data[1], x, y, z);
              starCount += 1;
              output.add(star);
            }
            line = br.readLine();
          } catch (IOException e) {
            System.out.println("Error: Something went wrong processing your input.");
            System.out.println(e.getMessage());
          }
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Read " + starCount + " star from " + filename);
    return output;
  }

  /**
   * A method that checks that the header of the CSV is correct.
   * @param header - The header of the CSV.
   * @return - True if correct, else false.
   */
  private boolean checkHeader(String header) {
    String[] data = header.split(",");
    return data[0].equals("StarID") && data[1].equals("ProperName") && data[2].equals("X")
        && data[3].equals("Y") && data[4].equals("Z") || (data.length != 5);
  }

  /**
   * A method that finds the k nearest stars to the point (x, y, z).
   * @param k - How many stars to print out at most.
   * @param x - The x coordinate.
   * @param y - The y coordinate.
   * @param z - The z coordinate.
   * @return - A list of the closest stars from nearest to farthest
   */
  public List<Star> getPositionNeighbors(int k, Double x, Double y, Double z) {
    HashMap<Star, Double> distances = new HashMap<>();
    List<Star> neighbors = new ArrayList<>();
    try {
      if (k < 0) {
        throw new IOException("ERROR: k cannot be less than 0.");
      }
      for (Star curStar : stars) {
        Double dist = curStar.getDistance(x, y, z);
        distances.put(curStar, dist);
      }
      int howManyStars = Math.min(k, distances.size());
      int i = 1;
      while (i <= howManyStars) {
        List<Star> curSmallest = this.findSmallest(distances);
        if (neighbors.size() + curSmallest.size() <= howManyStars) {
          neighbors.addAll(curSmallest);
          for (Star s : curSmallest) {
            distances.remove(s);
          }
          i += curSmallest.size();
        } else {
          int left = howManyStars - i;
          List<Star> selectedTies = this.selectTies(curSmallest, left);
          neighbors.addAll(selectedTies);
          i += selectedTies.size();
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return neighbors;
  }

  /**
   * A method that randomly selects which tied stars go into the result.
   * @param ties - The stars equidistant from the target.
   * @param count - How many stars to return.
   * @return - The randomly selected stars.
   */
  private List<Star> selectTies(List<Star> ties, int count) {
    List<Star> selected = new ArrayList<>();
    for (int i = 0; i <= count; i++) {
      int ind = new Random().nextInt(ties.size());
      selected.add(ties.get(ind));
    }
    return selected;
  }

  /**
   * A method that finds the nearest star(s) in the given hashmap.
   * @param distances - A hashmap of stars to their distance from the given point.
   * @return - The current nearest neighbor star(s).
   */
  private List<Star> findSmallest(HashMap<Star, Double> distances) {
    Star nearestStar = new Star(-1, "temp", 0.0, 0.0, 0.0);
    List<Star> nearestStars = new ArrayList<>();
    double min = Double.POSITIVE_INFINITY;
    for (Map.Entry<Star, Double> star : distances.entrySet()) {
      if (star.getValue() < min) {
        nearestStar = star.getKey();
        min = star.getValue();
      }
    }
    nearestStars.add(nearestStar);
    distances.remove(nearestStar);
    List<Star> ties = this.checkTies(distances, min);
    nearestStars.addAll(ties);
    return nearestStars;
  }

  /**
   * A method that checks and returns any ties to the current nearest neighbor.
   * @param distances - A hashmap of stars and their distance to the target.
   * @param minimum - The current minimum distance.
   * @return - A list of stars equidistant to the target.
   */
  private List<Star> checkTies(HashMap<Star, Double> distances, double minimum) {
    List<Star> ties = new ArrayList<>();
    for (Map.Entry<Star, Double> star : distances.entrySet()) {
      if (star.getValue().equals(minimum)) {
        ties.add(star.getKey());
      }
    }
    return ties;
  }

  /**
   * A method that finds the k nearest stars to the given star.
   * @param k - How many stars to print out at most.
   * @param star - The star from which distance is calculated.
   * @return - A list of the closest stars from nearest to farthest.
   */
  public List<Star> getStarNeighbors(int k, String star) {
    String name = star.replaceAll("\"", "");
    List<Star> neighbors = new ArrayList<>();
    try {
      List<String> names = this.getNames();
      if (!names.contains(name)) {
        throw new IOException("ERROR: The given star is not in the star data.");
      } else {
        int ind = names.indexOf(name);
        Star startStar = this.stars.get(ind);
        Double[] position = startStar.getPosition();
        neighbors = this.getPositionNeighbors(k + 1, position[0], position[1], position[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    neighbors.remove(0);
    return neighbors;
  }

  /**
   * A method that gets the names of the stars in the same order as the stars list.
   * @return - The list of star names.
   */
  private List<String> getNames() {
    List<String> names = new ArrayList<>();
    for (Star s : this.stars) {
      names.add(s.getName());
    }
    return names;
  }

  /**
   * Gets the stars field.
   * @return - The star field.
   */
  public List<Star> getStars() {
    return this.stars;
  }

}
