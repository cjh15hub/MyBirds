package com.cjh15hub.mybirds;

/**
 * Created by cjh15 on 2/21/2017.
 */

public class Bird {



    private int id;
    private String name;
    private String description;
    private String imageURL;


    public Bird(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Bird(String name, String description, String imageURL) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

    public int getID() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getImageURL(){return imageURL;}

    public void setName(String name){ this.name = name;}

    public void setDescription(String description){ this.description = description;}

    public void setImageURL(String imageURL){this.imageURL = imageURL;}

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
