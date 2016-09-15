package com.projects.anders.shoppinglist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

/**
 * Description
 */
public class ShoppingListFragment extends Fragment {

    private RecyclerView  _recyclerView;

    public static ShoppingListFragment newInstance() {
        ShoppingListFragment f = new ShoppingListFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }
}
