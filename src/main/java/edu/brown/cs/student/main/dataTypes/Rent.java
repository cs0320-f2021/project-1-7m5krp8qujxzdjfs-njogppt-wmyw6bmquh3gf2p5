package edu.brown.cs.student.main.dataTypes;

/**
 * A class that stores rent data.
 */
public class Rent implements DataTypes {

  private final int id;
  private final String userId;
  private final String itemId;
  private final String fit;
  private final String rating;
  private final String rentedFor;
  private final String category;
  private final String size;

  /**
   * The constructor for rent objects.
   * @param id - The unique id of the renting
   * @param userId - The unique id of the user
   * @param itemId - The unique id of the item rented
   * @param fit - The fit of the clothing
   * @param rating - A numeric rating (10 is max)
   * @param rentedFor - Occasion for the rental
   * @param category - The type of clothing rented
   * @param size - The numeric size
   */
  public Rent(int id, String userId, String itemId, String fit, String rating,
              String rentedFor, String category, String size) {
    this.id = id;
    this.userId = userId;
    this.itemId = itemId;
    this.fit = fit;
    this.rating = rating;
    this.rentedFor = rentedFor;
    this.category = category;
    this.size = size;
  }

  @Override
  public String toString() {
    return "Rental data with id: " + this.id;
  }

  @Override
  public int getID() {
    return this.id;
  }
}
