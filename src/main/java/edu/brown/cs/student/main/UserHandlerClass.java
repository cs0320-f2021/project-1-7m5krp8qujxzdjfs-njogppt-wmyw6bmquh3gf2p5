package edu.brown.cs.student.main;

import edu.brown.cs.student.main.dataTypes.Users;

import java.util.Comparator;
import java.util.List;

public class UserHandlerClass {
  /**
   * method to sort a user list by the users' weight attribute.
   * @param userListForWeight - list of users to sort
   * @return sorted list of users based on ascending weight values
   */
  public List<Users> sortingUsersByWeight(List<Users> userListForWeight) {
    Comparator<Users> sortingComparator = Comparator.comparing(Users::getWeight);
    userListForWeight.sort(sortingComparator);
    return userListForWeight;
  }

  /**
   * method for sorting a user lists by users' height attribute.
   * @param userListForHeight - list of users to sort
   * @return sorted ascending list of users by height values
   */
  public List<Users> sortingUsersByHeight(List<Users> userListForHeight) {
    Comparator<Users> sortingComparator = Comparator.comparing(Users::getHeight);
    userListForHeight.sort(sortingComparator);
    return userListForHeight;
  }

  /**
   * method for sorting list of users by users' age attribute.
   * @param userListForAge - list of users to sort
   * @return sorted ascending list of users by age values
   */
  public List<Users> sortingUsersByAge(List<Users> userListForAge) {
    Comparator<Users> sortingComparator = Comparator.comparing(Users::getAge);
    userListForAge.sort(sortingComparator);
    return userListForAge;
  }

  /**
   * calls the appropriate sorting method depending on the attribute corresponding to the current depth of the KDTree
   * @param currentDepth - the level of the tree currently being analyzed
   * @param userListForSort - the list of users to be sorted according to the dimension associated with current depth
   * @return sorted list of users
   */
  public List<Users> sortedByDimension(int currentDepth, List<Users> userListForSort) {
    int dimension = currentDepth % 3;
    if (dimension == 0) {
      return sortingUsersByWeight(userListForSort);
    } else if (dimension == 1) {
      return sortingUsersByHeight(userListForSort);
    } else {
      return sortingUsersByAge(userListForSort);
    }
  }



}

