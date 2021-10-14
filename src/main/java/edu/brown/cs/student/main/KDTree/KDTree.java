package edu.brown.cs.student.main.KDTree;

import java.util.ArrayList; import java.util.Comparator; import java.util.List; import java.util.PriorityQueue;

public class KDTree<V> {
  
    private int _k; // the dimensions of the space (ex. 3-dimensional space)
//  private Integer _dimension;
    private Node<NodeValue<V>> _root; // root node
//  private Node<V> _root;

    public KDTree(int dimensions, List<NodeValue<V>> nodeValues) {
        this._k = dimensions;
        this._root = this.generateOnStart(new ArrayList<>(nodeValues), 1);
    }
  
//    public KDTree(Integer k) { this._k =  k; }

    public Node<NodeValue<V>> getRoot() { return _root; }
  
    public Node<NodeValue<V>> generateOnStart(List<NodeValue<V>> nodeValuesLeft, int dim) {
        if (nodeValuesLeft.size() != 0) {
            int indexMid = nodeValuesLeft.size() / 2;
            Comparator<NodeValue<V>> byDimension = Comparator.comparingDouble(nodeValues -> nodeValues.getSingleNodeValue(dim));
            nodeValuesLeft.sort(byDimension);
            NodeValue<V> medianNodeValues = nodeValuesLeft.get(indexMid); // find median nodeValue, nodeValues lesser, nodeValues greater than median

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

            List<NodeValue<V>> lesserNodeValues = finalResult.get(0);
            List<NodeValue<V>> greaterNodeValues = finalResult.get(1);

            int dimNext; // calculate next dimension
            if (dim + 1 > _k) { dimNext = 1; }
            else { dimNext = dim + 1; }

            return new Node<>(medianNodeValues, generateOnStart(lesserNodeValues, dimNext), generateOnStart(greaterNodeValues, dimNext));
        } else { return new Node<>(null, null, null); }
    }

    /** Get the first few IDs of KeyDistances within the passed threshold with the least distances, randomizing the order of any with tied distances, by using a Nearest Neighbors Algorithm.
     @param k An int threshold representing the maximum number of IDs that can be returned.
     @param targetPoint A Coordinate of type specified when constructing the KdTreeSearch representing the point around which all distances will be calculated.
     @param excludeTarget A Boolean representing whether the targetPoint should be included or excluded within the List of IDs returned
     @return A List of IDs of type specified when constructing the KdTreeSearch.
     */
    public List<NodeValue<V>> getKNN(int k, NodeValue<V> targetPoint, Boolean excludeTarget) {
        if (excludeTarget) { k++; } // this is to account for the fact that the neighbors will exclude the target star
        Comparator<KeyDistance<NodeValue<V>>> byDistance = Comparator.comparing(KeyDistance::getDistance);

        Comparator<KeyDistance<NodeValue<V>>> byReverseDistance = Comparator.comparing(keyDist -> -1 * keyDist.getDistance());
        PriorityQueue<KeyDistance<NodeValue<V>>> kNearestNeighborsQueue = new PriorityQueue<>(byDistance);
        // maintaining a reverse ordered PriorityQueue to facilitate ease of peeking maximum known neighbor distance
        PriorityQueue<KeyDistance<NodeValue<V>>> kNearestNeighborsReverse = new PriorityQueue<>(byReverseDistance);

        this.searchAlgorithm(_root, k, targetPoint, kNearestNeighborsQueue, kNearestNeighborsReverse, 1); // the big boi

        List<NodeValue<V>> returnNeighbors = new ArrayList<>();
        if (excludeTarget) { k--; } // reset k to account for previous offset
        while (returnNeighbors.size() < k) {
          KeyDistance<NodeValue<V>> curr = kNearestNeighborsQueue.remove();
          if (excludeTarget && !(curr.getKey().getId().equals(targetPoint.getId()))) { returnNeighbors.add(curr.getKey()); }
          else if (!excludeTarget) { returnNeighbors.add(curr.getKey()); } // exclude the target star ID if applicable
        } return returnNeighbors;

//        List<KeyDistance<NodeValue<V>>> kNearestNeighborsList = new ArrayList<>();
//
//        // begin conversion process of PriorityQueue into ListNaiveSearch to handle randomness of tied distance star IDs
//        while (!kNearestNeighborsQueue.isEmpty()) {
//          KeyDistance<NodeValue<V>> keyDist = kNearestNeighborsQueue.remove();
//
//          if (excludeTarget) {
//            if ( !(keyDist.getKey().getId().equals(targetPoint.getId())) ) { kNearestNeighborsList.add(keyDist); }
//          }
//          else { kNearestNeighborsList.add(keyDist); } // exclude the target star ID if applicable
//        }
//
//        ListNaiveSearch<NodeValue<V>> kNN = new ListNaiveSearch<>(kNearestNeighborsList);
//        if (excludeTarget) { return kNN.getNaiveNearestNeighbors(k - 1); } // discount for the previous offset since we still want k IDs at the end
//        else { return kNN.getNaiveNearestNeighbors(k); }
    }

    /** Update the PriorityQueue of KeyDistance with valid Neighbors on the path to the target in the passed Node tree.
     @param k An int threshold representing the maximum number of IDs upto which Node values must be indiscriminately added to the PriorityQueue.
     @param targ A Coordinate of type specified when constructing the KdTreeSearch representing the point around which all distances are calculated.
     @param root A Node representing the sub-KdTree on which to recur the Nearest Neighbors Search algorithm.
     @param dim The current cutting dimension in the recursion.
     @param neighbors The PriorityQueue to which valid KeyDistances should be added in ascending order of their distances.
     @param rev The PriorityQueue to which valid KeyDistances should be added in descending order of their distances to peek the maximum valid neighbor at that level of iteration.
     */
    public void searchAlgorithm(Node<NodeValue<V>> root, int k, NodeValue<V> targ, PriorityQueue<KeyDistance<NodeValue<V>>> neighbors, PriorityQueue<KeyDistance<NodeValue<V>>> rev, int dim) {
        if (!(root.getValue() == null || k == 0)) {
            double currDistSq = 0;
            double distCurr;

            int i = 1; // calculate the distance, which works for coordinates in any dimension
            try { while (true) { currDistSq += Math.pow(root.getValue().getSingleNodeValue(i) - targ.getSingleNodeValue(i), 2); i++; } }
            catch (IndexOutOfBoundsException e) { distCurr = Math.sqrt(currDistSq); }

            NodeValue<V> rootId = root.getValue();
            KeyDistance<NodeValue<V>> rootStarDist = new KeyDistance<>(rootId, distCurr);

            // If not all k neighbors have been filled yet, indiscriminately add to the PriorityQueue
            if (neighbors.size() < k || distCurr <= rev.peek().getDistance()) { neighbors.add(rootStarDist); rev.add(rootStarDist); }

            int nextDimension; // get the next dimension
            if (dim + 1 > targ.getNodeValue().size()) { nextDimension = 1; }
            else { nextDimension = dim + 1; }

            if (neighbors.size() < k) { // If not all k neighbors have been filled yet, recurse in both children
                this.searchAlgorithm(root.getLeft(), k, targ, neighbors, rev, nextDimension);
                this.searchAlgorithm(root.getRight(), k, targ, neighbors, rev, nextDimension);
            } else {
                double maxEuclideanDistance = rev.peek().getDistance();
                double axisDistance = Math.abs(targ.getSingleNodeValue(dim) - root.getValue().getSingleNodeValue(dim));
                if (maxEuclideanDistance >= axisDistance) { // recur on both children
                    this.searchAlgorithm(root.getLeft(), k, targ, neighbors, rev, nextDimension);
                    this.searchAlgorithm(root.getRight(), k, targ, neighbors, rev, nextDimension);
                } else {
                    if (root.getValue().getSingleNodeValue(dim) < targ.getSingleNodeValue(dim)) { searchAlgorithm(root.getRight(), k, targ, neighbors, rev, nextDimension); }
                    else if (root.getValue().getSingleNodeValue(dim) > targ.getSingleNodeValue(dim)) { searchAlgorithm(root.getLeft(), k, targ, neighbors, rev, nextDimension); }
                }
            }
        }
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
