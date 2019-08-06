import calculator.Calculator;
import calculator.CalculatorException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * @author liuqian
 * @date 2019/7/23 20:05.
 */
public class CalculatorTest {

  /**
   * Example Tests
   */
  @Test
  public void calculatorTest() throws CalculatorException {
    Calculator calculator = new Calculator();

    // Example 1
    calculator.calculate("5 2");
    Stack<Double> valuesStack1 = calculator.getValuesStack();
    Assert.assertEquals(5, valuesStack1.get(0), 0);
    Assert.assertEquals(2, valuesStack1.get(1), 0);

    // Example 2
    calculator.calculate("clear");
    calculator.calculate("2 sqrt");
    Stack<Double> valuesStack2 = calculator.getValuesStack();
    Assert.assertEquals(1.4142135623730951, valuesStack2.get(0), 0);
    calculator.calculate("clear 9 sqrt");
    valuesStack2 = calculator.getValuesStack();
    Assert.assertEquals(3, valuesStack2.get(0), 0);

    // Example 3
    calculator.calculate("clear");
    calculator.calculate("5 2 -");
    Stack<Double> valuesStack3 = calculator.getValuesStack();
    Assert.assertEquals(3, valuesStack3.get(0), 0);
    calculator.calculate("clear");
    calculator.calculate("3 3 -");
    valuesStack3 = calculator.getValuesStack();
    Assert.assertEquals(0, valuesStack3.get(0), 0);

    // Example 4
    calculator.calculate("clear");
    calculator.calculate("5 4 3 2");
    Stack<Double> valuesStack4 = calculator.getValuesStack();
    Assert.assertEquals(5, valuesStack4.get(0), 0);
    Assert.assertEquals(4, valuesStack4.get(1), 0);
    Assert.assertEquals(3, valuesStack4.get(2), 0);
    Assert.assertEquals(2, valuesStack4.get(3), 0);
    calculator.calculate("undo undo *");
    valuesStack4 = calculator.getValuesStack();
    Assert.assertEquals(20, valuesStack4.get(0), 0);
    calculator.calculate("5 *");
    valuesStack4 = calculator.getValuesStack();
    Assert.assertEquals(100, valuesStack4.get(0), 0);
    calculator.calculate("undo");
    valuesStack4 = calculator.getValuesStack();
    Assert.assertEquals(20, valuesStack4.get(0), 0);
    Assert.assertEquals(5, valuesStack4.get(1), 0);

    // Example 5
    calculator.calculate("clear");
    calculator.calculate("7 12 2 /");
    Stack<Double> valuesStack5 = calculator.getValuesStack();
    Assert.assertEquals(7, valuesStack5.get(0), 0);
    Assert.assertEquals(6, valuesStack5.get(1), 0);
    calculator.calculate("*");
    valuesStack5 = calculator.getValuesStack();
    Assert.assertEquals(42, valuesStack5.get(0), 0);
    calculator.calculate("4 /");
    valuesStack5 = calculator.getValuesStack();
    Assert.assertEquals(10.5, valuesStack5.get(0), 0);

    // Example 6
    calculator.calculate("clear");
    calculator.calculate("1 2 3 4 5 *");
    Stack<Double> valuesStack6 = calculator.getValuesStack();
    Assert.assertEquals(20, valuesStack6.get(3), 0);
    calculator.calculate("clear 3 4 -");
    valuesStack6 = calculator.getValuesStack();
    Assert.assertEquals(-1, valuesStack6.get(0), 0);

    // Example 7
    calculator.calculate("clear");
    calculator.calculate("1 2 3 4 5");
    calculator.calculate("* * * *");
    Stack<Double> valuesStack7 = calculator.getValuesStack();
    Assert.assertEquals(120, valuesStack7.get(0), 0);

    // Example 8
    calculator.calculate("clear");
    try {
      calculator.calculate("1 2 3 * 5 + * * 6 5");
    } catch (CalculatorException e) {
      assertEquals("operator * (position: 8): insufficient parameters", e.getMessage());
    }
    Stack<Double> valuesStack8 = calculator.getValuesStack();
    assertEquals(1, valuesStack8.size());
    assertEquals(11, valuesStack8.get(0), 0);
  }
}
