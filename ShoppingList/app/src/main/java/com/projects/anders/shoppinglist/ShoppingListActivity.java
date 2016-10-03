package com.projects.anders.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.projects.anders.shoppinglist.db.RealmDB;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.User;

/**
 * Description
 */
public class ShoppingListActivity extends FragmentActivity {

    private RealmDB realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        Realm.init(this);
        User user = User.currentUser();
        if (user == null) {
            gotoLoginActivity();
        }
        else {
            realm = RealmDB.getRealmDB(ShoppingListActivity.this, user);
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        if (savedInstanceState == null) {
            trans.add(R.id.container_shopping_list, new ShoppingListFragment());
        } else {
            trans.replace(R.id.container_shopping_list, new ShoppingListFragment());
        }
        trans.commit();
    }


    @Override
    protected void onDestroy() {
        super.onStop();
        if (realm != null) realm.close();
        User.currentUser().logout();
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, RESULT_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            User user = User.currentUser();
            realm = RealmDB.getRealmDB(ShoppingListActivity.this, user);
        }
    }
}
