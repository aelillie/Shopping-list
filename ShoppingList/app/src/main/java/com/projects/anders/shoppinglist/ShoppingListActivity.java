package com.projects.anders.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import io.realm.Realm;
import io.realm.User;

/**
 * Description
 */
public class ShoppingListActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        Realm.init(this);
        User user = User.currentUser();
        if (user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, RESULT_OK);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction trans = fm.beginTransaction();
            if (savedInstanceState == null) {
                trans.add(R.id.container_shopping_list, new ShoppingListFragment());
            } else {
                trans.replace(R.id.container_shopping_list, new ShoppingListFragment());
            }
            trans.commit();
        }
    }
}
