package ru.nsu.primakova;

/**
 * Class Vertex.
 */
public class Vertex<T> {
    private T name;

    /**
     * Class constructor.
     */
    public Vertex(){
        this.name = null;
    }

    public Vertex(T name){
        this.name = name;
    }

    public T get_name(){
        return this.name;
    }

    public void change_name(T newName){
        this.name = newName;
    }
}
