package com.projects.anders.shoppinglist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Customized list adapter
 */
public class ItemListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; //Categories
    private HashMap<String, List<String>> _listDataChild; //Items

    public ItemListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
        _context = context;
        _listDataChild = listDataChild;
        _listDataHeader = listDataHeader;
    }

    @Override
    public int getGroupCount() {
        return _listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int headerPos) {
        return _listDataChild.get(_listDataHeader.get(headerPos)).size();
    }

    @Override
    public Object getGroup(int headerPos) {
        return _listDataHeader.get(headerPos);
    }

    @Override
    public Object getChild(int headerPos, int itemPos) {
        return _listDataChild.get(_listDataHeader.get(headerPos)).get(itemPos);
    }

    @Override
    public long getGroupId(int headerPos) {
        return headerPos;
    }

    @Override
    public long getChildId(int headerPos, int itemPos) {
        return itemPos;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int headerPos, boolean b, View view, ViewGroup viewGroup) {
        final String headerText = (String) getGroup(headerPos);
        TextView headerTextView = (TextView) view.findViewById(R.id.listTitle);
        headerTextView.setText(headerText);
        return view;
    }

    @Override
    public View getChildView(int headerPos, int itemPos,
                             boolean isLastChild, View view, ViewGroup viewGroup) {
        final String itemText = (String) getChild(headerPos, itemPos);
        TextView itemTextView = (TextView) view.findViewById(R.id.expandedListItem);
        itemTextView.setText(itemText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int headerPos, int itemPos) {
        return true;
    }
}
