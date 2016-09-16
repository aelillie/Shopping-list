package com.projects.anders.shoppinglist.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.projects.anders.shoppinglist.ItemListAdapter;
import com.projects.anders.shoppinglist.R;
import com.projects.anders.shoppinglist.data.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description
 */
public class ShoppingListFragment extends Fragment {

    private ExpandableListView _listView;
    private BaseExpandableListAdapter _listAdapter;
    private List<String> _listCategories;
    private HashMap<String, List<String>> _listItems;

    private ItemListener _callBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, null);

        _listView = (ExpandableListView) view.findViewById(R.id.expandable_list_view);
        //_listItems = <realm data fetched>
        _listCategories = new ArrayList<>(_listItems.keySet());
        _listAdapter = new ItemListAdapter(getContext(), _listCategories, _listItems);
        _listView.setAdapter(_listAdapter);
        setListeners();
        return view;
    }

    private void setListeners() {
        _listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener(){

            @Override
            public void onGroupExpand(int headerPos) {
                Toast.makeText(getContext(),
                        _listCategories.get(headerPos) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }


        });

        _listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listView, View v,
                                        int headerPos, int itemPos, long id) {
                Toast.makeText(getContext(),
                        _listCategories.get(headerPos) + " -> " + _listItems.get(_listCategories.get(headerPos)).get(itemPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    //Attach activity as callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;
        if (context instanceof Activity)
            a = (Activity) context;
        else throw new ClassCastException("Activity context not found");
        try {
            _callBack = (ItemListener) a;
        } catch (ClassCastException e) {
            System.out.println(a.toString() + "must implement the interface");
        }

    }

    public interface ItemListener {
        void onItemCreated(Item item);
        void onItemRemoved(Item item);
    }
}
