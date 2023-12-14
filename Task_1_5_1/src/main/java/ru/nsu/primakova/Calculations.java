package ru.nsu.primakova;

import java.util.Objects;
import java.util.Stack;

/**
 * Class Calculations.
 */
public class Calculations {
    public static String calculations(String str) {
        var stack = new Stack<String>();
        double res = 0;
        var haveDigit = false;
        var line = str.split("\\s+");
        for (var elem : line) {
            if (isDouble(elem)) {
                if (stack.isEmpty() && haveDigit) {
                    return "Incorrect number of operations.";
                }
                if (!haveDigit) {
                    haveDigit = true;
                    res = Double.parseDouble(elem);
                    if (stack.isEmpty()) {
                        continue;
                    }
                    if (!Objects.equals(stack.peek(), "sin") && !Objects.equals(stack.peek(), "cos")
                            && !Objects.equals(stack.peek(), "sqrt") && !Objects.equals(stack.peek(), "log")) {
                        continue;
                    }
                }
                if (!Objects.equals(stack.peek(), "sin") && !Objects.equals(stack.peek(), "cos")
                        && !Objects.equals(stack.peek(), "sqrt") && !Objects.equals(stack.peek(), "log")) {
                    var exception = exceptionInOperation(Double.parseDouble(elem), stack.peek());
                    if (!exception.isEmpty()) {
                        return exception;
                    }
                    res = operation(res, Double.parseDouble(elem), stack.pop());
                }
                while (!stack.isEmpty()) {
                    if (Objects.equals(stack.peek(), "sin") || Objects.equals(stack.peek(), "cos")
                            || Objects.equals(stack.peek(), "sqrt") || Objects.equals(stack.peek(), "log")) {
                        var exception = exceptionInOperation(res, stack.peek());
                        if (!exception.isEmpty()) {
                            return exception;
                        }
                        res = operation(res, stack.pop());
                    } else {
                        break;
                    }
                }
            } else {
                if (haveDigit) {
                    return "Incorrect input order.";
                }
                stack.push(elem);
            }
        }
        if (!stack.isEmpty()) {
            stack.clear();
            return "Incorrect number of operations";
        }
        return String.valueOf(res);
    }

    private static double operation(double number1, double number2, String oper) {
        if (Objects.equals(oper, "+")) {
            return number1 + number2;
        }
        if (Objects.equals(oper, "-")) {
            return number1 - number2;
        }
        if (Objects.equals(oper, "*")) {
            return number1 * number2;
        }
        if (Objects.equals(oper, "/")) {
            return number1 / number2;
        }
        if (Objects.equals(oper, "pow")) {
            return Math.pow(number1, number2);
        }
        return 0;
    }

    private static double operation(double number, String oper) {
        if (Objects.equals(oper, "log")) {
            return Math.log(number);
        }
        if (Objects.equals(oper, "sqrt")) {
            return Math.sqrt(number);
        }
        if (Objects.equals(oper, "sin")) {
            return Math.sin(Math.toRadians(number));
        }
        if (Objects.equals(oper, "cos")) {
            return Math.cos(Math.toRadians(number));
        }
        return 0;
    }

    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static String exceptionInOperation(double number, String oper) {
        if (Objects.equals(oper, "sin") || Objects.equals(oper, "cos")
                || Objects.equals(oper, "sqrt") || Objects.equals(oper, "log")
                || Objects.equals(oper, "+") || Objects.equals(oper, "-") || Objects.equals(oper, "*")
                || Objects.equals(oper, "/") || Objects.equals(oper, "pow")) {
            if (Objects.equals(oper, "log") && number <= 0) {
                return "ln by non-positive number.";
            }
            if (Objects.equals(oper, "sqrt") && number < 0) {
                return "sqrt of a negative number.";
            }
            if (Objects.equals(oper, "/") && number == 0) {
                return "/ by zero.";
            }
            return "";
        }
        return "Incorrect operation name(" + oper + ").";
    }
}
