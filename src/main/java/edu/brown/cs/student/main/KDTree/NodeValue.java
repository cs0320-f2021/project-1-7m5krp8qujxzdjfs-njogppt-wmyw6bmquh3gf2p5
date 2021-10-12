package edu.brown.cs.student.main.KDTree;

import java.util.List;

// Interface of a User with a specified type ID.
// @param <V> Any type for the ID of the user that is specified when constructing a User.
public interface NodeValue<V> {
    V getId(); // return the id of type with which the NodeValue was created
    List<Double> getNodeValue(); // a list of as many numbers as dimensions (i.e [x, y, z] )
    Double getSingleNodeValue(int dim); // get the nodeValue value at the dimension requested (i.e. x or y or z)

//    int getSecondAttribute();
//    int getFourthAttribute();
//    int getFifthAttribute();
//    int getDimensionValue(int currentDepth);
}
