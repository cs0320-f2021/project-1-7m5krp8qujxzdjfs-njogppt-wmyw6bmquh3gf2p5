package edu.brown.cs.student.main.ORM;

import edu.brown.cs.student.main.StarData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class REPL {
    public HashMap<String, FunctionHolder> CommandToFunction;

    /**
     * method to register user commands and output result of function being requested
     */
    public void run() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            StarData starData = new StarData("");
            Database database = null;
            while ((input = br.readLine()) != null) {
                try {
                    input = input.trim();
                    String[] arguments = splitHelper(input);
                    String command = arguments[0];
                    CommandToFunction.get(command).implementFunction(arguments);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ERROR: FunctionHolder not associated with this command.");
                }
            }

        }
    }

    /**
     * A helper that splits the input string appropriately.
     * @param input - The input to the REPL
     * @return - The array of commands.
     */
    private String[] splitHelper(String input) {
        String[] split = input.split("\\s(?=(?:`[^`]*`|[^`])*$)");
        if (split[0].equals("naive_neighbors")) {
            // regex expression found here: https://tinyurl.com/5xd7n29n
            return input.split("\\s(?=(?:\"[^\"]*\"|[^\"])*$)");
        } else {
            return split;
        }
    }

    public void addFunctionToMap(String command, FunctionHolder functionPassed){
        CommandToFunction.put(command, functionPassed);
    }
}
