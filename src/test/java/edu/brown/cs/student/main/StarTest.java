package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import edu.brown.cs.student.main.Star.Star;
import org.junit.Test;

/**
 * A class for testing the Star class's methods.
 */
public class StarTest {

  @Test
  public void testConstructor() {
    Star s = new Star(1, "Sun", 1.0, 2.5, 0.0);
    Double[] position = s.getPosition();
    Double[] test = {1.0, 2.5, 0.0};
    assertEquals(1, s.getID());
    assertEquals("Sun", s.getName());
    for (int i = 0; i < position.length; i++) {
      assertEquals(test[i], position[i]);
    }
  }

  @Test
  public void testGetDistance() {
    Star s = new Star(1, "Sun", 0.0, 0.0, 0.0);
    assertEquals(Math.sqrt(5), s.getDistance(1.0, 2.0, 0.0), 0.01);
  }
  @Test
  public void testGetDistanceHard() {
    Star s = new Star(1, "Sun", -125.12, 12.4, 1.2);
    assertEquals(Math.sqrt(392019.6269), s.getDistance(500.0, 47.5, -2.35), 0.01);
  }

}
