package com.projects.anders.shoppinglist.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Item category, for sorting the list
 */
public class CATEGORY {
    public static String BREAD = "BREAD"; //Tortillas, bagels, rye bread
    public static String MEAT = "MEAT";//Lunch meat, beef, pork
    public static String DAIRY = "DAIRY"; //Cheeses, eggs, milk
    public static String FROZEN = "FROZEN"; //Bread, ice cream
    public static String SWEETS = "SWEETS"; //Chocolate, candy, chips
    public static String BEVERAGES = "BEVERAGES"; //Coffee, tea, juice, soda
    public static String CANNED = "CANNED"; //Sauce, beans, vegetables
    public static String DRY = "DRY"; //Cereals, flour, sugar, pasta
    public static String PRODUCE = "PRODUCE"; //fruits, vegetables
    public static String CLEANERS = "CLEANERS"; //All-purpose, laundry detergent, dishwasher liquid
    public static String PAPER = "PAPER"; //Paper towels, toilet paper
    public static String PERSONAL = "PERSONAL"; //Shampoo, soap, shaving cream
    public static String OTHER = "OTHER"; //Pet items, batteries

    public static List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add(BREAD);
        categories.add(MEAT);
        //TODO: Rest
        return categories;
    }
}
