package edu.brown.cs.student.main.Math;

import edu.brown.cs.student.main.ORM.FunctionHolder;

public class AddFunction implements FunctionHolder {

    public AddFunction(){
    }
    @Override
    public void implementFunction(String[]arguments) {
        MathBot mbot = new MathBot();
        double num1 = Double.parseDouble(arguments[1]);
        double num2 = Double.parseDouble(arguments[2]);
        System.out.println(mbot.add(num1, num2));
    }
}
