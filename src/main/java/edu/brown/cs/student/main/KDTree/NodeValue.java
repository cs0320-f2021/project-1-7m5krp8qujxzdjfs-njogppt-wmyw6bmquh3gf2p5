package edu.brown.cs.student.main.KDTree;

import java.util.List;

// Interface of a User with a specified type ID.
// @param <V> Any type for the ID of the user that is specified when constructing a User.
public interface NodeValue<V> {
    V getId(); // return the id of type with which the User was created
    List<Double> getNodeValue(); // get all user values of the User, a list of as many numbers as dimensions
    Double getNodeValueVal(int dim); // get the node value at the dimension requested

//    int getSecondAttribute();
//    int getFourthAttribute();
//    int getFifthAttribute();
//    int getDimensionValue(int currentDepth);
}
