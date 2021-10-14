package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.Star;
import edu.brown.cs.student.main.StarData;

import java.util.List;


public class NaiveNeighborsFunction implements FunctionHolder {

    public NaiveNeighborsFunction() {
    }


    @Override
    public void implementFunction(String[] arguments) {
        StarData starData = new StarData(arguments[1]);
        if (starData.getStars().size() == 0) {
            System.out.println("There are no stars available. "
                    + "Make sure to run the stars command with a file containing star data.");
        } else if (arguments.length == 5) {
            List<Star> neighbors = starData.getPositionNeighbors(Integer.parseInt(arguments[1]),
                    Double.parseDouble(arguments[2]), Double.parseDouble(arguments[3]),
                    Double.parseDouble(arguments[4]));
            for (Star s : neighbors) {
                System.out.println(s.getID());
            }
        } else if (arguments.length == 3) {
            List<Star> neighbors = starData.getStarNeighbors(Integer.parseInt(arguments[1]),
                    arguments[2]);
            for (Star s : neighbors) {
                System.out.println(s.getID());
            }
        } else {
            System.out.println("ERROR: The arguments you provided were incorrect.");
        }
    }
}
