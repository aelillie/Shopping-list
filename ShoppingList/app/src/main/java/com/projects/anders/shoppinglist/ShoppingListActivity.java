package com.projects.anders.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.projects.anders.shoppinglist.data.Item;

import io.realm.Realm;
import io.realm.User;

/**
 * Description
 */
public class ShoppingListActivity extends FragmentActivity
    implements AddItemFragment.ItemListener, ShoppingListFragment.ShoppingListListener{

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

    /**
     * Notify shopping list of item added
     */
    @Override
    public void onItemAdded() {
        updateShoppingList();
    }

    /**
     * Notify shopping list of item removed
     */
    @Override
    public void onItemRemoved() {
        updateShoppingList();
    }

    private void updateShoppingList() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_shopping_list_recycler_view);
        if (fragment == null) {
            throw new NullPointerException("Shopping list fragment is not initialized!");
        }
        fm.beginTransaction().replace(R.id.container_shopping_list,
                new ShoppingListFragment()).commit();

    }
}
