package com.projects.anders.shoppinglist.db;

import android.content.Context;

import com.projects.anders.shoppinglist.data.CATEGORY;
import com.projects.anders.shoppinglist.data.Item;
import com.projects.anders.shoppinglist.BuildConfig;
import com.projects.anders.shoppinglist.data.UNIT;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.ObjectServerError;
import io.realm.SyncConfiguration;
import io.realm.Credentials;
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

    public void addItem(Item item) //throws <SomeRealmException>
    {
        _realm.beginTransaction();
        //Add shopping list here
        _realm.commitTransaction();
        //Throw some exception if it fails
    }

    public Item getItem(String name) {
        _realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                //Define "query"
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                //perform some action
            }
        });
        return null;
    }

    public void removeItem(Item item) {
        //TODO: Remove an item from the db
    }

    public void close() {
        _realm.close();
    }

    /**
     * Fetch all items currently on the shopping list
     * @return Relevant shopping list items
     */
    public List<Item> getItems() {
        return testItems;
    }
}
