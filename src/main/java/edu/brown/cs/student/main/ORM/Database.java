package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.dataTypes.Users;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Class that implements the ORM methods.
 */
public class Database {

  private static Connection conn = null;

  /**
   * Initiates the database and loads in the file.
   * @param filename - The file path to the database
   * @throws SQLException - If an error occurs in any SQL query
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
   * @param <T> - The class of the object (users, rent, or reviews)
   * @throws IllegalAccessException
   * @throws SQLException
   */
  public <T> void insert(T datum) throws IllegalAccessException, SQLException {
    try {
      String table = datum.getClass().getSimpleName().toLowerCase();
      String sql = this.checkClass(table);
      PreparedStatement prep =
          conn.prepareStatement(sql);
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
   * @param className - The table to insert into
   * @return - The appropraite sql command.
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

  public <T> void delete(T datum) {

  }

  public List<Users> where(String searchBy, String searchFor) {
    return null;
  }

  public <T> void update(T toUpdate, String updateBy) {

  }

  public void sql(String command) {

  }

}
