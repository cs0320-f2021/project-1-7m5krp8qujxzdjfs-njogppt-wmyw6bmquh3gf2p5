package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.MathBot;

public class SubtractFunction implements FunctionHolder {

    public SubtractFunction(){
    }
    @Override
    public void implementFunction(String[] arguments) {
        MathBot mbot = new MathBot();
        double num1 = Double.parseDouble(arguments[1]);
        double num2 = Double.parseDouble(arguments[2]);
        System.out.println(mbot.subtract(num1, num2));
    }
}
