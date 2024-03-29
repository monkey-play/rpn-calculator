package calculator;

/**
 * @author liuqian
 * @date 2019/7/22 20:40.
 */
public class Instruction {
  private Operator operator;
  private Double value;

  public Instruction(Operator operator, Double value) {
    this.operator = operator;
    this.value = value;
  }

  public String getReverseInstruction() throws CalculatorException {
    if (operator.getOperandsNumber() < 1) {
      throw new CalculatorException(String.format("invalid operation for operator %s", operator.getSymbol()));
    }

    return (operator.getOperandsNumber() < 2) ?
      String.format("%s", operator.getOpposite()) :
      String.format("%f %s %f", value, operator.getOpposite(), value);
  }
}
