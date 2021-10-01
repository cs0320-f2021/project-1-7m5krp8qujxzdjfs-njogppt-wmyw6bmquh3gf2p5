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
              if (arguments.length == 8) {
                Users newUser = new Users(arguments[1], arguments[2], arguments[3], arguments[4],
                    arguments[5], arguments[6], arguments[7]);
                if (database != null) {
                  database.insert(newUser);
                  System.out.println("New user added.");
                } else {
                  throw new IOException("ERROR: There is no database loaded in.");
                }
              }
              break;
            default:
              throw new IOException("ERROR: There is no command to process.");
          }
        } catch (Exception e) {
          // e.printStackTrace();
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
    String[] split = input.split(" ");
    if (split[0].equals("naive_neighbors")) {
      // regex expression found here: https://tinyurl.com/5xd7n29n
      return input.split("\\s(?=(?:\"[^\"]*\"|[^\"])*$)");
    } else {
      return split;
    }
  }

}
