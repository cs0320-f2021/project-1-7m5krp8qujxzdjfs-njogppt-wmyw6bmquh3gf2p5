package edu.brown.cs.student.main.KDTree;

/** Class to create a KeyDistance of specified type ID.
 @param <T> Any type for the ID of the KeyDistance that is specified when constructing a KeyDistance.
 */
public class KeyDistance<T> {
  private final T key;
  private final Double distance;

  /** Create a KeyDistance that pairs a unique id to any real number.
   @param key the id of the KeyDistance.
   @param distance the distance of the KeyDistance to some arbitrary target.
   */
  public KeyDistance(T key, Double distance) { this.key = key; this.distance = distance; }

  /** Get the distance from the KeyDistance.
   @return a double number, i.e., a real number.
   */
  public Double getDistance() { return distance; }

  /** Get the key from the KeyDistance.
   @return a value of the same type when constructing the KeyDistance.
   */
  public T getKey() { return key; }
}