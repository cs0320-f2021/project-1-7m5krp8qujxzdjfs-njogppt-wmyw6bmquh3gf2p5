import edu.brown.cs.student.main.Comparator;
import edu.brown.cs.student.main.Node;

/**
 * A class that stores user info
 */
public class User implements Comparable<User> {

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

  public int getDimensionValue(int depth, int currentDepth, int k) {
    int dimension = currentDepth % k;
    if (currentDepth > depth) {
      return -1;
    } else if (dimension == 1) {
      return this.weight;
    } else if (dimension == 2) {
      return this.height;
    } else if (dimension == 3) {
      return this.age;
    }
    else {
      return -1;
    }
  }


  @Override
  public int compareTo(User o) {
    if(this.compareTo(o.last) > 0) return 1;
    if(this.last.compareTo(o.last) < 0) return -1;

    if(this.first.compareTo(o.first) > 0) return 1;
    if(this.first.compareTo(o.first) < 0) return -1;
    return 0;
  }

}


