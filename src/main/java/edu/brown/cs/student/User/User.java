package edu.brown.cs.student.User;

import edu.brown.cs.student.main.Comparator;
import edu.brown.cs.student.main.Node;

import java.util.Collections;

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


}

static class UserComparatorByDimension extends Comparator<User> {
  int depth;
  int currentDepth;
  int k;


  UserComparatorByDimension(int depth, int currentDepth, int k) {
    this.depth = depth;
    this.currentDepth = currentDepth;
    this.k = k;
  }

  @Override
  public int compare(User o1, User o2) {
    // TODO: modify depending on caseMatters
    if(o1.getDimensionValue(this.depth, this.currentDepth, this.k) > (o2.getDimensionValue(this.depth, this.currentDepth, this.k))) return 1;
    if(o1.getDimensionValue(this.depth, this.currentDepth, this.k) > (o2.getDimensionValue(this.depth, this.currentDepth, this.k))) return -1;
    return 0;
  }


  public static <T extends Comparable<T>> void sortSomeRecords(Comparator<T> comp, List<T> lst) {
    for(int i=0; i<lst.size(); i++) {
      for(int j=i+1; j<lst.size(); j++) {
        if(comp.compare(lst.get(j), lst.get(i)) < 0) {
          T swap = lst.get(i);
          lst.set(i, lst.get(j));
          lst.set(j, swap);
        }
      }
    }
  }

  initial instance of comparator
Collections.sort(Comparator<T> comp, List<T> lst);
