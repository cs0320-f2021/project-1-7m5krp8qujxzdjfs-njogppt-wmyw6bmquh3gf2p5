package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.dataTypes.Users;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

  public <T> void insert(T datum) throws IllegalAccessException {
    String table = datum.getClass().getSimpleName().toLowerCase();
    Field[] fields = datum.getClass().getDeclaredFields();
  }

  public <T> void delete(T datum) {

  }

  public List<Users> where(String searchBy, String searchFor) {

  }

  public <T> void update(T toUpdate, String updateBy) {
    
  }

  public void sql(String command) {

  }

}
