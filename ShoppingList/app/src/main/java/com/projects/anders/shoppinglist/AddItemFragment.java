package com.projects.anders.shoppinglist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.projects.anders.shoppinglist.data.CATEGORY;
import com.projects.anders.shoppinglist.data.Item;
import com.projects.anders.shoppinglist.data.UNIT;
import com.projects.anders.shoppinglist.db.RealmDB;

import io.realm.User;

/**
 * Handles adding items to the shopping list in @ShoppingListFragment
 */
public class AddItemFragment extends Fragment {

    private EditText itemName;
    private EditText itemQuantity;
    private Spinner itemCategory;
    private Spinner itemUnit;
    private Button addItemButton;

    private Item item;

    private ItemListener _callBack;
    private RealmDB db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_item, container, false);

        bindViews(v);
        setAdapters();
        setListeners();

        item = new Item(); //Empty item
        db = RealmDB.getRealmDB(getContext(), User.currentUser()); //Maybe get user from parent?
        return v;
    }

    private void setAdapters() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, CATEGORY.getCategories());
        itemCategory.setAdapter(categoryAdapter);
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, UNIT.getUnits());
        itemUnit.setAdapter(unitAdapter);
    }

    private void setListeners() {
        itemCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String category = (String) parent.getItemAtPosition(pos);
                item.setCategory(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });
        itemUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String unit = (String) parent.getItemAtPosition(pos);
                item.setUnit(unit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item != null) {
                    String name = itemName.getText().toString();
                    item.setName(name);
                    double quantity = Double.parseDouble(itemQuantity.getText().toString());
                    item.setQuantity(quantity);
                    db.addItem(item);
                    _callBack.onItemAdded();
                }
            }
        });
    }

    private void bindViews(View v) {
        itemName = (EditText) v.findViewById(R.id.text_item_name);
        itemQuantity = (EditText) v.findViewById(R.id.text_item_quantity);
        itemCategory = (Spinner) v.findViewById(R.id.spinner_item_category);
        itemUnit = (Spinner) v.findViewById(R.id.spinner_item_unit);
        addItemButton = (Button) v.findViewById(R.id.button_add);
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
        void onItemAdded();
    }

}
