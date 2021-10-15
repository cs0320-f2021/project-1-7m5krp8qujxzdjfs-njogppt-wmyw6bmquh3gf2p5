package edu.brown.cs.student.main.KDTree;

// Class to create a mapping of ID to distance.
public class Map<V> {
    private final V key; private final Double distance;

    public Map(V key, Double distance) { this.key = key; this.distance = distance; }
    public Double getDistance() { return distance; }
    public V getKey() { return key; }
}
