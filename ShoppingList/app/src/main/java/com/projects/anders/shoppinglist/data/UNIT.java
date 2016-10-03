package com.projects.anders.shoppinglist.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Item unit
 */
public class UNIT {
    public static String KILOGRAM = "kg";
    public static String GRAM = "g";
    public static String LITRE = "l";
    public static String DECILITER = "dl";
    public static String PIECES = "pieces";


    public static List<String> getUnits() {
        List<String> units = new ArrayList<>();
        units.add(KILOGRAM);
        units.add(GRAM);
        //TODO: Add rest
        return units;
    }
}
