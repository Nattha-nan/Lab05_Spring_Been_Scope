package com.example.coffeeshop.model;

public class Coffee {

    private Long id;
    private String name;
    private double price;

    // ต้องมี constructor ว่าง (no-args) ไว้ให้ Jackson แปลง JSON -> Object
    public Coffee() {
    }

    public Coffee(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}