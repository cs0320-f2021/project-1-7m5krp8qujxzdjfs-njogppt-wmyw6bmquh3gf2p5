package edu.brown.cs.student.main.dataTypes;

/**
 * A class that stores review data.
 */
public class Reviews {

  private int id;
  private String reviewText;
  private String reviewSummary;
  private String reviewDate;

  /**
   * The constructor for a review object.
   * @param id - The unique id
   * @param reviewText - The main text of the review
   * @param reviewSummary - The summary of the review
   * @param reviewDate - The date the review was published
   */
  public Reviews(int id, String reviewText, String reviewSummary, String reviewDate) {
    this.id = id;
    this.reviewText = reviewText;
    this.reviewSummary = reviewSummary;
    this.reviewDate = reviewDate;
  }
}
