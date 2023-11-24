package ru.nsu.primakova;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class Calculator.
 */
public class Calculator {

    public static double calculator() throws IncorrectInputException {
        var scanner = new Scanner(System.in);
        double res = 0;
        var haveDigit = false;
        var stack = new Stack<String>();
        if (scanner.hasNextLine()) {
            var str = scanner.nextLine();
            scanner.close();
            var line = str.split(" ");
            for (var elem : line) {
                if (isDouble(elem)) {
                    if (stack.isEmpty()) {
                        throw new IncorrectInputException("Incorrect number of operations.");
                    }
                    if (!haveDigit) {
                        haveDigit = true;
                        res = Double.parseDouble(elem);
                        if (!Objects.equals(stack.peek(), "sin") && !Objects.equals(stack.peek(), "cos")
                                && !Objects.equals(stack.peek(), "sqrt") && !Objects.equals(stack.peek(), "log")) {
                            continue;
                        }
                    }
                    if (!Objects.equals(stack.peek(), "sin") && !Objects.equals(stack.peek(), "cos")
                            && !Objects.equals(stack.peek(), "sqrt") && !Objects.equals(stack.peek(), "log")) {
                        res = operation(res, Double.parseDouble(elem), stack.pop());
                    }
                    while (!stack.isEmpty()) {
                        if (Objects.equals(stack.peek(), "sin") || Objects.equals(stack.peek(), "cos")
                                || Objects.equals(stack.peek(), "sqrt") || Objects.equals(stack.peek(), "log")) {
                            res = operation(res, stack.pop());
                        } else {
                            break;
                        }
                    }
                } else {
                    if (haveDigit) {
                        throw new IncorrectInputException("Incorrect input order.");
                    }
                    stack.push(elem);
                }
            }
        }
        if (!stack.isEmpty()) {
            throw new IncorrectInputException("Incorrect number of operations.");
        }
        return res;
    }

    private static double operation(double number1, double number2, String oper) throws IncorrectInputException {
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
            if (number2 == 0) {
                throw new IncorrectInputException("/ by zero.");
            }
            return number1 / number2;
        }
        if (Objects.equals(oper, "pow")) {
            return Math.pow(number1, number2);
        } else {
            throw new IncorrectInputException("Incorrect operation name(" + oper + ").");
        }
    }

    private static double operation(double number, String oper) throws IncorrectInputException {
        if (Objects.equals(oper, "log")) {
            if (number <= 0) {
                throw new IncorrectInputException("ln by non-positive number.");
            }
            return Math.log(number);
        }
        if (Objects.equals(oper, "sqrt")) {
            if (number < 0) {
                throw new IncorrectInputException("sqrt of a negative number.");
            }
            return Math.sqrt(number);
        }
        if (Objects.equals(oper, "sin")) {
            return Math.sin(Math.toRadians(number));
        }
        if (Objects.equals(oper, "cos")) {
            return Math.cos(Math.toRadians(number));
        } else {
            throw new IncorrectInputException("Incorrect operation name(" + oper + ").");
        }
    }

    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
