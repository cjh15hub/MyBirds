package com.cjh15hub.mybirds;

/**
 * Created by cjh15 on 2/21/2017.
 */

public class Bird {



    private int id;
    private String name;
    private String description;

    public Bird(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Bird(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
