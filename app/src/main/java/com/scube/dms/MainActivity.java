package com.scube.dms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.scube.dms.Fragments.Frag0;
import com.scube.dms.Fragments.Frag1;
import com.scube.dms.Fragments.Frag2;
import com.scube.dms.Fragments.Frag3;
import com.scube.dms.Fragments.Frag4;
import com.scube.dms.Fragments.Frag5;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String[] activityTitles;
    private static final String TAG_FRAG_0 = "Frag 0";
    private static final String TAG_FRAG_1 = "Frag 1";
    private static final String TAG_FRAG_2 = "Frag 2";
    private static final String TAG_FRAG_3 = "Frag 3";
    private static final String TAG_FRAG_4 = "Frag 4";
    private static final String TAG_FRAG_5 = "Frag 5";
    public static String FG_TAG = TAG_FRAG_0;
    private DrawerLayout drawer;
    private Handler mHandler;
    public static int navItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mHandler = new Handler();
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                loadHomeFragment();
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setMainFrag();
    }

    private void setMainFrag() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, new Frag0(), FG_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void loadHomeFragment() {
        setActivityTitle();
        invalidateOptionsMenu();
        if (getSupportFragmentManager().findFragmentByTag(FG_TAG) != null) {
            // getSupportFragmentManager().popBackStack(FG_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            drawer.closeDrawers();
            return;
        }
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main_internal_storage content by replacing fragments
                Fragment fragment = getHomeFragment();
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.frame, fragment, FG_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                } else {
                    Toast.makeText(MainActivity.this, "Null", Toast.LENGTH_SHORT).show();
                }
            }
        };
        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                return new Frag0();
            case 1:
                return new Frag1();
            case 2:
                return new Frag2();
            case 3:
                return new Frag3();
            case 4:
                return new Frag4();
            case 5:
                return new Frag5();
            default:
                return new Frag0();
        }
    }

    private void setActivityTitle() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_internal_storage:
                navItemIndex = 0;
                FG_TAG = TAG_FRAG_0;
                break;
            case R.id.nav_external_storage:
                navItemIndex = 1;
                FG_TAG = TAG_FRAG_1;
                break;
            case R.id.nav_images:
                navItemIndex = 2;
                FG_TAG = TAG_FRAG_2;
                break;
            case R.id.nav_audios:
                navItemIndex = 3;
                FG_TAG = TAG_FRAG_3;
                break;
            case R.id.nav_videos:
                navItemIndex = 4;
                FG_TAG = TAG_FRAG_4;
                break;
            case R.id.nav_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "FlyCabs");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Download  here to visit https://play.google.com/store/apps/details?id=com.droids.tamada.filemanager&hl=en ");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            case R.id.nav_settings:
                navItemIndex = 5;
                FG_TAG = TAG_FRAG_5;
                break;
        }
//        removeFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void removeFragment() {
        if (getSupportFragmentManager().findFragmentByTag(FG_TAG) == null) {
            try {
                getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.frame)).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
