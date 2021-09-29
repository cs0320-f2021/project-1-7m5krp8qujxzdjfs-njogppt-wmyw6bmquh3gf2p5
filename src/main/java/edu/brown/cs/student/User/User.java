
/**
 * A class that stores user info
 */
public class User {

  private final int id;
  private int weight;
  private int height;
  private int age;

  /**
   * User constructor
   * @param id - The user's unique id
   * @param weight - The user's weight in pounds
   * @param height - The user's height in inches
   * @param age - The user's age
   */
  public User(int id, int weight, int height, int age) {
    this.id = id;
    this.weight = weight;
    this.height = height;
    this.age = age;
  }
}