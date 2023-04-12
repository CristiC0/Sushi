package com.example.sushi;

public class SushiCard {

    private int ID;
    private String name;
    private int photo;
    private int cost;
    private int quantity;


    public SushiCard(int ID, String name, int photo, int cost, int quantity) {
        this.ID = ID;
        this.name = name;
        this.photo = photo;
        this.cost = cost;
        this.quantity = quantity;
    }

    public SushiCard(String name, int photo, int cost, int quantity) {
        this.name = name;
        this.photo = photo;
        this.cost = cost;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SushiCard{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", photo=" + photo +
                ", cost=" + cost +
                ", quantity=" + quantity +
                '}';
    }
}
