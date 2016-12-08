package com.projects.anders.shoppinglist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Credentials;
import io.realm.ObjectServerError;
import io.realm.User;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button createUserButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        bindViews();
        setListeners();
    }

    private void setListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(false);
            }
        });
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(true);
            }
        });
    }

    private void bindViews() {
        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.button_login);
        createUserButton = (Button) findViewById(R.id.button_create);
    }

    public void login(boolean createUser) {
        if (!validate()) {
            onLoginFailed("Invalid username or password");
            return;
        }

        createUserButton.setEnabled(false);
        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = this.username.getText().toString();
        String password = this.password.getText().toString();

        Credentials creds = Credentials.usernamePassword(username, password, createUser);
        String authUrl = "http://" + RealmDB.SERVER_IP + ":9080/auth";
        User.Callback callback = new User.Callback() {
            @Override
            public void onSuccess(User user) {
                progressDialog.dismiss();
                onLoginSuccess();
            }

            @Override
            public void onError(ObjectServerError error) {
                progressDialog.dismiss();
                String errorMsg;
                switch (error.getErrorCode()) {
                    case UNKNOWN_ACCOUNT:
                        errorMsg = "Account does not exists.";
                        break;
                    case INVALID_CREDENTIALS:
                        errorMsg = "User name and password does not match";
                        break;
                    default:
                        errorMsg = error.toString();
                }
                onLoginFailed(errorMsg);
            }
        };

        User.loginAsync(creds, authUrl, callback);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        createUserButton.setEnabled(true);
        startActivity(new Intent(this, ShoppingListActivity.class));
    }

    public void onLoginFailed(String errorMsg) {
        loginButton.setEnabled(true);
        createUserButton.setEnabled(true);
        Toast.makeText(getBaseContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    public boolean validate() {
        boolean valid = true;
        String email = username.getText().toString();
        String password = this.password.getText().toString();

        if (email.isEmpty()) {
            valid = false;
        }

        if (password.isEmpty()) {
            valid = false;
        }

        return valid;
    }
}
