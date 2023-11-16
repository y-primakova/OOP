package ru.nsu.primakova;
import ru.nsu.primakova.Heapsort;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        var array = new int[]{4,3,2,1};
        int[] arr = Heapsort.heapsort(array);
        System.out.print(Arrays.toString(arr));
    }
}
