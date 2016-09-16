package com.projects.anders.shoppinglist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.projects.anders.shoppinglist.fragments.ShoppingListFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        if (savedInstanceState == null) { //Initialization
            trans.add(R.id.main_fragment_container, new ShoppingListFragment());
        } else { //Navigated here from elsewhere
            trans.replace(R.id.main_fragment_container, new ShoppingListFragment());
        }
        trans.commit();
    }
}
