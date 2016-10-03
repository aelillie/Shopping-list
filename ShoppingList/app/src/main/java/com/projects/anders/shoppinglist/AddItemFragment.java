package com.projects.anders.shoppinglist;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_item, container, false);

        bindViews(v);
        setAdapters();
        setListeners();

        item = new Item(); //Empty item

        return v;
    }

    private void setAdapters() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, CATEGORY.getCategories());
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, UNIT.getUnits());
        itemCategory.setAdapter(categoryAdapter);
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
                    //Callback to activity
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

}
