package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.Star.StarData;
import edu.brown.cs.student.main.dataTypes.Rent;
import edu.brown.cs.student.main.dataTypes.Reviews;
import edu.brown.cs.student.main.dataTypes.Users;
import java.sql.SQLException;

public class InsertFunction implements FunctionHolder {

  private Database database = null;

  @Override
  public void implementFunction(String[] arguments) {
    try {
      if (database != null) {
        if (arguments.length == 8 || arguments.length == 10) {
          /* for users provide id weight bust_size height age body_type and horoscope in
          that order.*/
          Users newUser = new Users(arguments[1], arguments[2], arguments[3], arguments[4],
              arguments[5], arguments[6], arguments[7]);
          database.insert(newUser);
          System.out.println("New user data added.");
        } else if (arguments.length == 9 || arguments.length == 11) {
          /* for rent provide id fit user_id item_id rating rented_for category
          and size in that order. */
          Rent newRent = new Rent(Integer.parseInt(arguments[1]), arguments[2], arguments[3],
              arguments[4], arguments[5], arguments[6], arguments[7], arguments[8]);
          database.insert(newRent);
          System.out.println("New rent data added.");
        } else if (arguments.length == 5 || arguments.length == 7) {
          /* for reviews provide id `review_text` `review_summary` and `review_date` in
          that order. ` delimits chunks of text. */
          String reviewText = arguments[2].replaceAll("`", "");
          String reviewSum = arguments[3].replaceAll("`", "");
          String reviewDate = arguments[4].replaceAll("`", "");
          Reviews newReview = new Reviews(Integer.parseInt(arguments[1]), reviewText,
              reviewSum, reviewDate);
          database.insert(newReview);
          System.out.println("New review data added.");
        }
      } else {
        throw new RuntimeException("ERROR: There is no database connected.");
      }
    } catch (IllegalAccessException | SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void setStarData(StarData sd) {
  }

  @Override
  public void setDatabase(Database db) {
    database = db;
  }
}

