package com.example.babybuy.models;

public class Firestoreproduct {
    String name;
    String desc;
    float price;
    String image;

    public Firestoreproduct(String name, String desc, float price, String image) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.image = image;
    }

    public Firestoreproduct() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
