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

    @Test
    public void makeBiggerKDTree() {
        NodeValue<Integer> one = new TestObject(1, 4.0, 7.0, 4.0);
        NodeValue<Integer> two = new TestObject(2, 7.0, 1.0, 3.0);
        NodeValue<Integer> three = new TestObject(3, 2.0, 9.0, 4.0);
        NodeValue<Integer> four = new TestObject(4, 2.0, 7.5, 5.8);
        NodeValue<Integer> five = new TestObject(5, -3.5, 27.4, 45.0);
        NodeValue<Integer> six = new TestObject(6, 20.0, 3.0, -40000.0);
        NodeValue<Integer> seven = new TestObject(7, 16.0, -89.0, 4.5);
        NodeValue<Integer> eight = new TestObject(8, 17.0, 3.0, 1.0);
        NodeValue<Integer> nine = new TestObject(9, -7.0, 7.0, 7.0);
        NodeValue<Integer> ten = new TestObject(10, 7.1, 1.0, 3.1);
        NodeValue<Integer> eleven = new TestObject(11, 7.1, 1.1, 3.1);

        ArrayList<NodeValue<Integer>> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        list.add(six);
        list.add(seven);
        list.add(eight);
        list.add(nine);
        list.add(ten);
        list.add(eleven);

        KDTree kdt = new KDTree(3, list);

        NodeValue<Integer> target = new TestObject(999, 7.0, 1.0, 3.0);
        List<NodeValue<Integer>> result = kdt.getKNN(5, target);

        assertEquals(7.0, result.get(0).getSingleNodeValue(1), .01);
        assertEquals(1.0, result.get(0).getSingleNodeValue(2), .01);
        assertEquals(3.0, result.get(0).getSingleNodeValue(3), .01);

        assertEquals(7.1, result.get(1).getSingleNodeValue(1), .01);
        assertEquals(1.0, result.get(1).getSingleNodeValue(2), .01);
        assertEquals(3.1, result.get(1).getSingleNodeValue(3), .01);

        assertEquals(7.1, result.get(2).getSingleNodeValue(1), .01);
        assertEquals(1.1, result.get(2).getSingleNodeValue(2), .01);
        assertEquals(3.1, result.get(2).getSingleNodeValue(3), .01);

        assertEquals(5, result.size(), .01);
    }
}
