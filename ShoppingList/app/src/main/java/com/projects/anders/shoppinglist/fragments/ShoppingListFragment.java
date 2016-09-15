package com.projects.anders.shoppinglist.fragments;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description
 */
public class ShoppingListFragment extends Fragment {

    private ExpandableListView _listView;
    private BaseExpandableListAdapter _listAdapter;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, null);

        _listView = (ExpandableListView) view.findViewById(R.id.expandable_list_view);
        //_listDataChild = <realm data fetched>
        _listDataHeader = new ArrayList<>(_listDataChild.keySet());
        _listAdapter = new ItemListAdapter(getContext(), _listDataHeader, _listDataChild);
        _listView.setAdapter(_listAdapter);
        setListeners();
        return view;
    }

    private void setListeners() {
        _listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener(){

            @Override
            public void onGroupExpand(int headerPos) {
                Toast.makeText(getContext(),
                        _listDataHeader.get(headerPos) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }


        });

        _listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listView, View v,
                                        int headerPos, int itemPos, long id) {
                Toast.makeText(getContext(),
                        _listDataHeader.get(headerPos) + " -> " + _listDataChild.get(_listDataHeader.get(headerPos)).get(itemPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
