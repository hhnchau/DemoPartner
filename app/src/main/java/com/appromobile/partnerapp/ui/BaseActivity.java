package com.appromobile.partnerapp.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.adapter.DrawerAdapter;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.model.other.DrawerObject;
import com.appromobile.partnerapp.presenter.Presenter.PresenterLogout;
import com.appromobile.partnerapp.presenter.view.ViewLogout;
import com.appromobile.partnerapp.storage.Storage;
import com.appromobile.partnerapp.utils.Helper;

import java.util.ArrayList;

/**
 * Created by Chau Huynh on 3/19/2017.
 */

public class BaseActivity extends AppCompatActivity implements ViewLogout {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private ArrayList<DrawerObject> arrayList;
    private DrawerAdapter adapter;
    private FrameLayout frame;
    private float lastTranslate = 0.0f;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void createSlideMenu(String[] navMenuTitles, TypedArray navMenuIcons) {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        frame = (FrameLayout) findViewById(R.id.content_frame);
        arrayList = new ArrayList<>();

        if (navMenuIcons == null) {
            for (int i = 0; i < navMenuTitles.length; i++) {
                arrayList.add(new DrawerObject(navMenuTitles[i]));
            }
        } else {
            for (int i = 0; i < navMenuTitles.length; i++) {
                arrayList.add(new DrawerObject(navMenuTitles[i], navMenuIcons.getResourceId(i, -1)));
            }
        }

        //ListView Click
        listView.setOnItemClickListener(new SlideMenuClickListener());
        adapter = new DrawerAdapter(getApplicationContext(), arrayList);
        listView.setAdapter(adapter);

        //Set Toolbar
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.menu);


        //Set Action Togge
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //getSupportActionBar().setTitle("Close");
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("Open");
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = (listView.getWidth() * slideOffset);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    frame.setTranslationX(moveFactor);
                } else {
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    frame.startAnimation(anim);

                    lastTranslate = moveFactor;
                }
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    public void hideMenu() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void showMenu() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    //ListView Click;
    private class SlideMenuClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    if (ManageReservation.isRunning == null) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent reservation = new Intent(BaseActivity.this, ManageReservation.class);
                                startActivity(reservation);
                                finish();
                                //overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_stable);
                            }
                        }, 150);
                    }
                    break;
                case 1:
                    if (InfoPromotion.isRunning == null) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent prmotion = new Intent(BaseActivity.this, InfoPromotion.class);
                                startActivity(prmotion);
                                finish();
                                //overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_stable);
                            }
                        }, 150);
                    }
                    break;
                case 2:
                    if (Comment.isRunning == null) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent comment = new Intent(BaseActivity.this, Comment.class);
                                startActivity(comment);
                                finish();
                                //overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_stable);
                            }
                        }, 150);
                    }
                    break;
                case 3:
                    if (QuestionAnswer.isRunning == null) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent faq = new Intent(BaseActivity.this, QuestionAnswer.class);
                                startActivity(faq);
                                finish();
                                //overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_stable);
                            }
                        }, 150);
                    }
                    break;
                case 4:
                    if (Setting.isRunning == null) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent setting = new Intent(BaseActivity.this, Setting.class);
                                startActivity(setting);
                                finish();
                                //overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_stable);
                            }
                        }, 150);
                    }
                    break;
                case 5:
                    showDialogLogout();
                    break;
            }
            listView.setItemChecked(position, true);
            listView.setSelection(position);
            //close Drawer
            drawerLayout.closeDrawer(listView);
        }
    }

    private void showDialogLogout() {
        Dialag.getInstance().show(BaseActivity.this, true,false, getString(R.string.txt_10_logout), getString(R.string.txt_3_1_no), getString(R.string.txt_10_yes), new CallbackDialag() {
            @Override
            public void ok() {
                PresenterLogout presenterLogout = new PresenterLogout(new ViewLogout() {
                    @Override
                    public void logoutOk() {
                        //Set Logout
                        Storage.getInstance(BaseActivity.this).setAutoLogin(false);
                        Intent splash = new Intent(BaseActivity.this, Splash.class);
                        startActivity(splash);
                        finish();
                        //overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_stable);
                    }
                });
                presenterLogout.logout(BaseActivity.this, Helper.getDeviceId(BaseActivity.this));
            }

            @Override
            public void cancel() {

            }
        });
    }

    @Override
    public void logoutOk() {

    }
}

