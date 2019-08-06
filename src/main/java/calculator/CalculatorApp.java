package calculator;


import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author liuqian
 * @date 2019/07/20
 */
public class CalculatorApp {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    boolean exit = false;
    Calculator calculator = new Calculator();
    System.out.println("Enter your expression, or 'exit' to quit");
    while (!exit) {
      String expression = scanner.nextLine();
      if (expression != null && expression.length() > 0) {
        if ("exit".equals(expression)) {
          exit = true;
        } else {
          try {
            calculator.calculate(expression);
          } catch (CalculatorException e) {
            System.out.println(e.getMessage());
          }
          DecimalFormat fmt = new DecimalFormat("0.##########");
          Stack<Double> stack = calculator.getValuesStack();
          System.out.print("stack: ");
          for (Double value : stack) {
            System.out.print(fmt.format(value));
            System.out.print(" ");
          }
          System.out.printf("%n");
        }
      }
    }
  }
}
