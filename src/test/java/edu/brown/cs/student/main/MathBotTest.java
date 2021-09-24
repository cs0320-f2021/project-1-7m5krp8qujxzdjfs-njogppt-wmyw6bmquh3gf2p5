package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathBotTest {

  @Test
  public void testAddition() {
    MathBot matherator9000 = new MathBot();
    double output = matherator9000.add(10.5, 3);
    assertEquals(13.5, output, 0.01);
  }

  @Test
  public void testLargerNumbers() {
    MathBot matherator9001 = new MathBot();
    double output = matherator9001.add(100000, 200303);
    assertEquals(300303, output, 0.01);
  }

  @Test
  public void testSubtraction() {
    MathBot matherator9002 = new MathBot();
    double output = matherator9002.subtract(18, 17);
    assertEquals(1, output, 0.01);
  }

  // TODO: add more unit tests of your own

  @Test
  public void testZerosAdd() {
    MathBot matherator9003 = new MathBot();
    double output = matherator9003.add(5, 0);
    assertEquals(5, output, 0.01);
  }

  @Test
  public void testZerosSub() {
    MathBot matherator9004 = new MathBot();
    double output = matherator9004.subtract(0, 5);
    assertEquals(-5, output, 0.01);
  }

  @Test
  public void testDecimalsAdd() {
    MathBot matherator9005 = new MathBot();
    double output = matherator9005.add(0.26, 23.5);
    assertEquals(23.76, output, 0.01);
  }

  @Test
  public void testDecimalsSub() {
    MathBot matherator9006 = new MathBot();
    double output = matherator9006.subtract(43.750, 3.6457);
    assertEquals(40.1043, output, 0.01);
  }

  @Test
  public void testNegativesAdd() {
    MathBot matherator9007 = new MathBot();
    double output = matherator9007.add(-5, -2);
    assertEquals(-7, output, 0.01);
  }

  @Test
  public void testNegativesSub() {
    MathBot matherator9008 = new MathBot();
    double output = matherator9008.subtract(-6, -3);
    assertEquals(-3, output, 0.01);
  }
}
