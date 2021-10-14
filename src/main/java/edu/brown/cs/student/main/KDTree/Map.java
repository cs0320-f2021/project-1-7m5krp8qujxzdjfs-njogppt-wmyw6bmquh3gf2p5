package edu.brown.cs.student.main.KDTree;

// Class to create a mapping of ID to distance.
public class Map<T> {
    private final T key; private final Double distance;

    public Map(T key, Double distance) { this.key = key; this.distance = distance; }
    public Double getDistance() { return distance; }
    public T getKey() { return key; }
}