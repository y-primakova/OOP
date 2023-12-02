package ru.nsu.primakova;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class Calculator.
 */
public class Calculator {

    private static String readNextLine() {
        var scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    public static void calculator() {
        var stack = new Stack<String>();
        while (true) {
            var str = readNextLine();
            if (Objects.equals(str, "")) {
                break;
            }
            double res = 0;
            var haveDigit = false;
            var haveException = false;
            var line = str.split("\\s+");
            for (var elem : line) {
                if (isDouble(elem)) {
                    if (stack.isEmpty() && haveDigit) {
                        System.out.println("Incorrect number of operations.");
                        haveException = true;
                        break;
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
                        if (exceptionInOperation(Double.parseDouble(elem), stack.peek())) {
                            haveException = true;
                            break;
                        }
                        res = operation(res, Double.parseDouble(elem), stack.pop());
                    }
                    while (!stack.isEmpty()) {
                        if (Objects.equals(stack.peek(), "sin") || Objects.equals(stack.peek(), "cos")
                                || Objects.equals(stack.peek(), "sqrt") || Objects.equals(stack.peek(), "log")) {
                            if (exceptionInOperation(res, stack.peek())) {
                                haveException = true;
                                break;
                            }
                            res = operation(res, stack.pop());
                        } else {
                            break;
                        }
                    }
                } else {
                    if (haveDigit) {
                        System.out.println("Incorrect input order.");
                        haveException = true;
                        break;
                    }
                    stack.push(elem);
                }
            }
            if (!stack.isEmpty() && !haveException) {
                System.out.println("Incorrect number of operations");
                haveException = true;
            }
            if (haveException) {
                stack.clear();
                continue;
            }
            System.out.println(" = " + res);
        }
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

    private static boolean exceptionInOperation(double number, String oper) {
        if (Objects.equals(oper, "sin") || Objects.equals(oper, "cos")
                || Objects.equals(oper, "sqrt") || Objects.equals(oper, "log")
                || Objects.equals(oper, "+") || Objects.equals(oper, "-") || Objects.equals(oper, "*")
                || Objects.equals(oper, "/") || Objects.equals(oper, "pow")) {
            if (Objects.equals(oper, "log") && number <= 0) {
                System.out.println("ln by non-positive number.");
                return true;
            }
            if (Objects.equals(oper, "sqrt") && number < 0) {
                System.out.println("sqrt of a negative number.");
                return true;
            }
            if (Objects.equals(oper, "/") && number == 0) {
                System.out.println("/ by zero.");
                return true;
            }
            return false;
        }
        System.out.println("Incorrect operation name(" + oper + ").");
        return true;
    }
}
