package edu.brown.cs.student.main.KDTree;

import java.util.ArrayList;
import java.util.List;

// for testing purposes only
public class TestObject implements NodeValue<Integer> {

  Integer id;
  Double x;
  Double y;
  Double z;

  public TestObject(Integer id, Double x, Double y, Double z) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public List<Double> getNodeValue() {
    ArrayList<Double> coords = new ArrayList<>();
    coords.add(x);
    coords.add(y);
    coords.add(z);
    return coords;
  }

  @Override
  public Double getSingleNodeValue(int dim) {
    if (dim % 3 == 1) {
      return x;
    } else if (dim % 3 == 2) {
      return y;
    } else {
      return z;
    }
  }

  @Override
  public int getSecondAttribute() {
    return 0;
  }

  @Override
  public int getFourthAttribute() {
    return 0;
  }

  @Override
  public int getFifthAttribute() {
    return 0;
  }
}
