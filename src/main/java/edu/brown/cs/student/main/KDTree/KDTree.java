package edu.brown.cs.student.main.KDTree;

import edu.brown.cs.student.main.NodeValueHandlerClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KDTree<V> {
  
  private int _dimensions;
  private Node<Coordinate<V>> _root;

//  private Node<V> _root; // root node
//  private Integer _k; // the dimensions of the space (ex. 3-dimensional space)

  public KDTree(int dimensions, List<Coordinate<V>> coordinates) {
    this._dimensions = dimensions;
    this._root = createKDTree(new ArrayList<>(coordinates));
  }
  
//  public KDTree(Integer k) {
//    this._k =  k;
//  }
  
  public Node<Coordinate<V>> createKDTree(List<Coordinate<V>> coordinates) {
    return createNextLayer(1, coordinates);
  }
  
  public Node<Coordinate<V>> createNextLayer(int currentDim, List<Coordinate<V>> remainingCoordinates) {
    if (remainingCoordinates.size() == 0) {
      return new Node<>(null, null, null);
    } else {
      
      Comparator<Coordinate<V>> byDimension = Comparator.comparingDouble(coordinate -> coordinate.getCoordinateVal(currentDim));

      remainingCoordinates.sort(byDimension);

      int middleIndex = remainingCoordinates.size() / 2;

      // find median coordinate, coordinates lesser, coordinates greater than median
      Coordinate<V> medianCoordinate = remainingCoordinates.get(middleIndex);



//      List<List<Coordinate<V>>> splitResult = Utils.splitList(remainingCoordinates, middleIndex);
      List<Coordinate<V>> preSplit = new ArrayList<>();
      List<Coordinate<V>> postSplit = new ArrayList<>();
      int index = 0;
      for (Coordinate<V> element : remainingCoordinates) {
        if (index < middleIndex) { preSplit.add(element); }
        else if (index > middleIndex) { postSplit.add(element); }
        index++;
      }
      List<List<Coordinate<V>>> splitResult = new ArrayList<>();
      splitResult.add(preSplit);
      splitResult.add(postSplit);



      List<Coordinate<V>> lesserCoordinates = splitResult.get(0);
      List<Coordinate<V>> greaterCoordinates = splitResult.get(1);

      // calculate next dimension
      int nextDimension;
      if (currentDim + 1 > _dimensions) {
        nextDimension = 1;
      } else {
        nextDimension = currentDim + 1;
      }

      return new Node<>(
          // value
          medianCoordinate,
          // recursive call to fill left subtree
          createNextLayer(nextDimension, lesserCoordinates),
          // recursive call to fill right subtree
          createNextLayer(nextDimension, greaterCoordinates));
    }
  }
  
  public Node<Coordinate<V>> getRoot() {
    return _root;
  }

//  public Node<NodeValue> getMidpointToNode(int depth, List<NodeValue> userList) {
//    NodeValueHandlerClass userSorter = new NodeValueHandlerClass();
//    List<NodeValue> sortedUserList = userSorter.sortedByDimension(depth, userList);
//    int midPointIndex = (sortedUserList.size() / 2) - 1;
//    User userToNode = sortedUserList.get(midPointIndex);
//
//    List<NodeValue> leftList = sortedUserList.subList(0, midPointIndex);
//    List<NodeValue> rightList = sortedUserList.subList(midPointIndex + 1, sortedUserList.size());
//
//    return this.createNode(sortedUserList.get(midPointIndex), this.getMidpointToNode(depth + 1,
//        leftList), this.getMidpointToNode(depth + 1, rightList), depth);
//  }
//
//
//  public Node<NodeValue> createNode(NodeValue val, Node<NodeValue> left, Node<NodeValue> right, int depth) {
//    Node<NodeValue> newNode = new Node<>(val, depth, _k);
//
//    if (_root == null) {
//      _root = newNode;
//    }
//
//    newNode.setLesser(left);
//    newNode.setGreater(right);
//    left.setParent(newNode);
//    right.setParent(newNode);
//
//    return newNode;
//  }
//
//  public Node<V> nearestNeighbor(Node<V> root, Node<V> target, int depth) {
//    if (root == null) return null;
//
//    Node<V> nextBranch = null;
//    Node<V> otherBranch = null;
//    if (target[depth % _k] < root[depth % _k]) {
//      nextBranch = root.getLesser();
//      otherBranch = root.getGreater();
//    } else {
//      nextBranch = root.getGreater();
//      otherBranch = root.getLesser();
//    }
//
//    Node<V> temp = this.nearestNeighbor(nextBranch, target, depth + 1);
//    Node<V> best = null;
//    if (this.NodeDistance(target, temp) < this.NodeDistance(target, root)) {
//      best = temp;
//    } else {
//      best = root;
//    }
//
//    double radius = this.NodeDistance(target, best);
//    double dist = target[depth % _k] - root[depth % _k];
//
//    if (radius >= dist) {
//      temp = this.nearestNeighbor(otherBranch, target, depth + 1);
//      if (this.NodeDistance(target, temp) < this.NodeDistance(target, best)) {
//        best = temp;
//      } else {
//        best = best;
//      }
//    }
//
//    return best;
//  }
//
  // takes in two nodes of the tree and returns the euclidean distance between them
//  public double NodeDistance(Node<V> node1, Node<V> node2) {
//    V key1 = node1.getKey();
//    V key2 = node2.getKey();
//
//    return euclideanDist();
//  }
//
//  public double euclideanDist(double x, double y, double z, double x2, double y2, double z2) {
//    double newX = (x - x2) * (x - x2);
//    double newY = (y - y2) * (y - y2);
//    double newZ = (z - z2) * (z - z2);
//    return Math.sqrt(newX + newY + newZ);
//  }








//  public List<V> getKNearestNeighbors() {
//    ArrayList<V> kNearestNeighbors = new ArrayList<V>();
//
//
//
//
//
//    return kNearestNeighbors;
//  }

















  // kinda useless ig
//  public void addNode(V val) {
//    if (val == null) {
//      return; // exit
//    }
//    if (this._root == null) { // tree empty
//      this._root = new Node<V>(val, this._k, 0); // create root with val
//      return; // exit
//    }
//
//    Node<V> currNode = this._root; // the current node we're looking at in the traversal
//
//    while (true) { // goes forever until reaches a break statement
//
//      int axis = currNode.getDepth() % _k; // what attribute we're looking at at this level
//      if(this._comparator.compare(currNode.getKey(), val, axis) >= 0) { // lesser          !!!!!!!!!!!!!!!!!!!--figure out way to compare--!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        if(currNode.getLesser() == null) { // no left child
//          Node<V> newNode = new Node<>(val, currNode.getDepth() + 1, _k); // create new node
//          newNode.setParent(currNode); // child points at parent
//          currNode.setLesser(newNode); // parent points at child
//          break; // exit loop
//        }
//        currNode = currNode.getLesser(); // if there is a left child, set currNode to it
//      } else { //greater
//        if(currNode.getGreater() == null) { // no right child
//          Node<V> newNode = new Node<>(val, currNode.getDepth() + 1, _k); // create new node
//          newNode.setParent(currNode); // child points at parent
//          currNode.setGreater(newNode); // parent points at child
//          break; // exit loop
//        }
//        currNode = currNode.getGreater(); // if there is a right child, set currNode to it
//      }
//    }
//  }

}
