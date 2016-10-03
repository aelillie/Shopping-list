package com.projects.anders.shoppinglist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.anders.shoppinglist.data.Item;
import com.projects.anders.shoppinglist.db.RealmDB;

import java.util.List;

import io.realm.User;

/**
 * Shopping list
 */

public class ShoppingListFragment extends Fragment {

    private RecyclerView itemsRecyclerView;
    private ItemAdapter adapter;
    private RealmDB db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        itemsRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_shopping_list_recycler_view);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = RealmDB.getRealmDB(getContext(), User.currentUser());
        updateUI();

        return v;
    }

    /**
     * Query items to show
     */
    private void updateUI() {
        List<Item> items = db.getItems();
        if (adapter == null) {
            adapter = new ItemAdapter(items, getActivity());
            itemsRecyclerView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class ItemHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private TextView itemText;
        private Item item;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemText = (TextView) itemView.findViewById(R.id.list_item);
        }

        public void bindItem(Item item) {
            this.item = item;
            itemText.setText(item.getName());
        }

        @Override
        public void onClick(View view) {
            //TODO: Option for deleting item?
            Toast.makeText(getContext(), "CLICK", Toast.LENGTH_SHORT).show();
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private Context context;
        private List<Item> items;

        public ItemAdapter(List<Item> items, Context context) {
            this.items = items;
            this.context = context;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater l = LayoutInflater.from(context);
            View view = l.inflate(R.layout.list_item, parent, false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = items.get(position);
            holder.bindItem(item);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        /**
         * Update items
         * @param items Updated items
         */
        public void setItems(List<Item> items) {
            this.items = items;
        }
    }
}
