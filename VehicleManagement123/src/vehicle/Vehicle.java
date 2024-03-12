/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle;

/**
 *
 * @author Bond
 */
public class Vehicle {
    private String id;
    private String name;
    private String color;
    private double price;
    private String brand;
    private String type;
    private String productYear;
    private int quantity;

    public Vehicle() {
    }

    public Vehicle(String id, String name, String color, double price, String brand, String type, String productYear, int quantity) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.productYear = productYear;
        this.quantity = quantity;
    }

    public String getId() {
        return id.toUpperCase();
    }

    public void setId(String id) {
        this.id = id.toUpperCase();
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getColor() {
        return color.toUpperCase();
    }

    public void setColor(String color) {
        this.color = color.toUpperCase();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand.toUpperCase();
    }

    public void setBrand(String brand) {
        this.brand = brand.toUpperCase();
    }

    public String getType() {
        return type.toUpperCase();
    }

    public void setType(String type) {
        this.type = type.toUpperCase();
    }

    public String getProductYear() {
        return productYear;
    }

    public void setProductYear(String productYear) {
        this.productYear = productYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "id = " + id + ", name = " + name + ", color = " + color + ", price = " + price + ", brand = " + brand + ", type= " + type + ", productYear = " + productYear + ", quantity = " + quantity;
    }
    
}
