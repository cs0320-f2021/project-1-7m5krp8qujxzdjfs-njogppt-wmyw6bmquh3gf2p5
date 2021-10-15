package edu.brown.cs.student.main.KDTree;

import java.util.List;

// Interface of a User with a specified type ID.
// @param <V> Any type for the ID of the user that is specified when constructing a User.
public interface NodeValue<V> {
    V getId(); // return the generic object's identification
    List<Double> getNodeValue(); // a list of as many numbers as dimensions (i.e [x, y, z] )
    Double getSingleNodeValue(int dim); // get the nodeValue value at the dimension requested (i.e. x or y or z)
    // NOTE: should throw indexOutOfBound exception if dim is not accessible (ex. getSingleNodeValue(4) in 3-D space)
        // so recommend storing x,y,z in list of some sort

    int getSecondAttribute(); // not used anymore
    int getFourthAttribute(); // not used anymore
    int getFifthAttribute(); // not used anymore
//    int getDimensionValue(int currentDepth);
}
