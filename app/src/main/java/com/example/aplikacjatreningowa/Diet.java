package com.example.aplikacjatreningowa;

public class Diet {
    private int id;
    private String name;
    private String bodyType;
    private String description;

    public Diet() {
    }

    public Diet(String name, String bodyType, String description) {
        this.name = name;
        this.bodyType = bodyType;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
