package edu.brown.cs.student.main.KDTree;

// Class to create a mapping of value to distance.
public class Map<V> {
    private final V _value;
    private final Double _dist;

    public Map(V val, Double dist) { this._value = val; this._dist = dist; }
    public Double getDist() { return _dist; }
    public V getVal() { return _value; }
}
