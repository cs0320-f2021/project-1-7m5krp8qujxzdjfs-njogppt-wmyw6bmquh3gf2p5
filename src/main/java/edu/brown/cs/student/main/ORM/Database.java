package edu.brown.cs.student.main.ORM;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.brown.cs.student.main.dataTypes.User;

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

  public void insert(User datum) throws IllegalAccessException {
    String table = datum.getClass().getSimpleName();
    Field[] fields = datum.getClass().getDeclaredFields();
    int userId = fields[0].getInt(datum);
  }

}
