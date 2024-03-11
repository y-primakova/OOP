package ru.nsu.primakova;

import java.util.List;

/**
 * Class Config.
 */
public class Config {
    private List<Integer> cookingTime;
    private List<Integer> courierCapacity;
    private int storageCapacity;

    public Config() {

    }

    public Config(List<Integer> cookingTime, List<Integer> courierCapacity, int storageCapacity) {
        this.cookingTime = cookingTime;
        this.courierCapacity = courierCapacity;
        this.storageCapacity = storageCapacity;
    }

    public List<Integer> getcookingTime() {
        return this.cookingTime;
    }

    public List<Integer> getcourierCapacity() {
        return this.courierCapacity;
    }

    public int getstorageCapacity() {
        return this.storageCapacity;
    }

    public void setcookingTime(List<Integer> cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setcourierCapacity(List<Integer> courierCapacity) {
        this.courierCapacity = courierCapacity;
    }

    public void setstorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }
}
