package ru.nsu.primakova;

import java.util.List;

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

    public void settext(List<Integer> cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void settitle(List<Integer> courierCapacity) {
        this.courierCapacity = courierCapacity;
    }

    public void setdate(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }
}
