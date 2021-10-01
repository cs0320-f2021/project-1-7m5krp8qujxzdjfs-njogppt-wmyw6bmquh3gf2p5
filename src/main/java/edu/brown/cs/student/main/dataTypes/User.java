package edu.brown.cs.student.main.dataTypes;

import java.util.Collections;

/**
 * A class that stores user info
 */
public class User {

  private final int id;
  private int weight;
  private String bustSize;
  private int height;
  private int age;
  private String bodyType;
  private String horoscope;

  /**
   * User constructor
   * @param id - The user's unique id
   * @param weight - The user's weight in pounds
   * @param height - The user's height in inches
   * @param age - The user's age
   */
  public User(int id, int weight, String bustSize, int height, int age, String bodyType, String horoscope) {
    this.id = id;
    this.weight = weight;
    this.bustSize = bustSize;
    this.height = height;
    this.age = age;
    this.bodyType = bodyType;
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




