package com.projects.anders.shoppinglist.db;

import android.content.Context;

import com.projects.anders.shoppinglist.data.Item;
import com.projects.anders.shoppinglist.data.ShoppingList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Singleton Realm database
 */
public class RealmDB {
    private Realm realm;
    private RealmDB db;

    private RealmDB(Context context) {
        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        RealmConfiguration realmConfig =
                new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);

        //Create a Realm instance for this thread
        realm = Realm.getDefaultInstance();
    }

    public RealmDB getRealmDB(Context context) {
        return db == null ? new RealmDB(context) : db;
    }

    public void addList(ShoppingList list) //throws <SomeRealmException>
    {
        realm.beginTransaction();
        //Add shopping list here
        realm.commitTransaction();
        //Throw some exception if it fails
    }

    public ShoppingList getList(long id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
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
}
