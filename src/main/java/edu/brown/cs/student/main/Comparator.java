package edu.brown.cs.student.main;

public class Comparator<V> {

  public Comparator() {

  }

  public int compare(V v1, V v2, int axis) {
    return 0;
  }
}
static class UserComparatorByDimension implements Comparator<User> {
  int depth;
  int currentDepth;
  int k;


  UserComparatorByDimension(int depth, int currentDepth, int k) {
    this.depth = depth;
    this.currentDepth = currentDepth;
    this.k = k;
  }

  @Override
  public int compare(User o1, User o2) {
    // TODO: modify depending on caseMatters
    if(o1.getDimensionValue(this.depth, this.currentDepth, this.k).compareTo(o2.getDimensionValue(this.depth, this.currentDepth, this.k)) > 0) return 1;
    if(o1.getDimensionValue(this.depth, this.currentDepth, this.k).compareTo(o2.getDimensionValue(this.depth, this.currentDepth, this.k)) < 0) return -1;
    return 0;
  }


  public static <T extends Comparable<T>> void sortSomeRecords(Comparator<T> comp, List<T> lst) {
    for(int i=0; i<lst.size(); i++) {
      for(int j=i+1; j<lst.size(); j++) {
        if(comp.compare(lst.get(j), lst.get(i)) < 0) {
          T swap = lst.get(i);
          lst.set(i, lst.get(j));
          lst.set(j, swap);
        }
      }
    }
  }
