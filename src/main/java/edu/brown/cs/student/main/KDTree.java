package edu.brown.cs.student.main;

public class KDTree<V> {

  private Node<V> _root; // root node
  private Integer _k; // the dimensions of the space (ex. 3-dimensional space)

  private Comparator<V> _comparator;

  public KDTree(Integer k) {
    this._k =  k;
  }

  public void addNode(V val) {
    if (val == null) {
      return; // exit
    }
    if (this._root == null) { // tree empty
      this._root = new Node<V>(val, this._k, 0); // create root with val
      return; // exit
    }

    Node<V> currNode = this._root; // the current node we're looking at in the traversal

    while (true) { // goes forever until reaches a break statement

      int axis = currNode.getDepth() % _k; // what attribute we're looking at at this level
      if(this._comparator.compare(currNode.getKey(), val, axis) >= 0) { // lesser          !!!!!!!!!!!!!!!!!!!--figure out way to compare--!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(currNode.getLesser() == null) { // no left child
          Node<V> newNode = new Node<>(val, currNode.getDepth() +1, _k); // create new node
          newNode.setParent(currNode); // child points at parent
          currNode.setLesser(newNode); // parent points at child
          break; // exit loop
        }
        currNode = currNode.getLesser(); // if there is a left child, set currNode to it
      } else { //greater
        if(currNode.getGreater() == null) { // no right child
          Node<V> newNode = new Node<>(val, currNode.getDepth() +1, _k); // create new node
          newNode.setParent(currNode); // child points at parent
          currNode.setGreater(newNode); // parent points at child
          break; // exit loop
        }
        currNode = currNode.getGreater(); // if there is a right child, set currNode to it
      }
    }
  }

}
