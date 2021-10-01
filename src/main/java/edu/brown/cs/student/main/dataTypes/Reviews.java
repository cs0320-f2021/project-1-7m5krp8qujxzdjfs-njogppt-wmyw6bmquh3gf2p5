package edu.brown.cs.student.main.dataTypes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class that stores review data.
 */
public class Reviews {

  private int id;
  private String review_text;
  private String review_summary;
  private Date review_date;

  /**
   * The constructor for a review object.
   * @param id - The unique id
   * @param review_text - The main text of the review
   * @param review_summary - The summary of the review
   * @param review_date - The date the review was published
   */
  public Reviews(int id, String review_text, String review_summary, Date review_date) {
    this.id = id;
    this.review_text = review_text;
    this.review_summary = review_summary;
    this.review_date = review_date;
  }
}