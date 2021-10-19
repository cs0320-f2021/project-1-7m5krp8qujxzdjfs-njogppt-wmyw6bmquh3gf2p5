package edu.brown.cs.student.main.KDTree;

public class Node<V> {

  private NodeValue _key; // generic
  private int _depth = 0; // location of this node within the tree
  private static int _k = 0; // the dimensions of the space (ex. 3-dimensional space)

  private V _val;
  private Node<V> _nodeL;
  private Node<V> _nodeR;

  private Node<NodeValue<V>> _parent = null;
  private Node<NodeValue<V>> _lesser = null; // left child node
  private Node<NodeValue<V>> _greater = null; // right child node

  public Node(V value, Node<V> left, Node<V> right) {
    this._val = value;
    this._nodeL = left;
    this._nodeR = right;
  }

  public Node(NodeValue key, int depth, int k) {
    this._key = key;
    this._k = k;
    this._depth = depth;
  }

  public Node() {}

  public Node<NodeValue<V>> getParent() { return _parent;}

  public void setParent(Node<NodeValue<V>> parent) { this._parent = parent; }

  public Node<NodeValue<V>> getLesser() { return _lesser; }

  public void setLesser(Node<NodeValue<V>> lesser) { this._lesser = lesser; }

  public Node<NodeValue<V>> getGreater() { return _greater; }

  public void setGreater(Node<NodeValue<V>> greater) { this._greater = greater; }

  public int getDepth() { return _depth; }

  public void setDepth(int depth) { this._depth = depth; }

  public V getValue() { return _val; }

  public Node<V> getLeft() { return _nodeL; }

  public Node<V> getRight() { return _nodeR; }

  public NodeValue getKey() { return _key; }

  public void setKey(NodeValue key) { this._key = key; }
}
