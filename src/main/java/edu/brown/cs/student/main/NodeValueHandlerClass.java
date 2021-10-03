package edu.brown.cs.student.main;

import edu.brown.cs.student.main.KDTree.Node;
import edu.brown.cs.student.main.KDTree.NodeValue;
import edu.brown.cs.student.main.dataTypes.Users;

import java.util.Comparator;
import java.util.List;

public class NodeValueHandlerClass {
  /**
   * method to sort a list of NodeValue by the NodeValues' second attribute.
   * @param nodeListForSecondAttribute - list of NodeValues to sort
   * @return sorted list of NodeValues based on ascending second attribute values
   */
  public List<NodeValue> sortingUsersBySecondAttribute(List<NodeValue> nodeListForSecondAttribute) {
    Comparator<NodeValue> sortingComparator = Comparator.comparing(NodeValue::getSecondAttribute);
    nodeListForSecondAttribute.sort(sortingComparator);
    return nodeListForSecondAttribute;
  }

  /**
   * method for sorting a list of NodeValues by the NodeValues' fourth attribute.
   * @param nodeListForFourthAttribute - list of NodeValues to sort
   * @return sorted ascending list of NodeValues by fourth attribute values
   */
  public List<NodeValue> sortingUsersByFourthAttribute(List<NodeValue> nodeListForFourthAttribute) {
    Comparator<NodeValue> sortingComparator = Comparator.comparing(NodeValue::getFourthAttribute);
    nodeListForFourthAttribute.sort(sortingComparator);
    return nodeListForFourthAttribute;
  }

  /**
   * method for sorting list of NodeValues by NodeValues' fifth attribute.
   * @param nodeListForFifthAttribute - list of NodeValues to sort
   * @return sorted ascending list of NodeValues by fifth attribute values
   */
  public List<NodeValue> sortingUsersByFifthAttribute(List<NodeValue> nodeListForFifthAttribute) {
    Comparator<NodeValue> sortingComparator = Comparator.comparing(NodeValue::getFifthAttribute);
    nodeListForFifthAttribute.sort(sortingComparator);
    return nodeListForFifthAttribute;
  }

  /**
   * calls the appropriate sorting method depending on the attribute corresponding to the current depth of the KDTree
   * @param currentDepth - the level of the tree currently being analyzed
   * @param nodeValuesForSort - list of NodeValues to be sorted according to the dimension associated with current depth
   * @return sorted list of NodeValues
   */
  public List<NodeValue> sortedByDimension(int currentDepth, List<NodeValue> nodeValuesForSort) {
    int dimension = currentDepth % 3;
    if (dimension == 0) {
      return sortingUsersBySecondAttribute(nodeValuesForSort);
    } else if (dimension == 1) {
      return sortingUsersByFourthAttribute(nodeValuesForSort);
    } else {
      return sortingUsersByFifthAttribute(nodeValuesForSort);
    }
  }



}

