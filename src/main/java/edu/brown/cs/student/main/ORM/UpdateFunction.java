package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.FunctionHolder;
import edu.brown.cs.student.main.Star.StarData;
import edu.brown.cs.student.main.dataTypes.Rent;
import edu.brown.cs.student.main.dataTypes.Reviews;
import edu.brown.cs.student.main.dataTypes.Users;

public class UpdateFunction implements FunctionHolder {

  private Database database = null;

  @Override
  public void implementFunction(String[] arguments) {
    if (database != null) {
      if (arguments.length == 8 || arguments.length == 10) {
        Users newUser = new Users(arguments[1], arguments[2], arguments[3], arguments[4],
            arguments[5], arguments[6], arguments[7]);
        String updateBy = arguments[8];
        String updateWith = arguments[9];
        database.update(newUser, updateBy, updateWith);
        System.out.println("User with user_id: " + newUser.getID() + " updated.");
      } else if (arguments.length == 9 || arguments.length == 11) {
        Rent newRent = new Rent(Integer.parseInt(arguments[1]), arguments[2], arguments[3],
            arguments[4], arguments[5], arguments[6], arguments[7], arguments[8]);
        String updateBy = arguments[9];
        String updateWith = arguments[10];
        database.update(newRent, updateBy, updateWith);
        System.out.println("Rent data with id: " + newRent.getID() + " updated.");
      } else if (arguments.length == 5 || arguments.length == 7) {
        String reviewText = arguments[2].replaceAll("`", "");
        String reviewSum = arguments[3].replaceAll("`", "");
        String reviewDate = arguments[4].replaceAll("`", "");
        Reviews newReview = new Reviews(Integer.parseInt(arguments[1]), reviewText,
            reviewSum, reviewDate);
        String updateBy = arguments[5];
        String updateWith = arguments[6];
        database.update(newReview, updateBy, updateWith);
        System.out.println("Review with id: " + newReview.getID() + " updated.");
      }
    } else {
      throw new RuntimeException("ERROR: There is no database connected.");
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
