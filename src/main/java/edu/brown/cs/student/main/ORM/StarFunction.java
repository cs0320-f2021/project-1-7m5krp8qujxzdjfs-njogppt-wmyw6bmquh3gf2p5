package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.StarData;

public class StarFunction implements FunctionHolder {

    public StarFunction(){
    }

    @Override
    public void implementFunction(String[] arguments) {
        StarData starData = new StarData(arguments[1]);
    }
}
