package com.example.myshop.models;

public class Item {

    private String name;
    private double price;

    //constructor

    public  Item(String name, double price){
        this.name = name;
        this.price = price;
    }

    //methods
    public String getName() { return name; }

    public double getPrice(){return price; }
}
