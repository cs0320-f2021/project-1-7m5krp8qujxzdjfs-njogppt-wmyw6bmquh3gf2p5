package edu.brown.cs.student.main;

public class Node<K> {
  private K key;
  private int depth = 0;
  private static int k = 0;
  private Node<K> parent = null;
  private Node<K> lesser = null;
  private Node<K> greater = null;


  public Node(K key, int k) {
    this.key = key;
    this.k = k;
  }

  public Node(K key, int depth, int k) {
    this.key = key;
    this.k = k;
    this.depth = depth;
  }

  public Node<K> getParent() {
    return parent;
  }

  public void setParent(Node<K> parent) {
    this.parent = parent;
  }

  public Node<K> getLesser() {
    return lesser;
  }

  public void setLesser(Node<K> lesser) {
    this.lesser = lesser;
  }

  public Node<K> getGreater() {
    return greater;
  }

  public void setGreater(Node<K> greater) {
    this.greater = greater;
  }

  public int getDepth() {
    return depth;
  }

  public K getKey() {
    return key;
  }
}