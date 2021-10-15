package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import edu.brown.cs.student.main.Star.Star;
import edu.brown.cs.student.main.Star.StarData;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * A test class for the StarData class's methods.
 */
public class StarDataTest {

  @Test
  public void testConstructor() {
    StarData s1 = new StarData("");
    StarData s2 = new StarData("data/stars/one-star.csv");
    assertEquals(0, s1.getStars().size());
    assertEquals(1, s2.getStars().size());
  }

  @Test
  public void testGetPositionNeighbors() {
    StarData s2 = new StarData("data/stars/three-star.csv");
    List<Star> actual = s2.getPositionNeighbors(2, 0.0, 0.0, 0.0);
    List<Star> expected = new LinkedList<>();
    expected.add(new Star(1, "Star One", 1.0, 0.0, 0.0));
    expected.add(new Star(2, "Star Two", 2.0, 0.0, 0.0));
    assertEquals(expected, actual);
  }

  @Test
  public void testGetPositionNeighborsBigK() {
    StarData s2 = new StarData("data/stars/three-star.csv");
    List<Star> actual = s2.getPositionNeighbors(5, 0.0, 0.0, 0.0);
    List<Star> expected = new LinkedList<>();
    expected.add(new Star(1, "Star One", 1.0, 0.0, 0.0));
    expected.add(new Star(2, "Star Two", 2.0, 0.0, 0.0));
    expected.add(new Star(3, "Star Three", 3.0, 0.0, 0.0));
    assertEquals(expected, actual);
  }

  @Test
  public void testGetStarNeighbors() {
    StarData s2 = new StarData("data/stars/three-star.csv");
    List<Star> actual = s2.getStarNeighbors(1, "Star One");
    List<Star> expected = new LinkedList<>();
    expected.add(new Star(2, "Star Two", 2.0, 0.0, 0.0));
    assertEquals(expected, actual);
  }

  @Test
  public void testGetStarNeighborsBigK() {
    StarData s2 = new StarData("data/stars/three-star.csv");
    List<Star> actual = s2.getStarNeighbors(5, "Star One");
    List<Star> expected = new LinkedList<>();
    expected.add(new Star(2, "Star Two", 2.0, 0.0, 0.0));
    expected.add(new Star(3, "Star Three", 3.0, 0.0, 0.0));
    assertEquals(expected, actual);
  }

  @Test
  public void testGetStarNeighborsTie() {
    StarData s2 = new StarData("data/stars/three-star.csv");
    List<Star> actual = s2.getStarNeighbors(1, "Star Two");
    List<Star> expected = new LinkedList<>();
    expected.add(new Star(1, "Star One", 1.0, 0.0, 0.0));
    expected.add(new Star(3, "Star Three", 3.0, 0.0, 0.0));
    assertTrue(expected.contains(actual.get(0)));
  }

}
