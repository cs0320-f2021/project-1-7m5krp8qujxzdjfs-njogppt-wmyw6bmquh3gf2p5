package edu.brown.cs.student.main.dataTypes;

import java.util.Collections;

/**
 * A class that stores user info
 */
public class User {

  private final int user_id;
  private int weight;
  private String bust_size;
  private int height;
  private int age;
  private String body_type;
  private String horoscope;

  /**
   * User constructor
   * @param id - The user's unique id
   * @param weight - The user's weight in pounds
   * @param height - The user's height in inches
   * @param age - The user's age
   */
  public User(int id, int weight, String bust_size, int height, int age, String body_type, String horoscope) {
    this.user_id = id;
    this.weight = weight;
    this.bust_size = bust_size;
    this.height = height;
    this.age = age;
    this.body_type = body_type;
    this.horoscope = horoscope;
  }


  public int getWeight(){
    return this.weight;
  }

  public int getHeight(){
    return this.height;
  }

  public int getAge(){
    return this.age;
  }

}




