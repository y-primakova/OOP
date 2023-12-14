package ru.nsu.primakova;

import static ru.nsu.primakova.Calculations.calculations;
import static ru.nsu.primakova.Print.print;
import static ru.nsu.primakova.Read.readNextLine;

import java.util.Objects;

/**
 * Class Calculator.
 */
public class Calculator {
    public static void calculator() {
        while (true){
            var str = readNextLine();
            if (Objects.equals(str, "")) {
                break;
            }
            str = calculations(str);
            print(str);
        }
    }
}
