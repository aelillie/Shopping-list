package com.projects.anders.shoppinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.anders.shoppinglist.data.Item;
import com.projects.anders.shoppinglist.db.RealmDB;

import java.util.List;

import io.realm.Realm;
import io.realm.User;

/**
 * Description
 */
public class ShoppingListActivity extends AppCompatActivity{

    private RecyclerView itemsRecyclerView;
    private ItemAdapter adapter;
    private RealmDB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        Realm.init(this);
        User user = User.currentUser();
        if (user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, RESULT_OK);
        } else {
            itemsRecyclerView = (RecyclerView) findViewById(R.id.fragment_shopping_list_recycler_view);
            itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            db = RealmDB.getRealmDB(this, User.currentUser());
            updateUI();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

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
            Toast.makeText(this, "Not logged in!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Query items to show
     */
    private void updateUI() {
        List<Item> items = db.getItems();
        if (adapter == null) {
            adapter = new ItemAdapter(items, this);
            itemsRecyclerView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        db = RealmDB.getRealmDB(this, User.currentUser());
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recyclerview_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            //TODO: Go to add item activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ItemHolder extends RecyclerView.ViewHolder
            implements View.OnLongClickListener {

        private TextView itemText;
        private Item item;

        ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(this);
            itemText = (TextView) itemView.findViewById(R.id.list_item);
        }

        void bindItem(Item item) {
            this.item = item;
            itemText.setText(item.getName());
        }

        @Override
        public boolean onLongClick(View view) {
            //Toast.makeText(this, "CLICK", Toast.LENGTH_SHORT).show();
            db.removeItem(item); //TODO: Update view
            return true;
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
}
