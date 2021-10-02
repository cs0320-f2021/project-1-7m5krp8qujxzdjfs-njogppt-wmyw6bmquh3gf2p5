package edu.brown.cs.student.main.dataTypes;


/**
 * A class that stores user info.
 */
public class Users implements DataTypes {

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
  public int getWeight() {
    String[] w = this.weight.split("l");
    int lbs = Integer.parseInt(w[0]);
    return lbs;
  }

  /**
   * Gets the height in inches.
   * @return - The height of a user in inches.
   */
  public int getHeight() {
    String[] h = this.height.split("'|\"");
    int ft = Integer.parseInt(h[0]);
    int inches = Integer.parseInt(h[1]);
    return ft * 12 + inches;
  }

  /**
   * Gets the age of the user.
   * @return - The user's age.
   */
  public int getAge() {
    return Integer.parseInt(this.age);
  }

  public int getID() {
    return Integer.parseInt(this.userId);
  }

  @Override
  public String toString() {
    return "User with id: " + this.userId;
  }

  public int getDimensionValue(int currentDepth) {
    int dimension = currentDepth % 3;
    if (dimension == 0) {
      return this.getWeight();
    } else if (dimension == 1) {
      return this.getHeight();
    } else {
      return this.getAge();
    }
  }
}
