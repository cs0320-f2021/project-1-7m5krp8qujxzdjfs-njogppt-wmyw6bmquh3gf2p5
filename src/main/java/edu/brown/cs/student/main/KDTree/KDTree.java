package edu.brown.cs.student.main.KDTree;

import java.util.ArrayList; import java.util.Comparator; import java.util.List;

public class KDTree<V> {
  
  private int _dimensions; // the dimensions of the space (ex. 3-dimensional space)
//  private Integer _k;
  private Node<NodeValue<V>> _root; // root node
//  private Node<V> _root;

  public KDTree(int dimensions, List<NodeValue<V>> nodeValues) {
    this._dimensions = dimensions;
    this._root = generateOnStart(new ArrayList<>(nodeValues));
  }
  
//  public KDTree(Integer k) { this._k =  k; }

  public Node<NodeValue<V>> getRoot() { return _root; }
  
  public Node<NodeValue<V>> generateOnStart(List<NodeValue<V>> nodeValues) { return newLevel(nodeValues, 1); }
  
  public Node<NodeValue<V>> newLevel(List<NodeValue<V>> nodeValuesLeft, int dim) {
    if (nodeValuesLeft.size() != 0) {
      int indexMid = nodeValuesLeft.size() / 2;
      Comparator<NodeValue<V>> byDimension = Comparator.comparingDouble(nodeValues -> nodeValues.getSingleNodeValue(dim));
      nodeValuesLeft.sort(byDimension);

      NodeValue<V> medianNodeValues = nodeValuesLeft.get(indexMid); // find median nodeValue, nodeValues lesser, nodeValues greater than median

//      List<List<NodeValue<V>>> splitResult = Utils.splitList(remainingNodeValues, middleIndex);
      int i = 0;
      List<NodeValue<V>> first = new ArrayList<>();
      List<NodeValue<V>> second = new ArrayList<>();
      for (NodeValue<V> element : nodeValuesLeft) {
        if (i < indexMid) { first.add(element); }
        else if (i > indexMid) { second.add(element); }
        i++;
      }
      List<List<NodeValue<V>>> finalResult = new ArrayList<>();
      finalResult.add(first);
      finalResult.add(second);

      List<NodeValue<V>> lesserNodeValues = finalResult.get(0);
      List<NodeValue<V>> greaterNodeValues = finalResult.get(1);

      // calculate next dimension
      int dimNext;
      if (dim + 1 > _dimensions) { dimNext = 1; }
      else { dimNext = dim + 1; }

      return new Node<>(medianNodeValues, newLevel(lesserNodeValues, dimNext), newLevel(greaterNodeValues, dimNext));
    } else { return new Node<>(null, null, null); }
  }

//  public Node<NodeValue> getMidpointToNode(int depth, List<NodeValue> nodeValueList) {
//    NodeValueHandlerClass nodeValueSorter = new NodeValueHandlerClass();
//    List<NodeValue> sortedNodeValueList = nodeValueSorter.sortedByDimension(depth, nodeValueList);
//    int midPointIndex = (sortedNodeValueList.size() / 2) - 1;
//    NodeValue nodeValueToNode = sortedNodeValueList.get(midPointIndex);
//
//    List<NodeValue> leftList = sortedNodeValueList.subList(0, midPointIndex);
//    List<NodeValue> rightList = sortedNodeValueList.subList(midPointIndex + 1, sortedNodeValueList.size());
//
//    return this.createNode(sortedNodeValueList.get(midPointIndex), this.getMidpointToNode(depth + 1,
//        leftList), this.getMidpointToNode(depth + 1, rightList), depth);
//  }
//
//
//  public Node<NodeValue> createNode(NodeValue val, Node<NodeValue> left, Node<NodeValue> right, int depth) {
//    Node<NodeValue> newNode = new Node<>(val, depth, _k);
//
//    if (_root == null) { _root = newNode; }
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
