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
            ShoppingListFragment frag1 = (ShoppingListFragment)
                    fm.findFragmentById(R.id.fragment_shopping_list_recycler_view);
            AddItemFragment frag2 = (AddItemFragment)
                    fm.findFragmentById(R.id.fragment_add_item_view);
            if (frag1 == null || frag2 == null) { //First time
                trans.add(R.id.container_shopping_list, new ShoppingListFragment());
                trans.add(R.id.container_add_item, new AddItemFragment());
            } else { //Navigated here from another view
                trans.replace(R.id.container_shopping_list, new ShoppingListFragment());
                trans.replace(R.id.container_add_item, new AddItemFragment());
            }
            trans.commit();
        }
    }
}
