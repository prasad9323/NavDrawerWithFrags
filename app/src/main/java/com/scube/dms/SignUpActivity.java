package com.scube.dms;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Context mContext = DMS.getContext();
    EditText etUsername, etPassword;
    View viewLogin, viewSignUp, root;
    TextView tvVersion, tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
        viewSignUp = findViewById(R.id.view_signUp);
        viewSignUp.setOnClickListener(this);
        tvVersion = findViewById(R.id.tv_version);
        tvVersion.setOnClickListener(this);
        tvForgotPassword = findViewById(R.id.view_login);
        tvForgotPassword.setOnClickListener(this);
        setUpVersionText();
    }

    /**
     * Set app version number on login screen
     */
    private void setUpVersionText() {
        tvVersion.setText("VERSION " + DMS.getAppVersion());
    }

    /**
     * Setup clicks
     *
     * @param v view on which click listener is fired
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_signUp:
                checkSignUpInfo();  //here "layout" is your parentView in a layout
                break;
            case R.id.tv_forgot_password:
                finish();
        }
    }

    /**
     * Verify login info and redirect
     */
    private void checkSignUpInfo() {
        startActivity(new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        //Show snackbar if signup info is wrong
    }

    public void setSnackBar(String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_SHORT);
        snackbar.show();
        View view = snackbar.getView();
        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }
}

