package edu.brown.cs.student.main;

public class Node<K> {

  private K _key; // generic
  private int _depth = 0; // location of this node within the tree

  private static int _k = 0; // the dimensions of the space (ex. 3-dimensional space)

  private Node<K> _parent = null;
  private Node<K> _lesser = null; // left child node
  private Node<K> _greater = null; // right child node



  public Node(K key, int depth, int k) {
    this._key = key;
    this._k = k;
    this._depth = depth;
  }

  public Node<K> getParent() {
    return _parent;
  }

  public void setParent(Node<K> parent) {
    this._parent = parent;
  }

  public Node<K> getLesser() {
    return _lesser;
  }

  public void setLesser(Node<K> lesser) {
    this._lesser = lesser;
  }

  public Node<K> getGreater() {
    return _greater;
  }

  public void setGreater(Node<K> greater) {
    this._greater = greater;
  }

  public int getDepth() {
    return _depth;
  }
  public void setDepth(int depth) {
    this._depth = depth;
  }

  public K getKey() {
    return _key;
  }

  public void setKey(K key) {
    this._key = key;
  }
}