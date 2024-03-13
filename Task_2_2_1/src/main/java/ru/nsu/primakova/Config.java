package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Config.
 */
public class Config {
    private List<Integer> storage;
    private List<Integer> orders;
    private List<Integer> cookingTime;
    private List<Integer> courierCapacity;
    private int storageCapacity;
    private int workTime;

    public Config() {

    }

    public Config(List<Integer> cookingTime, List<Integer> courierCapacity, int storageCapacity, int workTime, List<Integer> orders) {
        this.cookingTime = cookingTime;
        this.courierCapacity = courierCapacity;
        this.storageCapacity = storageCapacity;
        this.workTime = workTime;
        this.orders = orders;
        this.storage = new ArrayList<>();
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

    public int getworkTime() {
        return this.workTime;
    }

    public List<Integer> getstorage() {
        return this.storage;
    }

    public List<Integer> getorders() {
        return this.orders;
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

    public void setworkTime(int workTime) {
        this.workTime = workTime;
    }

    public void setstorage(List<Integer> storage) {
        this.storage = storage;
    }

    public void setorders(List<Integer> orders) {
        this.orders = orders;
    }
}
