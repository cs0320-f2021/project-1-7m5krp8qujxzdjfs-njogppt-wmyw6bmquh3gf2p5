package edu.brown.cs.student.main.dataTypes;

<<<<<<< HEAD

import edu.brown.cs.student.main.KDTree.NodeValue;

/**
 * A class that stores user info.
 */
public class Users implements NodeValue {
=======
/**
 * A class that stores user info.
 */
public class Users implements DataTypes {
>>>>>>> 5a59f38db467d521fc5e9cf3cdcfeebc0d3bd7b2

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

  /**
   * Gets the weight of the user.
   * @return - The user's weight
   */
  public int getSecondAttribute() {
    String[] w = this.weight.split("l");
    int lbs = Integer.parseInt(w[0]);
    return lbs;
  }

  /**
   * Gets the height in inches.
   * @return - The height of a user in inches.
   */
  public int getFourthAttribute() {
    String[] h = this.height.split("'|\"");
    int ft = Integer.parseInt(h[0]);
    int inches = Integer.parseInt(h[1]);
    return ft * 12 + inches;
  }

  /**
   * Gets the age of the user.
   * @return - The user's age.
   */
  public int getFifthAttribute() {
    return Integer.parseInt(this.age);
  }

  /**
   * Returns the id of the user.
   * @return - The user's id.
   */
  public int getFirstAttribute() {
    return Integer.parseInt(this.userId);
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
}
