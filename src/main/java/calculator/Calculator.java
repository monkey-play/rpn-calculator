package calculator;

import java.util.Stack;

/**
 * @author liuqian
 * @date 2019/7/21 15:26.
 */
public class Calculator {

  private Stack<Double> valuesStack = new Stack<>();
  private Stack<Instruction> operatorsStack = new Stack<>();
  private int currentTokenIndex = 0;

  private Double tryParseDouble(String str) {
    try {
      return Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
      return null;
    }
  }


  public void calculate(String expression) throws CalculatorException {
    calculate(expression, false);
  }

  private void calculate(String expression, boolean isUndoOperation) throws CalculatorException {
    if (expression == null) {
      throw new CalculatorException("The expression cannot be null.");
    }
    currentTokenIndex = 0;
    String[] resultArray = expression.split("\\s");
    for (String result : resultArray) {
      currentTokenIndex++;
      processToken(result, isUndoOperation);
    }
  }

  private void processToken(String token, boolean isUndoOperation) throws CalculatorException {
    Double tokenNumber = tryParseDouble(token);
    if (tokenNumber == null) {
      processOperator(token, isUndoOperation);
    } else {
      // 可以转为数字
      valuesStack.push(tokenNumber);
      if (!isUndoOperation) {
        operatorsStack.push(null);
      }
    }
  }

  private void processOperator(String operatorStr, boolean isUndoOperation) throws CalculatorException {
    if (valuesStack.isEmpty()) {
      return;
    }
    Operator operator = Operator.getEnum(operatorStr);
    if (operator == null) {
      throw new CalculatorException("invalid operator");
    }
    // clear stack
    if (operator == Operator.CLEAR) {
      clearStacks();
      return;
    }
    // undo
    if (operator == Operator.UNDO) {
      undoLastInstruction();
      return;
    }
    // 检查运算规则，数字是否数量是否满足运算符操作
    if (operator.getOperandsNumber() > valuesStack.size()) {
      throwInvalidOperand(operatorStr);
    }

    // 获取运算数
    Double firstNumber = valuesStack.pop();
    Double secondNumber = (operator.getOperandsNumber() > 1) ? valuesStack.pop() : null;
    // 计算
    Double result = operator.calculate(firstNumber, secondNumber);
    if (result != null) {
      valuesStack.push(result);
      if (!isUndoOperation) {
        operatorsStack.push(new Instruction(Operator.getEnum(operatorStr), firstNumber));
      }
    }
  }


  private void clearStacks() {
    valuesStack.clear();
    operatorsStack.clear();
  }

  private void undoLastInstruction() throws CalculatorException {
    if (operatorsStack.isEmpty()) {
      throw new CalculatorException("no operations to undo");
    }

    Instruction lastInstruction = operatorsStack.pop();
    if (lastInstruction == null) {
      valuesStack.pop();
    } else {
      calculate(lastInstruction.getReverseInstruction(), true);
    }
  }

  private void throwInvalidOperand(String operator) throws CalculatorException {
    throw new CalculatorException(
      String.format("operator %s (position: %d): insufficient parameters", operator, currentTokenIndex));
  }


  public Stack<Double> getValuesStack() {
    return valuesStack;
  }
}
