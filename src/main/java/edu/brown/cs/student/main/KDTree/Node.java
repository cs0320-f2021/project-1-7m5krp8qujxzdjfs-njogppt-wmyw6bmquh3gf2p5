package edu.brown.cs.student.main.KDTree;

public class Node<V> {

  private V _key; // generic
  private int _depth = 0; // location of this node within the tree

  private static int _k = 0; // the dimensions of the space (ex. 3-dimensional space)

  private Node<V> _parent = null;
  private Node<V> _lesser = null; // left child node
  private Node<V> _greater = null; // right child node



  public Node(V key, int depth, int k) {
    this._key = key;
    this._k = k;
    this._depth = depth;
  }

  public Node<V> getParent() {
    return _parent;
  }

  public void setParent(Node<V> parent) {
    this._parent = parent;
  }

  public Node<V> getLesser() {
    return _lesser;
  }

  public void setLesser(Node<V> lesser) {
    this._lesser = lesser;
  }

  public Node<V> getGreater() {
    return _greater;
  }

  public void setGreater(Node<V> greater) {
    this._greater = greater;
  }

  public int getDepth() {
    return _depth;
  }
  public void setDepth(int depth) {
    this._depth = depth;
  }

  public V getKey() {
    return _key;
  }

  public void setKey(V key) {
    this._key = key;
  }


}
