package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.dataTypes.DataTypes;
import edu.brown.cs.student.main.dataTypes.Rent;
import edu.brown.cs.student.main.dataTypes.Reviews;
import edu.brown.cs.student.main.dataTypes.Users;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the ORM methods.
 */
public class Database {

  private static Connection conn = null;

  /**
   * Initiates the database and loads in the file.
   * @param filename - The file path to the database.
   * @throws SQLException - Thrown if an error occurs in any SQL query.
   */
  public Database(String filename) throws SQLException, ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    String urlToDB = "jdbc:sqlite:" + filename;
    conn = DriverManager.getConnection(urlToDB);
    Statement stat = conn.createStatement();
    stat.executeUpdate("PRAGMA foreign_keys=ON;");
    stat.close();
  }

  /**
   * Inserts a given object into the connected database.
   * @param datum - The object being added in.
   * @param <T> - The class of the object (Users, Rent, or Reviews).
   * @throws IllegalAccessException - Thrown if there is a problem with the reflective access.
   * @throws SQLException - Thrown if there is a problem with the SQL command.
   */
  public <T> void insert(T datum) throws IllegalAccessException, SQLException {
    try {
      String table = datum.getClass().getSimpleName().toLowerCase();
      String sql = this.checkClass(table);
      PreparedStatement prep = conn.prepareStatement(sql);
      Field[] fields = datum.getClass().getDeclaredFields();
      int cnt = 1;
      for (Field f : fields) {
        f.setAccessible(true);
        if (f.getName().equals("id")) {
          prep.setInt(fields.length, f.getInt(datum));
        } else {
          prep.setString(cnt, String.valueOf(f.get(datum)));
          cnt += 1;
        }
      }
      prep.addBatch();
      prep.executeBatch();
      prep.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Helper method that returns the appropriate INSERT sql command.
   * @param className - The table to insert into.
   * @return - The appropriate sql command.
   * @throws IOException - Thrown when such a table does not exist.
   */
  private String checkClass(String className) throws IOException {
    switch (className) {
      case "users": return "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
      case "rent": return "INSERT INTO rent VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      case "reviews": return "INSERT INTO reviews VALUES (?, ?, ?, ?)";
      default: throw new IOException("ERROR: This table is not handled by the ORM.");
    }
  }

  /**
   * Deletes a given object from the connected database. Users are deleted via user_id,
   * rents are deleted via id, and reviews are deleted via id.
   * @param datum - The object to be deleted.
   * @param <T> - The class of the object (Users, Reviews, Rent).
   */
  public <T> void delete(T datum) {
    try {
      String table = datum.getClass().getSimpleName().toLowerCase();
      String sql = checkDeleteBy(table);
      PreparedStatement prep = conn.prepareStatement(sql);
      Field[] fields = datum.getClass().getDeclaredFields();
      for (Field f : fields) {
        f.setAccessible(true);
        if (f.getName().equals("userId") && table.equals("users")) {
          prep.setString(1, String.valueOf(f.get(datum)));
        } else if (f.getName().equals("id")) {
          prep.setInt(1, f.getInt(datum));
        }
      }
      prep.addBatch();
      prep.executeBatch();
      prep.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Returns the appropriate SQL command for the given object.
   * @param className - The table to delete from.
   * @return - The SQL command for deletion.
   * @throws IOException - Thrown if the given data type does not exist.
   */
  private String checkDeleteBy(String className) throws IOException {
    switch (className) {
      case "users": return "DELETE FROM users WHERE user_id=?";
      case "rent": return "DELETE FROM rent WHERE id=?";
      case "reviews": return "DELETE FROM reviews WHERE id=?";
      default: throw new IOException("ERROR: This type of data is not in the database.");
    }
  }

  /**
   * A method that searches the database and selects the desired objects.
   * It then creates new instances of the selected objects and returns them in a list.
   * @param dataType - The data type that will be made.
   * @param searchBy - The attribute being searched by.
   * @param searchFor - The value being searched for.
   * @return - A list of the desired data type.
   * @throws IOException - Thrown if the given data type doesn't exist.
   * @throws SQLException - Thrown if the SQL query is incorrect.
   */
  public List<DataTypes> where(String dataType, String searchBy, String searchFor)
      throws SQLException, IOException {
    List<DataTypes> output = new ArrayList<>();
    String sql = "SELECT * FROM " + dataType.toLowerCase() + " WHERE " + searchBy;
    PreparedStatement prep = conn.prepareStatement(sql);
    prep.setString(1, searchFor);
    ResultSet selected = prep.executeQuery();
    while (selected.next()) {
      DataTypes datum = this.makeNewT(dataType, selected);
      output.add(datum);
    }
    prep.close();
    return output;
  }

  /**
   * Helper that returns a blank instance of the required data type.
   * @param dataType - The type of data being made.
   * @param selected - The result set from the query.
   * @return - An empty instance of the data type.
   * @throws SQLException - Thrown if there is an issue getting something from the result set.
   * @throws IOException - Thrown if the data type given doesn't exist.
   */
  public DataTypes makeNewT(String dataType, ResultSet selected) throws SQLException, IOException {
    switch (dataType) {
      case "Users":
        String userID = selected.getString("user_id");
        String weight = selected.getString("weight");
        String bustSize = selected.getString("bust_size");
        String height = selected.getString("height");
        String age = selected.getString("age");
        String bodyType = selected.getString("body_type");
        String horoscope = selected.getString("horoscope");
        return new Users(userID, weight, bustSize, height, age, bodyType, horoscope);
      case "Rent":
        int id = selected.getInt("id");
        String userID2 = selected.getString("user_id");
        String itemID = selected.getString("item_id");
        String fit = selected.getString("fit");
        String rating = selected.getString("rating");
        String rentedFor = selected.getString("rented_for");
        String category = selected.getString("category");
        String size = selected.getString("size");
        return new Rent(id, userID2, itemID, fit, rating, rentedFor, category, size);
      case "Reviews":
        int id2 = selected.getInt("id");
        String reviewText = selected.getString("review_text");
        String reviewSum = selected.getString("review_summary");
        String reviewDate = selected.getString("review_date");
        return new Reviews(id2, reviewText, reviewSum, reviewDate);
      default: throw new IOException("ERROR: This data type is not present.");
    }
  }

  public <T> void update(T toUpdate, String updateBy) {

  }

  public void sql(String command) {

  }

}
