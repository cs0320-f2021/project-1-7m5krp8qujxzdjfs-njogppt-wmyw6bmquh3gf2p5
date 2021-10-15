package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import edu.brown.cs.student.main.KDTree.KDTree;
import edu.brown.cs.student.main.KDTree.Node;
import edu.brown.cs.student.main.KDTree.NodeValue;
import edu.brown.cs.student.main.KDTree.TestObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KDTreeTest {

  @Test
  public void makeKDTree() {
    NodeValue<Integer> one = new TestObject(1, 4.0, 7.0, 4.0);
    NodeValue<Integer> two = new TestObject(2, 7.0, 1.0, 3.0);
    NodeValue<Integer> three = new TestObject(3, 2.0, 9.0, 4.0);

    ArrayList<NodeValue<Integer>> list = new ArrayList<>();
    list.add(one);
    list.add(two);
    list.add(three);

    KDTree kdt = new KDTree(3, list);

    NodeValue<Integer> target = new TestObject(999, 7.0, 1.0, 3.1);
    List<NodeValue<Integer>> result = kdt.getKNN(1, target);

    assertEquals(7.0, result.get(0).getSingleNodeValue(1), .01);
    assertEquals(1.0, result.get(0).getSingleNodeValue(2), .01);
    assertEquals(3.0, result.get(0).getSingleNodeValue(3), .01);
  }
}
