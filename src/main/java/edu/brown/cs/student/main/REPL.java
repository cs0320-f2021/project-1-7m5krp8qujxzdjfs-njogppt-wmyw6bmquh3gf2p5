package edu.brown.cs.student.main;

import edu.brown.cs.student.main.KDTree.KDTree;
import edu.brown.cs.student.main.KDTree.NodeValue;
import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.Star.StarData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class REPL {
  private HashMap<String, FunctionHolder> commandToFunction = new HashMap<>();

  /**
   * Method to register user commands and output result of function being requested.
   */
  public void run() throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      StarData starData;
      Database database;
      KDTree<Integer> kdTree;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = splitHelper(input);
          String command = arguments[0];
          if (command.equals("database")) {
            database = new Database(arguments[1]);
            Collection<FunctionHolder> functions = commandToFunction.values();
            for (FunctionHolder f : functions) {
              f.setDatabase(database);
            }
            System.out.println("Database at " + arguments[1] + " loaded in.");
          } else if (command.equals("star")) {
            starData = new StarData(arguments[1]);
            Collection<FunctionHolder> functions = commandToFunction.values();
            for (FunctionHolder f : functions) {
              f.setStarData(starData);
            }
          } else if (command.equals("users")) {
            Database db = new Database(arguments[1]);
            List<NodeValue<Integer>> users = db.getUsers();
            kdTree = new KDTree<>(3, users);
            Collection<FunctionHolder> functions = commandToFunction.values();
            for (FunctionHolder f : functions) {
              f.setKDTree(kdTree);
            }
          } else {
            commandToFunction.get(command).implementFunction(arguments);
          }
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

  /**
   * Adds a command to the REPL.
   * @param command - The name of the command/first argument given when calling the command.
   * @param functionPassed - The function implementation of the command.
   */
  public void addFunctionToMap(String command, FunctionHolder functionPassed) {
    commandToFunction.put(command, functionPassed);
  }
}
