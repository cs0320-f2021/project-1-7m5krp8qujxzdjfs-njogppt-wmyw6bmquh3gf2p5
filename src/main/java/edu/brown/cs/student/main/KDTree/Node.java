package edu.brown.cs.student.main.KDTree;

public class Node<NodeValue> {

  private NodeValue _key; // generic
  private int _depth = 0; // location of this node within the tree

  private static int _k = 0; // the dimensions of the space (ex. 3-dimensional space)

  private Node<NodeValue> _parent = null;
  private Node<NodeValue> _lesser = null; // left child node
  private Node<NodeValue> _greater = null; // right child node



  public Node(NodeValue key, int depth, int k) {
    this._key = key;
    this._k = k;
    this._depth = depth;
  }

  public Node<NodeValue> getParent() {
    return _parent;
  }

  public void setParent(Node<NodeValue> parent) {
    this._parent = parent;
  }

  public Node<NodeValue> getLesser() {
    return _lesser;
  }

  public void setLesser(Node<NodeValue> lesser) {
    this._lesser = lesser;
  }

  public Node<NodeValue> getGreater() {
    return _greater;
  }

  public void setGreater(Node<NodeValue> greater) {
    this._greater = greater;
  }

  public int getDepth() {
    return _depth;
  }
  public void setDepth(int depth) {
    this._depth = depth;
  }

  public NodeValue getKey() {
    return _key;
  }

  public void setKey(NodeValue key) {
    this._key = key;
  }


}
