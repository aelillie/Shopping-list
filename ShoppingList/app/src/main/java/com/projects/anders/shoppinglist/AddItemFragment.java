package com.projects.anders.shoppinglist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
 * Handles adding items to the shopping list in @ShoppingListActivity
 */
public class AddItemFragment extends AppCompatActivity {

    private EditText itemName;
    private EditText itemQuantity;
    private Spinner itemCategory;
    private Spinner itemUnit;
    private Button addItemButton;

    private Item item;

    private RealmDB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        setAdapters();
        setListeners();

        item = new Item(); //Empty item
        db = RealmDB.getRealmDB(this, User.currentUser()); //Maybe get user from parent?
    }


    private void setAdapters() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, CATEGORY.getCategories());
        itemCategory.setAdapter(categoryAdapter);
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this,
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
                    //TODO: Go to list
                }
            }
        });
    }

    private void bindViews() {
        itemName = (EditText) findViewById(R.id.text_item_name);
        itemQuantity = (EditText) findViewById(R.id.text_item_quantity);
        itemCategory = (Spinner) findViewById(R.id.spinner_item_category);
        itemUnit = (Spinner) findViewById(R.id.spinner_item_unit);
        addItemButton = (Button) findViewById(R.id.button_add);
    }

}
