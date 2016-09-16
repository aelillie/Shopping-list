package com.projects.anders.shoppinglist.db;

import android.content.Context;

import com.projects.anders.shoppinglist.data.Item;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Singleton Realm database
 */
public class RealmDB {
    private Realm _realm;
    private static RealmDB _db;

    private RealmDB(Context context) {
        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        RealmConfiguration realmConfig =
                new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);

        //Create a Realm instance for this thread
        _realm = Realm.getDefaultInstance();
    }

    public static RealmDB getRealmDB(Context context) {
        return _db == null ? new RealmDB(context) : _db;
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
}
