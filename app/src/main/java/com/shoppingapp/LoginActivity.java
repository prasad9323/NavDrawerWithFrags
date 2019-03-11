package com.shoppingapp;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shoppingapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Context mContext = Master.getContext();
    EditText etUsername, etPassword;
    View viewLogin, viewSignUp, root;
    TextView tvVersion, tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupViews();
    }

    /**
     * Bind views
     */
    private void setupViews() {
        root = findViewById(R.id.root);
        etUsername = findViewById(R.id.et_username);
        etUsername.setOnClickListener(this);
        etPassword = findViewById(R.id.et_password);
        etPassword.setOnClickListener(this);
        viewLogin = findViewById(R.id.view_login);
        viewLogin.setOnClickListener(this);
        viewSignUp = findViewById(R.id.view_signUp);
        viewSignUp.setOnClickListener(this);
        tvVersion = findViewById(R.id.tv_version);
        tvVersion.setOnClickListener(this);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvForgotPassword.setOnClickListener(this);
        setUpVersionText();
    }

    /**
     * Set app version number on login screen
     */
    private void setUpVersionText() {
        tvVersion.setText("VERSION " + Master.getAppVersion());
    }

    /**
     * Setup clicks
     *
     * @param v view on which click listener is fired
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_login:
                checkLoginInfo();
                break;
            case R.id.view_signUp:
                startActivity(new Intent(mContext, SignUpActivity.class));
                break;
            case R.id.tv_forgot_password:
                setSnackBar("Clicked on forgot password");  //here "layout" is your parentView in a layout
        }
    }

    /**
     * Verify login info and redirect
     */
    private void checkLoginInfo() {
        startActivity(new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        //Show snackbar if login info is wrong
    }

    public void setSnackBar(String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_SHORT);
        snackbar.show();
        View view = snackbar.getView();
        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }
}
