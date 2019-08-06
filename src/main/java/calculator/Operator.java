package calculator;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.sqrt;

/**
 * @author liuqian
 * @date 2019/7/22 20:41.
 */
public enum Operator {
  /**
   * 加
   */
  ADDITION("+", "-", 2) {
    @Override
    public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
      return secondOperand + firstOperand;
    }
  },

  /**
   * 减
   */
  SUBTRACTION("-", "+", 2) {
    @Override
    public Double calculate(Double firstOperand, Double secondOperand) {
      return secondOperand - firstOperand;
    }
  },

  /**
   * 乘
   */
  MULTIPLICATION("*", "/", 2) {
    @Override
    public Double calculate(Double firstOperand, Double secondOperand) {
      return secondOperand * firstOperand;
    }
  },

  /**
   * 除
   */
  DIVISION("/", "*", 2) {
    @Override
    public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
      if (firstOperand == 0) {
        throw new CalculatorException("Cannot divide by 0.");
      }
      return secondOperand / firstOperand;
    }
  },

  /**
   * 开平方
   */
  SQRT("sqrt", "pow", 1) {
    @Override
    public Double calculate(Double firstOperand, Double secondOperand) {
      return sqrt(firstOperand);
    }
  },

  /**
   * 撤销上一步
   */
  UNDO("undo", null, 0) {
    @Override
    public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
      throw new CalculatorException("Invalid operation");
    }
  },

  /**
   * 清空
   */
  CLEAR("clear", null, 0) {
    @Override
    public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
      throw new CalculatorException("Invalid operation");
    }
  };

  private static final Map<String, Operator> LOOKUP = new HashMap<>();

  static {
    for (Operator o : values()) {
      LOOKUP.put(o.getSymbol(), o);
    }
  }

  private String symbol;
  private String opposite;
  private int operandsNumber;

  Operator(String symbol, String opposite, int operandsNumber) {
    this.symbol = symbol;
    this.opposite = opposite;
    this.operandsNumber = operandsNumber;
  }

  public static Operator getEnum(String value) {
    return LOOKUP.get(value);
  }

  public abstract Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException;

  public String getSymbol() {
    return symbol;
  }

  public String getOpposite() {
    return opposite;
  }

  public int getOperandsNumber() {
    return operandsNumber;
  }

  @Override
  public String toString() {
    return symbol;
  }

}
