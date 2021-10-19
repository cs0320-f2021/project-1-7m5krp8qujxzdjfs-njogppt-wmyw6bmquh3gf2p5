package edu.brown.cs.student.main.Star;

/**
 * A class that stores an individual star's info.
 */
public class Star {

  private final int id;
  private final String name;
  private final Double x;
  private final Double y;
  private final Double z;

  /**
   * Constructor for a star object.
   * @param id - The unique id of the star.
   * @param name - The name of the star.
   * @param x - The x value of the star's position.
   * @param y - The y value of the star's position.
   * @param z - The z value of the star's position.
   */
  public Star(int id, String name, Double x, Double y, Double z) {
    this.id = id;
    this.name = name;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Calculates the distance between the star and a given point.
   * @param xCoord - The x coordinate of the given point.
   * @param yCoord - The y coordinate of the given point.
   * @param zCoord - The z coordinate of the given point.
   * @return - The distance.
   */
  public Double getDistance(Double xCoord, Double yCoord, Double zCoord) {
    double xVal = Math.pow(this.x - xCoord, 2);
    double yVal = Math.pow(this.y - yCoord, 2);
    double zVal = Math.pow(this.z - zCoord, 2);
    return Math.sqrt(xVal + yVal + zVal);
  }

  /**
   * Gets the position of the star as an array with [x, y, z].
   * @return - The star's coordinates in an array.
   */
  public Double[] getPosition() {
    return new Double[]{this.x, this.y, this.z};
  }

  /**
   * Gets the name of the star.
   * @return - The name of the star.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the id of the star.
   * @return - The star's id.
   */
  public int getID() {
    return id;
  }

  /**
   * An override of the toString method for Stars.
   * @return - A string representation of a star.
   */
  @Override
  public String toString() {
    return "Star{" + "id= " + id + " name='" + name + '\'' + '}';
  }

  /**
   * An override of the equals method for Stars.
   * @param o - The object being compared.
   * @return - True if all fields are equal, else false.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Star)) {
      return false;
    }
    Star s = (Star) o;
    Double[] positionOther = s.getPosition();
    return s.getID() == this.id && s.getName().equals(this.name) && this.x.equals(positionOther[0])
        && this.y.equals(positionOther[1]) && this.z.equals(positionOther[2]);
  }

  /**
   * An override of the hashCode method for Stars.
   * @return - The hashCode for a given star.
   */
  @Override
  public int hashCode() {
    return this.id + this.name.hashCode();
  }
}
