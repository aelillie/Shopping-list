package com.projects.anders.shoppinglist.db;

import android.content.Context;

import com.projects.anders.shoppinglist.BuildConfig;
import com.projects.anders.shoppinglist.data.CATEGORY;
import com.projects.anders.shoppinglist.data.Item;
import com.projects.anders.shoppinglist.data.UNIT;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.User;

/**
 * Singleton Realm database
 */
public class RealmDB {
    private Realm _realm;
    private static RealmDB _db;
    private List<Item> testItems;

    private RealmDB(Context context, User user) {

        String serverURL = "realm://" + BuildConfig.OBJECT_SERVER_IP + "/~/default";
        SyncConfiguration configuration = new SyncConfiguration.Builder(user, serverURL).build();
        _realm = Realm.getInstance(configuration);

        testItems = new ArrayList<>();
        testItems.add(new Item(CATEGORY.BEVERAGES, "Milk", 1.0, UNIT.LITRE));
        testItems.add(new Item(CATEGORY.MEAT, "Pork", 500.0, UNIT.GRAM));
        testItems.add(new Item(CATEGORY.SWEETS, "Chocolate", 1.0, UNIT.PIECES));

    }

    public static RealmDB getRealmDB(Context context, User user) {

        return _db == null ? new RealmDB(context, user) : _db;
    }

    /**
     * Adds the specified item to the shopping list
     * Ignoring duplicates
     * @param item Item to add
     */
    public void addItem(Item item) //throws <SomeRealmException>
    {
        //TODO: Add item from the db
    }

    /**
     * Remove a single item from the shopping list
     * @param item Item to remove
     */
    public void removeItem(Item item) { //throws <SomeRealmException>
        //TODO: Remove an item from the db
    }

    /**
     * Fetch all items currently on the shopping list
     * Ordered by category
     * @return Relevant shopping list items
     */
    public List<Item> getItems() {
        //TODO: Query db for all items, sorted by category
        return testItems;
    }

    public void close() {
        _realm.close();
        _realm = null; //New connection to db required for reuse
    }
}
