import java.util.Scanner;

public class Calculator {
  public static void main(String[] args) {
    try {
      @SuppressWarnings("resource")
      Scanner scanner = new Scanner(System.in);

      System.out.println("List of operations: add subtract multiply divide alphabetize");
      System.out.println("Enter an operation:");
      String userInput = scanner.nextLine();
      userInput = userInput.toLowerCase();

      switch (userInput) {
        case "add":
        case "subtract":
          System.out.println("Enter two integers:");
          int int1 = scanner.nextInt();
          int int2 = scanner.nextInt();

          if (userInput.equals("add")) {
            add(int1, int2);
          } else {
            subtract(int1, int2);
          }
          break;
        case "multiply":
        case "divide":
          System.out.println("Enter two doubles:");
          double double1 = scanner.nextDouble();
          double double2 = scanner.nextDouble();

          if (userInput.equals("multiply")) {
            multiply(double1, double2);
          } else {
            divide(double1, double2);
          }
          break;
        case "alphabetize":
          System.out.println("Enter two words:");
          String word1 = scanner.next();
          String word2 = scanner.next();
          alphabetize(word1, word2);
          break;
        default:
          System.out.println("Invalid input entered. Terminating...");
      }
    } catch (Exception e) {
      System.out.println("Invalid input entered. Terminating...");
    }
  }

  // Method to perform addition
  private static void add(int digit1, int digit2) {
    System.out.println(String.format("Answer: %s", digit1 + digit2));
  }

  // Method to perform subtraction
  private static void subtract(int digit1, int digit2) {
    System.out.println(String.format("Answer: %s", digit1 - digit2));
  }

  // Method to perform multiplication
  private static void multiply(double double1, double double2) {
    System.out.println(String.format("Answer: %s", String.format("%.2f", double1 * double2)));
  }

  // Method to perform division
  private static void divide(double double1, double double2) {
    if (double2 == 0) {
      System.out.println("Invalid input entered. Terminating...");
      return;
    }
    System.out.println(String.format("Answer: %s", String.format("%.2f", double1 / double2)));
  }

  // Method to alphabetize
  private static void alphabetize(String word1, String word2) {
    String word1Lower = word1.toLowerCase();
    String word2Lower = word2.toLowerCase();
    int comparedWords = word1Lower.compareTo(word2Lower);

    System.out.println(comparedWords == 0
      ? "Answer: Chicken or Egg."
      : String.format("Answer: %s comes before %s alphabetically.",
        comparedWords < 0 ? word1 : word2,
        comparedWords < 0 ? word2 : word1));
  }
}