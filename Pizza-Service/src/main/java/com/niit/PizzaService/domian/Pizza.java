package com.niit.PizzaService.domian;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pizza {

    @Id
    private String pizzaName;
    private String pizzaType;
    private String pizzaSize;
    private double pizzaPrice;
    private String description;

    public Pizza() {
    }

    public Pizza(String pizzaName, String pizzaType, String pizzaSize, double pizzaPrice, String description) {
        this.pizzaName = pizzaName;
        this.pizzaType = pizzaType;
        this.pizzaSize = pizzaSize;
        this.pizzaPrice = pizzaPrice;
        this.description = description;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaName='" + pizzaName + '\'' +
                ", pizzaType='" + pizzaType + '\'' +
                ", pizzaSize='" + pizzaSize + '\'' +
                ", pizzaPrice=" + pizzaPrice +
                ", description='" + description + '\'' +
                '}';
    }
}
