package edu.brown.cs.student.main.KDTree;

import java.util.List;

/** Interface of a User with a specified type ID.
 @param <V> Any type for the ID of the user that is specified
 when constructing a User.
 */
public interface User<V> {

  /**
   * Get the user value at the dimension requested.
   *
   * @param dim the dimension number, from 1 to n where n is a positive integer.
   * @return a Double value, any real number.
   */
  Double getUserVal(int dim);

  /**
   * Get the ID.
   *
   * @return id of type with which the User was created.
   */
  V getId();

  /**
   * Get all user values of the User.
   *
   * @return a List of Double; i.e., a list of as many real numbers as there are dimensions.
   */
  List<Double> getUsers();

  /**
   * Represent the User as a String.
   *
   * @return a String representation of a User.
   */
  String toString();
}
