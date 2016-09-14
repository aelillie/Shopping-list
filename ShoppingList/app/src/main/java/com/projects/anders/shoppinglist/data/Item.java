package com.projects.anders.shoppinglist.data;

import io.realm.RealmObject;

/**
 * A single shopping item, containing a description, quantity, and unit.
 * E.g.: "Milk", "2.0", and "litres"
 */
public class Item extends RealmObject{
    private String description;
    private double quantity;
    private UNIT unit;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public UNIT getUnit() {
        return unit;
    }

    public void setUnit(UNIT unit) {
        this.unit = unit;
    }
}
