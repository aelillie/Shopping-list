package com.projects.anders.shoppinglist.data;

import io.realm.RealmObject;

/**
 * A single shopping item, containing a description, quantity, and unit.
 * E.g.: "Milk", "2.0", and "litres"
 */
public class Item extends RealmObject{
    private String _name;
    private double _quantity;
    private UNIT _unit;
    private CATEGORY _category;

    public CATEGORY getCategory() {
        return _category;
    }

    public void setCategory(CATEGORY category) {
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

    public UNIT getUnit() {
        return _unit;
    }

    public void setUnit(UNIT unit) {
        _unit = unit;
    }
}
