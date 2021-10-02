package edu.brown.cs.student.main.KDTree;

import edu.brown.cs.student.main.UserHandlerClass;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class KDTree<V> {

  private Node<V> _root; // root node
  private Integer _k; // the dimensions of the space (ex. 3-dimensional space)

  public KDTree(Integer k) {
    this._k =  k;
  }

  public Node<V> getMidpointToNode(int depth, List<V> userList) {
    UserHandlerClass userSorter = new UserHandlerClass();
    ArrayList<V> sortedUserList = userSorter.sortedByDimension(depth, userList);
    int midPointIndex = (sortedUserList.size() / 2) - 1;
//    User userToNode = sortedUserList.get(midPointIndex);

    List<V> leftList = sortedUserList.subList(0, midPointIndex);
    List<V> rightList = sortedUserList.subList(midPointIndex + 1, sortedUserList.size());

    return this.createNode(sortedUserList.get(midPointIndex), this.getMidpointToNode(depth + 1,
        leftList), this.getMidpointToNode(depth + 1, rightList), depth);
  }


  public Node<V> createNode(V val, Node<V> left, Node<V> right, int depth) {
    Node<V> newNode = new Node<>(val, depth, _k);

    if (_root == null) {
      _root = newNode;
    }

    newNode.setLesser(left);
    newNode.setGreater(right);
    left.setParent(newNode);
    right.setParent(newNode);

    return newNode;
  }

  // takes in two nodes of the tree and returns the euclidean distance between them
  public double NodeDistance(Node<V> node1, Node<V> node2) {
    return euclideanDist();
  }

  public double euclideanDist(double x, double y, double z, double x2, double y2, double z2) {
    double newX = (x - x2) * (x - x2);
    double newY = (y - y2) * (y - y2);
    double newZ = (z - z2) * (z - z2);
    return Math.sqrt(newX + newY + newZ);
  }








  public List<V> getKNearestNeighbors() {
    ArrayList<V> kNearestNeighbors = new ArrayList<V>();





    return kNearestNeighbors;
  }

















  // kinda useless ig
  public void addNode(V val) {
    if (val == null) {
      return; // exit
    }
    if (this._root == null) { // tree empty
      this._root = new Node<V>(val, this._k, 0); // create root with val
      return; // exit
    }

    Node<V> currNode = this._root; // the current node we're looking at in the traversal

    while (true) { // goes forever until reaches a break statement

      int axis = currNode.getDepth() % _k; // what attribute we're looking at at this level
      if(this._comparator.compare(currNode.getKey(), val, axis) >= 0) { // lesser          !!!!!!!!!!!!!!!!!!!--figure out way to compare--!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(currNode.getLesser() == null) { // no left child
          Node<V> newNode = new Node<>(val, currNode.getDepth() + 1, _k); // create new node
          newNode.setParent(currNode); // child points at parent
          currNode.setLesser(newNode); // parent points at child
          break; // exit loop
        }
        currNode = currNode.getLesser(); // if there is a left child, set currNode to it
      } else { //greater
        if(currNode.getGreater() == null) { // no right child
          Node<V> newNode = new Node<>(val, currNode.getDepth() + 1, _k); // create new node
          newNode.setParent(currNode); // child points at parent
          currNode.setGreater(newNode); // parent points at child
          break; // exit loop
        }
        currNode = currNode.getGreater(); // if there is a right child, set currNode to it
      }
    }
  }

}
