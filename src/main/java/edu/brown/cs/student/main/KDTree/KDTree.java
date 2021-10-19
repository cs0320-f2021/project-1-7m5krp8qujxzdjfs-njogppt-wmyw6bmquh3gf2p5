package edu.brown.cs.student.main.KDTree;

import java.util.ArrayList; import java.util.Comparator; import java.util.List; import java.util.PriorityQueue;
import edu.brown.cs.student.main.NodeValueHandlerClass;

public class KDTree<V> {

  private int _k; // the dimensions of the space (ex. 3-dimensional space)
  private Integer _dimension;
  private Node<NodeValue<V>> _root; // root node
  private List<NodeValue<V>> unsorted;
//  private Node<V> _root;

  public KDTree(int dimensions, List<NodeValue<V>> nodeValues) {
    this._k = dimensions;
    this._root = this.generateOnStart(new ArrayList<>(nodeValues), 1);
    this.unsorted = nodeValues;
  }

  public KDTree(Integer k) { this._k =  k; }

  public Node<NodeValue<V>> getRoot() { return _root; }
  
  // return List of "k" nearest neighbors to "targ"
    public List<NodeValue<V>> getKNN(int k, NodeValue<V> targ) {
        Comparator<Map<NodeValue<V>>> byDistance = Comparator.comparing(Map::getDist);
        Comparator<Map<NodeValue<V>>> byReverseDistance = Comparator.comparing(keyDist -> -1 * keyDist.getDist());

        PriorityQueue<Map<NodeValue<V>>> kNearestNeighborsQueue = new PriorityQueue<>(byDistance);
        PriorityQueue<Map<NodeValue<V>>> kNearestNeighborsReverse = new PriorityQueue<>(byReverseDistance);

        this.searchAlgorithm(_root, k+1, targ, kNearestNeighborsQueue, kNearestNeighborsReverse, 1); // the big boi

        List<NodeValue<V>> returnNeighbors = new ArrayList<>();
        while (returnNeighbors.size() < k) {
            Map<NodeValue<V>> curr = kNearestNeighborsQueue.remove();
            if (!(curr.getVal().getId().equals(targ.getId()))) { returnNeighbors.add(curr.getVal()); }
        } return returnNeighbors;
    }

  private Node<NodeValue<V>> generateOnStart(List<NodeValue<V>> nodeValuesLeft, int dim) {
        if (nodeValuesLeft.size() != 0) {
            int indexMid = nodeValuesLeft.size() / 2;
            Comparator<NodeValue<V>> byDimension = Comparator.comparingDouble(nodeValues -> nodeValues.getSingleNodeValue(dim));
            nodeValuesLeft.sort(byDimension);
            NodeValue<V> medianNodeValues = nodeValuesLeft.get(indexMid);

            int i = 0;
            List<NodeValue<V>> first = new ArrayList<>();
            List<NodeValue<V>> second = new ArrayList<>();
            for (NodeValue<V> element : nodeValuesLeft) {
                if (i < indexMid) { first.add(element); }
                else if (i > indexMid) { second.add(element); }
                i++;
            } List<List<NodeValue<V>>> finalResult = new ArrayList<>();
            finalResult.add(first);
            finalResult.add(second);

            List<NodeValue<V>> less = finalResult.get(0);
            List<NodeValue<V>> more = finalResult.get(1);

            int dimNext; // calculate next dimension
            if (dim + 1 > _k) { dimNext = 1; }
            else { dimNext = dim + 1; }

            return new Node<>(medianNodeValues, generateOnStart(less, dimNext), generateOnStart(more, dimNext));
        } else { return new Node<>(null, null, null); }
    }

  // recursive search algorithm, private bc only used by getKNN()
    private void searchAlgorithm(Node<NodeValue<V>> root, int k, NodeValue<V> targ, PriorityQueue<Map<NodeValue<V>>> neighbors, PriorityQueue<Map<NodeValue<V>>> rev, int dim) {
        if (!(root.getValue() == null || k == 0)) {
            double currDistSq = 0;
            double distCurr;

            int i = 1;
            try { while (true) { currDistSq += Math.pow(root.getValue().getSingleNodeValue(i) - targ.getSingleNodeValue(i), 2); i++; } }
            catch (IndexOutOfBoundsException e) { distCurr = Math.sqrt(currDistSq); }

            NodeValue<V> rootId = root.getValue();
            Map<NodeValue<V>> rootStarDist = new Map<>(rootId, distCurr);

            if (neighbors.size() < k || distCurr <= rev.peek().getDist()) { neighbors.add(rootStarDist); rev.add(rootStarDist); }

            int nextDimension; // get the next dimension
            if (dim + 1 > targ.getNodeValue().size()) { nextDimension = 1; }
            else { nextDimension = dim + 1; }

            if (neighbors.size() < k) {
                this.searchAlgorithm(root.getLeft(), k, targ, neighbors, rev, nextDimension);
                this.searchAlgorithm(root.getRight(), k, targ, neighbors, rev, nextDimension);
            } else {
                double maxEuclideanDistance = rev.peek().getDist();
                double axisDistance = Math.abs(targ.getSingleNodeValue(dim) - root.getValue().getSingleNodeValue(dim));
                if (maxEuclideanDistance >= axisDistance) {
                    this.searchAlgorithm(root.getLeft(), k, targ, neighbors, rev, nextDimension);
                    this.searchAlgorithm(root.getRight(), k, targ, neighbors, rev, nextDimension);
                } else {
                    if (root.getValue().getSingleNodeValue(dim) < targ.getSingleNodeValue(dim)) { searchAlgorithm(root.getRight(), k, targ, neighbors, rev, nextDimension); }
                    else if (root.getValue().getSingleNodeValue(dim) > targ.getSingleNodeValue(dim)) { searchAlgorithm(root.getLeft(), k, targ, neighbors, rev, nextDimension); }
                }
            }
        }
    }

  private Node<NodeValue<V>> getMidpointToNode(int depth, List<NodeValue<V>> nodeValueList) {
    NodeValueHandlerClass nodeValueSorter = new NodeValueHandlerClass();
    List<NodeValue> sortedNodeValueList = nodeValueSorter.sortedByDimension(depth, new ArrayList<>());
    int midPointIndex = (sortedNodeValueList.size() / 2) - 1;
    NodeValue nodeValueToNode = sortedNodeValueList.get(midPointIndex);
    List<NodeValue> leftList = sortedNodeValueList.subList(0, midPointIndex);
    List<NodeValue> rightList = sortedNodeValueList.subList(midPointIndex + 1, sortedNodeValueList.size());
//      return this.createNode(sortedNodeValueList.get(midPointIndex), this.getMidpointToNode(depth + 1, leftList, this.getMidpointToNode(depth + 1, rightList), depth));
    return null;
  }

  private Node<NodeValue> createNode(NodeValue val, Node<NodeValue<V>> left, Node<NodeValue<V>> right, int depth) {
    Node<V> newNode = new Node<>(val, depth, _k);
    if (_root == null) { _root = new Node<>(); }
    newNode.setLesser(left);
    newNode.setGreater(right);
    left.setParent(new Node<>());
    right.setParent(new Node<>());
//      return newNode;
    return null;
  }

  private Node<V> nearestNeighbor(Node<V> root, Node<NodeValue<V>> target, int depth) {
    if (root == null) return null;
    Node<NodeValue<V>> nextBranch = null;
    Node<NodeValue<V>> otherBranch = null;
//      if (target[depth % _k] < root[depth % _k]) {
    if (true) { nextBranch = root.getLesser(); otherBranch = root.getGreater(); }
    else { nextBranch = root.getGreater(); otherBranch = root.getLesser(); }
    Node<V> temp = this.nearestNeighbor(new Node<>(), target, depth + 1);
    Node<V> best = null;
    if (this.NodeDistance(target, new Node<NodeValue<V>>()) < this.NodeDistance(target, new Node<NodeValue<V>>())) { best = temp; }
    else { best = root; }
    double radius = this.NodeDistance(target, new Node<>());
//      double dist = target[depth % _k] - root[depth % _k];
    double dist = 0.0;
    if (radius >= dist) {
      temp = this.nearestNeighbor(new Node<>(), target, depth + 1);
      if (this.NodeDistance(target, new Node<NodeValue<V>>()) < this.NodeDistance(target, new Node<NodeValue<V>>())) { best = temp; }
      else { best = best; }
    } return best;
  }

  // takes in two nodes of the tree and returns the euclidean distance between them
  private double NodeDistance(Node<NodeValue<V>> node1, Node<NodeValue<V>> node2) {
//      V key1 = node1.getVal();
//      V key2 = node2.getVal();
    return euclideanDist(1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
  }

  private double euclideanDist(double x, double y, double z, double x2, double y2, double z2) {
    double newX = (x - x2) * (x - x2);
    double newY = (y - y2) * (y - y2);
    double newZ = (z - z2) * (z - z2);
    return Math.sqrt(newX + newY + newZ);
  }

  // kinda useless ig
  private void addNode(V val) {
    if (val == null) { return; }// exit
    if (this._root == null) { this._root = new Node<NodeValue<V>>(); return; } // create root with val and exit
    Node<NodeValue<V>> currNode = this._root; // the current node we're looking at in the traversal
    while (true) { // goes forever until reaches a break statement
      int axis = currNode.getDepth() % _k; // what attribute we're looking at at this level
//              if (this._comparator.compare(currNode.getVal(), val, axis) >= 0) { // lesser
      if (true) {
        if(currNode.getLesser() == null) { // no left child
          Node<V> newNode = new Node<V>((NodeValue) val, currNode.getDepth() + 1, _k); // create new node
          newNode.setParent(currNode); // child points at parent
          currNode.setLesser(new Node<NodeValue<NodeValue<V>>>()); // parent points at child
          break; // exit loop
        } currNode = currNode; // if there is a left child, set currNode to it
      } else { //greater
        if(currNode.getGreater() == null) { // no right child
          Node<V> newNode = new Node<V>((NodeValue) val, currNode.getDepth() + 1, _k); // create new node
          newNode.setParent(currNode); // child points at parent
          currNode.setGreater(new Node<NodeValue<NodeValue<V>>>()); // parent points at child
          break; // exit loop
        } currNode = currNode; // if there is a right child, set currNode to it
      }
    }
  }

  /**
   * Finds the user in the KDTree with the given ID.
   * @param id - The id of the target user.
   * @return - The user as a NodeValue
   */
  public NodeValue<V> getTargetFromId(Integer id) {
    for (NodeValue<V> node : unsorted) {
      if (node.getId().equals(id)) {
        return node;
      }
    }
    throw new RuntimeException("ERROR: There is no user with this ID.");
  }
}
