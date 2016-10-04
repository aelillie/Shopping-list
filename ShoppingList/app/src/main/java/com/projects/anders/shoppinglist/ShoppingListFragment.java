package com.projects.anders.shoppinglist;

import android.app.Activity;
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
    private ShoppingListListener _callBack;

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

    @Override
    public void onStart() {
        super.onStart();

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
        db = RealmDB.getRealmDB(getContext(), User.currentUser());
        updateUI();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (db != null) db.close();
    }

    /**
     * User option for logging out
     */
    public void logout() {
        if (db != null) {
            db.close();
            User.currentUser().logout();
        } else {
            Toast.makeText(getContext(), "Not logged in!", Toast.LENGTH_SHORT).show();
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private TextView itemText;
        private Item item;

        ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemText = (TextView) itemView.findViewById(R.id.list_item);
        }

        void bindItem(Item item) {
            this.item = item;
            itemText.setText(item.getName());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "CLICK", Toast.LENGTH_SHORT).show();
            db.removeItem(item);
            _callBack.onItemRemoved();
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private Context context;
        private List<Item> items;

        ItemAdapter(List<Item> items, Context context) {
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
        void setItems(List<Item> items) {
            this.items = items;
        }
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
            _callBack = (ShoppingListListener) a;
        } catch (ClassCastException e) {
            System.out.println(a.toString() + "must implement the interface");
        }

    }

    public interface ShoppingListListener {
        void onItemRemoved();
    }
}
