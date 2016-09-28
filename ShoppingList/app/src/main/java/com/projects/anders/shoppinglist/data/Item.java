package com.projects.anders.shoppinglist.data;

import io.realm.RealmObject;

/**
 * A single shopping item, containing a description, quantity, and unit.
 * E.g.: "Milk", "2.0", and "litres"
 */
public class Item extends RealmObject{
    private String _name;
    private double _quantity;
    private int _unit;
    private int _category;

    public int getCategory() {
        return _category;
    }

    public void setCategory(int category) {
        _category = category;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public double getQuantity() {
        return _quantity;
    }

    public void setQuantity(double quantity) {
        _quantity = quantity;
    }

    public int getUnit() {
        return _unit;
    }

    public void setUnit(int unit) {
        _unit = unit;
    }
}
