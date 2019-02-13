package com.scube.dms;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Context mContext = DMS.getContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        runAfterDelay(2);
    }

    /**
     * Check if user is logged in, if yes then redirect to main activity, else redirect to login screen
     *
     * @param i splash screen delay
     */
    private void runAfterDelay(int i) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        }, i * 1000);
    }
}
