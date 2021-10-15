package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.dataTypes.Rent;
import edu.brown.cs.student.main.dataTypes.Reviews;
import edu.brown.cs.student.main.dataTypes.Users;

import java.sql.SQLException;

public class DeleteFunction implements FunctionHolder {
    @Override
    public void implementFunction(String[] arguments) {
        Database database = null;
        try {
            database = new Database(arguments[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (arguments.length == 8 || arguments.length == 10) {
            Users newUser = new Users(arguments[1], arguments[2], arguments[3], arguments[4],
                    arguments[5], arguments[6], arguments[7]);
            database.delete(newUser);
            System.out.println("User with user_id " + newUser.getID() + " deleted.");
        } else if (arguments.length == 9 || arguments.length == 11) {
            Rent newRent = new Rent(Integer.parseInt(arguments[1]), arguments[2], arguments[3],
                    arguments[4], arguments[5], arguments[6], arguments[7], arguments[8]);
            database.delete(newRent);
            System.out.println("Rent data with id " + newRent.getID() + " deleted.");
        } else if (arguments.length == 5 || arguments.length == 7) {
            String reviewText = arguments[2].replaceAll("`", "");
            String reviewSum = arguments[3].replaceAll("`", "");
            String reviewDate = arguments[4].replaceAll("`", "");
            Reviews newReview = new Reviews(Integer.parseInt(arguments[1]), reviewText,
                    reviewSum, reviewDate);
            database.delete(newReview);
            System.out.println("Review with id " + newReview.getID() + " deleted.");
        }
    }
}
