package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.dataTypes.Rent;
import edu.brown.cs.student.main.dataTypes.Reviews;
import edu.brown.cs.student.main.dataTypes.Users;

import java.io.IOException;
import java.sql.SQLException;

public class InsertFunction implements FunctionHolder {
    @Override
    public void implementFunction(String[] arguments) {
        Database database = null;
        try {
            database = new Database(arguments[1]);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL command is not recognized");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Database was not found.");}
        if (arguments.length == 8 || arguments.length == 10) {
                Users newUser = new Users(arguments[1], arguments[2], arguments[3], arguments[4],
                        arguments[5], arguments[6], arguments[7]);
                        database.insert(newUser);
                        System.out.println("New user data added.");
                }

      /* for rent provide id fit user_id item_id rating rented_for category
      and size in that order. */
             else if (arguments.length == 9 || arguments.length == 11) {
                Rent newRent = new Rent(Integer.parseInt(arguments[1]), arguments[2], arguments[3],
                        arguments[4], arguments[5], arguments[6], arguments[7], arguments[8]);
                        database.insert(newRent);
                        System.out.println("New rent data added.");
                }

      /* for reviews provide id `review_text` `review_summary` and `review_date` in
      that order. ` delimits chunks of text. */
            else if (arguments.length == 5 || arguments.length == 7) {
                String reviewText = arguments[2].replaceAll("`", "");
                String reviewSum = arguments[3].replaceAll("`", "");
                String reviewDate = arguments[4].replaceAll("`", "");
                Reviews newReview = new Reviews(Integer.parseInt(arguments[1]), reviewText,
                        reviewSum, reviewDate);
                        database.insert(newReview);
                        System.out.println("New review data added.");
            }
        }
}

