package edu.brown.cs.student.main;

import edu.brown.cs.student.main.dataTypes.User;

import java.util.Comparator;
import java.util.List;

public class UserHandlerClass {
    /**
     * method to sort a user list by the users' weight attribute
     * @param userListForWeight - list of users to sort
     * @return sorted list of users based on ascending weight values
     */
    public List<User> sortingUsersByWeight(List<User> userListForWeight){
        Comparator<User> sortingComparator = Comparator.comparing(User::getWeight);
        userListForWeight.sort(sortingComparator);
        return userListForWeight;
    }

    /**
     * method for sorting a user lists by users' height attribute
     * @param userListForHeight - list of users to sort
     * @return sorted ascending list of users by height values
     */
    public List<User> sortingUsersByHeight(List<User> userListForHeight){
        Comparator<User> sortingComparator = Comparator.comparing(User::getHeight);
        userListForHeight.sort(sortingComparator);
        return userListForHeight;
    }

    /**
     * method for sorting list of users by users' age attribute
     * @param userListForAge - list of users to sort
     * @return sorted ascending list of users by age values
     */
    public List<User> sortingUsersByAge(List<User> userListForAge){
        Comparator<User> sortingComparator = Comparator.comparing(User::getHeight);
        userListForAge.sort(sortingComparator);
        return userListForAge;
    }

    /**
     * calls the appropriate sorting method depending on the attribute corresponding to the current depth of the KDTree
     * @param currentDepth - the level of the tree currently being analyzed
     * @param userListForSort - the list of users to be sorted according to the dimension associated with current depth
     * @return sorted list of users
     */
    public List<User> sortedByDimension(int currentDepth, List<User> userListForSort) {
        int dimension = currentDepth % 3;
        if (dimension == 0) {
            return sortingUsersByWeight(userListForSort);
        } else if (dimension == 1) {
            return sortingUsersByHeight(userListForSort);
        } else {
            return sortingUsersByAge(userListForSort);
        }
    }

}

