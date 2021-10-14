package edu.brown.cs.student.main.KDTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Class to perform KdTreeSearch over coordinates of specified type ID.
// @param <V> Any type for the ID of the Coordinate that is specified when constructing a KdTreeSearch.
public class KdTreeSearch<V> {

    /** Get the first few IDs of KeyDistances within the passed threshold with the least distances, randomizing the order of any with tied distances, by using a Nearest Neighbors Algorithm.
     @param k An int threshold representing the maximum number of IDs that can be returned.
     @param targetPoint A Coordinate of type specified when constructing the KdTreeSearch representing the point around which all distances will be calculated.
     @param root A node representing the KdTree on which to apply the Nearest Neighbors Search algorithm.
     @param excludeTarget A Boolean representing whether the targetPoint should be included or excluded within the List of IDs returned
     @return A List of IDs of type specified when constructing the KdTreeSearch.
     */
    public List<NodeValue<V>> getNearestNeighborsResult(int k, NodeValue<V> targetPoint, Node<NodeValue<V>> root, Boolean excludeTarget) {
        if (excludeTarget) { k = k + 1; } // this is to account for the fact that the neighbors will exclude the target star
        Comparator<KeyDistance<NodeValue<V>>> byDistance = Comparator.comparing(KeyDistance::getDistance);

        Comparator<KeyDistance<NodeValue<V>>> byReverseDistance = Comparator.comparing(keyDist -> -1 * keyDist.getDistance());
        PriorityQueue<KeyDistance<NodeValue<V>>> kNearestNeighborsQueue = new PriorityQueue<>(byDistance);
        // maintaining a reverse ordered PriorityQueue to facilitate ease of peeking maximum known neighbor distance
        PriorityQueue<KeyDistance<NodeValue<V>>> kNearestNeighborsReverse = new PriorityQueue<>(byReverseDistance);

        performKdTreeSearchNeighbors(root, k, targetPoint, kNearestNeighborsQueue, kNearestNeighborsReverse, 1);

        List<KeyDistance<NodeValue<V>>> kNearestNeighborsList = new ArrayList<>();

        // begin conversion process of PriorityQueue into ListNaiveSearch to handle randomness of tied distance star IDs
        while (!kNearestNeighborsQueue.isEmpty()) {
          KeyDistance<NodeValue<V>> keyDist = kNearestNeighborsQueue.remove();

          if (excludeTarget) { if (!(keyDist.getKey().getId().equals(targetPoint.getId()))) { kNearestNeighborsList.add(keyDist); } }
          else { kNearestNeighborsList.add(keyDist); } // exclude the target star ID if applicable
        }

        ListNaiveSearch<NodeValue<V>> kNN = new ListNaiveSearch<>(kNearestNeighborsList);
        if (excludeTarget) { return kNN.getNaiveNearestNeighbors(k - 1); } // discount for the previous offset since we still want k IDs at the end
        else { return kNN.getNaiveNearestNeighbors(k); }
    }

    /** Update the PriorityQueue of KeyDistance with valid Neighbors on the path to the target in the passed Node tree.
     @param k An int threshold representing the maximum number of IDs upto which Node values must be indiscriminately added to the PriorityQueue.
     @param targetPoint A Coordinate of type specified when constructing the KdTreeSearch representing the point around which all distances are calculated.
     @param root A Node representing the sub-KdTree on which to recur the Nearest Neighbors Search algorithm.
     @param dim The current cutting dimension in the recursion.
     @param kNearestNeighbors The PriorityQueue to which valid KeyDistances should be added in ascending order of their distances.
     @param kNNReverse The PriorityQueue to which valid KeyDistances should be added in descending order of their distances to peek the maximum valid neighbor at that level of iteration.
     */
    void performKdTreeSearchNeighbors(Node<NodeValue<V>> root, int k, NodeValue<V> targetPoint, PriorityQueue<KeyDistance<NodeValue<V>>> kNearestNeighbors,
                                      PriorityQueue<KeyDistance<NodeValue<V>>> kNNReverse, int dim) {
        if (!(root.getValue() == null || k == 0)) {
          double currentNodeDistanceSquared = 0;
          double currentNodeDistance;

          int index = 1;
          // calculate the distance, which works for coordinates in any dimension
          try {
            while (true) {
              currentNodeDistanceSquared += Math.pow(root.getValue().getSingleNodeValue(index) - targetPoint.getSingleNodeValue(index), 2);
              index++;
            }
          } catch (IndexOutOfBoundsException e) { currentNodeDistance = Math.sqrt(currentNodeDistanceSquared); }

          NodeValue<V> rootId = root.getValue();

          KeyDistance<NodeValue<V>> rootStarDist = new KeyDistance<>(rootId, currentNodeDistance);

          // If not all k neighbors have been filled yet, indiscriminately add to the PriorityQueue
          if (kNearestNeighbors.size() < k || currentNodeDistance <= kNNReverse.peek().getDistance()) {
            kNearestNeighbors.add(rootStarDist);
            kNNReverse.add(rootStarDist);
          }

          int nextDimension; // get the next dimension
          if (dim + 1 > targetPoint.getNodeValue().size()) { nextDimension = 1; }
          else { nextDimension = dim + 1; }

          if (kNearestNeighbors.size() < k) { // If not all k neighbors have been filled yet, recurse in both children
            performKdTreeSearchNeighbors(root.getLeft(), k, targetPoint, kNearestNeighbors, kNNReverse, nextDimension);
            performKdTreeSearchNeighbors(root.getRight(), k, targetPoint, kNearestNeighbors, kNNReverse, nextDimension);
          } else {
            double maxEuclideanDistance = kNNReverse.peek().getDistance();

            double axisDistance = Math.abs(targetPoint.getSingleNodeValue(dim) - root.getValue().getSingleNodeValue(dim));

            if (maxEuclideanDistance >= axisDistance) { // recur on both children
              performKdTreeSearchNeighbors(root.getLeft(), k, targetPoint, kNearestNeighbors, kNNReverse, nextDimension);
              performKdTreeSearchNeighbors(root.getRight(), k, targetPoint, kNearestNeighbors, kNNReverse, nextDimension);
            } else {
              if (root.getValue().getSingleNodeValue(dim) < targetPoint.getSingleNodeValue(dim)) {
                // If the current node's coordinate on the relevant axis is less than target's coordinate on the relevant axis, recur on the right child.
                performKdTreeSearchNeighbors(root.getRight(), k, targetPoint, kNearestNeighbors, kNNReverse, nextDimension);
              } else if (root.getValue().getSingleNodeValue(dim) > targetPoint.getSingleNodeValue(dim)) {
                // Else if the current node's coordinate on the relevant axis is greater than the target's coordinate on the relevant axis, recur on the left child.
                performKdTreeSearchNeighbors(root.getLeft(), k, targetPoint, kNearestNeighbors, kNNReverse, nextDimension);
              }
            }
          }
        }
    }
}