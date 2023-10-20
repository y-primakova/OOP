package ru.nsu.primakova;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapsortTest{
    @Test
    public void heapsortTest1(){
        var a = Heapsort.heapsort(new int[] {5, 4, 3, 2, 1});
        Assertions.assertArrayEquals(a, new int[] {1, 2, 3, 4, 5});
    }
    @Test
    public void heapsortTest2(){
        var a = Heapsort.heapsort(new int[] {1, -4, 0, 10, -2, 5, 4});
        Assertions.assertArrayEquals(a, new int[] {-4, -2, 0, 1, 4, 5, 10});
    }
    @Test
    public void heapsortTest3(){
        var a = Heapsort.heapsort(new int[] {-9, 5, 9, 3, 5, -1});
        Assertions.assertArrayEquals(a, new int[] {-9, -1, 3, 5, 5, 9});
    }
    @Test
    public void heapsortTest4(){
        var a = Heapsort.heapsort(new int[] {});
        Assertions.assertArrayEquals(a, new int[] {});
    }
    @Test
    public void heapsortTest5(){
        var a = Heapsort.heapsort(new int[] {-3, 4, 7, 9});
        Assertions.assertArrayEquals(a, new int[] {-3, 4, 7, 9});
    }
    @Test
    public void heapsortTest6(){
        var a = Heapsort.heapsort(new int[] {321});
        Assertions.assertArrayEquals(a, new int[] {321});
    }
}
