package edu.brown.cs.student.main.dataTypes;

import edu.brown.cs.student.main.KDTree.NodeValue;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that stores user info.
 */
public class Users implements DataTypes, NodeValue<Integer> {

  private String userId;
  private String weight;
  private String bustSize;
  private String height;
  private String age;
  private String bodyType;
  private String horoscope;

  /**
   * User constructor.
   * @param id - The user's unique id
   * @param weight - The user's weight in pounds
   * @param bustSize - The user's bust size as a string
   * @param height - The user's height in inches
   * @param age - The user's age
   * @param bodyType - The user's body type
   * @param horoscope - The user's horoscope
   */
  public Users(String id, String weight, String bustSize, String height, String age,
               String bodyType, String horoscope) {
    this.userId = id;
    this.weight = weight;
    this.bustSize = bustSize;
    this.height = height;
    this.age = age;
    this.bodyType = bodyType;
    this.horoscope = horoscope;
  }

  @Override
  public Integer getId() {
    return Integer.parseInt(userId);
  }

  @Override
  public List<Double> getNodeValue() {
    ArrayList<Double> coords = new ArrayList<>();
    coords.add(this.getSecondAttribute() * 1.0);
    coords.add(this.getFourthAttribute() * 1.0);
    coords.add(this.getFifthAttribute() * 1.0);
    return coords;
  }

  @Override
  public Double getSingleNodeValue(int dim) {
    switch (dim) {
      case 1 : return this.getSecondAttribute() * 1.0;
      case 2 : return this.getFourthAttribute() * 1.0;
      case 3 : return this.getFifthAttribute() * 1.0;
      default: throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Gets the weight of the user.
   * @return - The user's weight
   */
  public int getSecondAttribute() {
    String[] w = this.weight.split("l");
    return Integer.parseInt(w[0]);
  }

  /**
   * Gets the height in inches.
   * @return - The height of a user in inches.
   */
  @SuppressWarnings("checkstyle:MagicNumber")
  public int getFourthAttribute() {
    String[] h = this.height.split("'|\"");
    int ft = Integer.parseInt(h[0]);
    int inches = Integer.parseInt(h[1].replaceAll(" ", ""));
    return ft * 12 + inches;
  }

  /**
   * Gets the age of the user.
   * @return - The user's age.
   */
  public int getFifthAttribute() {
    return Integer.parseInt(this.age);
  }

  @Override
  public String toString() {
    return "User with id: " + this.userId;
  }

  public int getDimensionValue(int currentDepth) {
    int dimension = currentDepth % 3;
    if (dimension == 0) {
      return this.getSecondAttribute();
    } else if (dimension == 1) {
      return this.getFourthAttribute();
    } else {
      return this.getFifthAttribute();
    }
  }

  @Override
  public int getID() {
    return Integer.parseInt(this.userId);
  }

  public String getSeventhAttribute() {
    return this.horoscope;
  }
}
