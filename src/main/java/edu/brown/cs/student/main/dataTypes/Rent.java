package edu.brown.cs.student.main.dataTypes;

/**
 * A class that stores rent data.
 */
public class Rent {

  private int id;
  private int user_id;
  private int item_id;
  private String fit;
  private int rating;
  private String rented_for;
  private String category;
  private int size;

  /**
   * The constructor for rent objects
   * @param id - The unique id of the renting
   * @param user_id - The unique id of the user
   * @param item_id - The unique id of the item rented
   * @param fit - The fit of the clothing
   * @param rating - A numeric rating (10 is max)
   * @param rented_for - Occasion for the rental
   * @param category - The type of clothing rented
   * @param size - The numeric size
   */
  public Rent(int id, int user_id, int item_id, String fit, int rating,
              String rented_for, String category, int size) {
    this.id = id;
    this.user_id = user_id;
    this.item_id = item_id;
    this.fit = fit;
    this.rating = rating;
    this.rented_for = rented_for;
    this.category = category;
    this.size = size;
  }
}