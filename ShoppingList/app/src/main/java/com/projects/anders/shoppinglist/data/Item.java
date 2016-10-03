package com.projects.anders.shoppinglist.data;

import io.realm.RealmObject;

/**
 * A single shopping item, containing a description, quantity, and unit.
 * E.g.: "Milk", "2.0", and "litres"
 */
public class Item extends RealmObject{
    private String _name;
    private double _quantity;
    private String _unit;
    private String _category;

    public Item() {
    }

    public Item(String category, String name, double quantity, String unit) {
        _category = category;
        _name = name;
        _quantity = quantity;
        _unit = unit;
    }

    public String getCategory() {
        return _category;
    }

    public String getName() {
        return _name;
    }

    public double getQuantity() {
        return _quantity;
    }

    public String getUnit() {
        return _unit;
    }

    public void setCategory(String category) {
        _category = category;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setQuantity(double quantity) {
        _quantity = quantity;
    }

    public void setUnit(String unit) {
        _unit = unit;
    }
}
