package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import edu.brown.cs.student.main.ORM.Database;
import edu.brown.cs.student.main.dataTypes.DataTypes;
import edu.brown.cs.student.main.dataTypes.Rent;
import edu.brown.cs.student.main.dataTypes.Reviews;
import edu.brown.cs.student.main.dataTypes.Users;
import freemarker.template.Configuration;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  // use port 4567 by default when running server
  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  @SuppressWarnings("checkstyle:AvoidNestedBlocks")
  private void run() {
    // set up parsing of command line flags
    OptionParser parser = new OptionParser();

    // "./run --gui" will start a web server
    parser.accepts("gui");

    // use "--port <n>" to specify what port on which the server runs
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);
    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      StarData starData = new StarData("");
      Database database = null;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = splitHelper(input);
          String command = arguments[0];
          switch (command) {
            case "add": this.addHelper(arguments[1], arguments[2]);
            break;
            case "subtract": this.subHelper(arguments[1], arguments[2]);
            break;
            case "stars":
              starData = new StarData(arguments[1]);
              break;
            case "naive_neighbors":
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
                throw new IOException("ERROR: The arguments you provided were incorrect.");
              }
              break;
            case "database":
              database = new Database(arguments[1]);
              System.out.println("Database at " + arguments[1] + " loaded in.");
              break;
            case "insert":
            case "delete":
            case "update":
              this.ormHelper(command, arguments, database);
              break;
            case "select":
              if (database != null) {
                List<DataTypes> results = database.where(arguments[1], arguments[2], arguments[3]);
                System.out.println(results);
              } else {
                throw new IOException("ERROR: There is no database connection.");
              }
            default:

          }
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("ERROR: We couldn't process your input");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR: Invalid input for REPL");
    }

  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration(Configuration.VERSION_2_3_0);

    // this is the directory where FreeMarker templates are placed
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
          templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer(int port) {
    // set port to run the server on
    Spark.port(port);

    // specify location of static resources (HTML, CSS, JS, images, etc.)
    Spark.externalStaticFileLocation("src/main/resources/static");

    // when there's a server error, use ExceptionPrinter to display error on GUI
    Spark.exception(Exception.class, new ExceptionPrinter());

    // initialize FreeMarker template engine (converts .ftl templates to HTML)
    FreeMarkerEngine freeMarker = createEngine();

    // setup Spark Routes
    Spark.get("/", new MainHandler(), freeMarker);
  }

  /**
   * Display an error page when an exception occurs in the server.
   */
  private static class ExceptionPrinter implements ExceptionHandler<Exception> {
    @Override
    public void handle(Exception e, Request req, Response res) {
      // status 500 generally means there was an internal server error
      res.status(500);

      // write stack trace to GUI
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

  /**
   * A handler to serve the site's main page.
   *
   * @return ModelAndView to render.
   * (main.ftl).
   */
  private static class MainHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      // this is a map of variables that are used in the FreeMarker template
      Map<String, Object> variables = ImmutableMap.of("title",
          "Go go GUI");

      return new ModelAndView(variables, "main.ftl");
    }
  }

  /**
   * A method that adds two given numbers together.
   * @param number1 - The first number given.
   * @param number2 - The second number given.
   */
  private void addHelper(String number1, String number2) {
    MathBot mbot = new MathBot();
    double num1 = Double.parseDouble(number1);
    double num2 = Double.parseDouble(number2);
    System.out.println(mbot.add(num1, num2));
  }

  /**
   * A method that subtracts two given numbers.
   * @param number1 - The first number given.
   * @param number2 - The second number given.
   */
  private void subHelper(String number1, String number2) {
    MathBot mbot = new MathBot();
    double num1 = Double.parseDouble(number1);
    double num2 = Double.parseDouble(number2);
    System.out.println(mbot.subtract(num1, num2));
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
   * A helper that handles running the given command on the ORM.
   * @param command - One of the ORM commands.
   * @param arguments - The list of arguments given.
   * @param db - The database
   */
  private void ormHelper(String command, String[] arguments, Database db) {
    try {
    /* for users provide user_id weight bust_size height age body_type
    and horoscope in that order */
      if (arguments.length == 8 || arguments.length == 10) {
        Users newUser = new Users(arguments[1], arguments[2], arguments[3], arguments[4],
            arguments[5], arguments[6], arguments[7]);
        switch (command) {
          case "insert":
            db.insert(newUser);
            System.out.println("New user data added.");
            break;
          case "delete":
            db.delete(newUser);
            System.out.println("User with user_id " + newUser.getID() + " deleted.");
            break;
          case "update":
            // add twp arguments to the above template giving what to update by then with what value
            String updateBy = arguments[8];
            String updateWith = arguments[9];
            db.update(newUser, updateBy, updateWith);
            System.out.println("User with user_id: " + newUser.getID() + " updated.");
            break;
          default: throw new IOException("ERROR: The given command is not supported.");
        }

      /* for rent provide id user_id item_id fit rating rented_for category
      and size in that order. */
      } else if (arguments.length == 9 || arguments.length == 11) {
        Rent newRent = new Rent(Integer.parseInt(arguments[1]), arguments[2], arguments[3],
            arguments[4], arguments[5], arguments[6], arguments[7], arguments[8]);
        switch (command) {
          case "insert":
            db.insert(newRent);
            System.out.println("New rent data added.");
            break;
          case "delete":
            db.delete(newRent);
            System.out.println("Rent data with id " + newRent.getID() + " deleted.");
            break;
          case "update":
            // add twp arguments to the above template giving what to update by then with what value
            String updateBy = arguments[9];
            String updateWith = arguments[10];
            db.update(newRent, updateBy, updateWith);
            System.out.println("Rent data with id: " + newRent.getID() + " updated.");
            break;
          default: throw new IOException("ERROR: The given command is not supported.");
        }

      /* for reviews provide id `review_text` `review_summary` and `review_date` in
      that order. ` delimits chunks of text. */
      } else if (arguments.length == 5 || arguments.length == 7) {
        String reviewText = arguments[2].replaceAll("`", "");
        String reviewSum = arguments[3].replaceAll("`", "");
        String reviewDate = arguments[4].replaceAll("`", "");
        Reviews newReview = new Reviews(Integer.parseInt(arguments[1]), reviewText,
            reviewSum, reviewDate);
        switch (command) {
          case "insert":
            db.insert(newReview);
            System.out.println("New review data added.");
            break;
          case "delete":
            db.delete(newReview);
            System.out.println("User with user_id " + newReview.getID() + " deleted.");
            break;
          case "update":
            // add twp arguments to the above template giving what to update by then with what value
            String updateBy = arguments[5];
            String updateWith = arguments[6];
            db.update(newReview, updateBy, updateWith);
            System.out.println("User with user_id: " + newReview.getID() + " updated.");
            break;
          default: throw new IOException("ERROR: The given command is not supported.");
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
