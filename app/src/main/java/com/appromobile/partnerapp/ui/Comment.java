package com.appromobile.partnerapp.ui;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.adapter.CommentAdapter;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.model.api.UserReviewForm;
import com.appromobile.partnerapp.model.storage.InfoHotel;
import com.appromobile.partnerapp.presenter.Presenter.PresenterComment;
import com.appromobile.partnerapp.presenter.view.ViewCommnet;
import com.appromobile.partnerapp.storage.Storage;
import com.appromobile.partnerapp.utils.HelperRating;

import java.util.ArrayList;
import java.util.List;

public class Comment extends BaseActivity implements ViewCommnet, View.OnClickListener {

    public static Comment isRunning;
    //Toolbar
    //Toolbar
    private Toolbar toolbar;
    private ImageView toolbarMenu, toolbarHome;
    private TextView toolbarTitle;
    //Toolbar
    //Toolbar

    private ListView listView;
    private CommentAdapter adapter;
    private List<UserReviewForm> list;

    private TextView txtTotal;
    private ImageView star1, star2, star3, star4, star5;

    private PresenterComment presenterComment = new PresenterComment(this);

    private InfoHotel infoHotel;

    private final static int limit = 20;
    private static int offset = 0;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        isRunning = this;
        createMenu();
        init();
    }

    //Create Menu
    private void createMenu() {
        createSlideMenu(
                getResources().getStringArray(R.array.nav_drawer_items),
                getResources().obtainTypedArray(R.array.nav_drawer_icons)
        );

        //menu
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //menu
        toolbarMenu = (ImageView) findViewById(R.id.menu);
        toolbarMenu.setOnClickListener(this);
        toolbarHome = (ImageView) findViewById(R.id.home);
        toolbarHome.setOnClickListener(this);
        toolbarTitle = (TextView) findViewById(R.id.text);
        toolbarTitle.setText(getString(R.string.txt_1_2_review));
    }

    private void init() {
        infoHotel = Storage.getInstance(this).getInfoHotel();
        listView = (ListView) findViewById(R.id.listView_comment);
        list = new ArrayList<>();
        adapter = new CommentAdapter(this, list);
        listView.setAdapter(adapter);
        scrollListView();

        txtTotal = (TextView) findViewById(R.id.textView_comment_total);
        star1 = (ImageView) findViewById(R.id.imageView_comment_star_1);
        star2 = (ImageView) findViewById(R.id.imageView_comment_star_2);
        star3 = (ImageView) findViewById(R.id.imageView_comment_star_3);
        star4 = (ImageView) findViewById(R.id.imageView_comment_star_4);
        star5 = (ImageView) findViewById(R.id.imageView_comment_star_5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createData();
    }

    //Create Data
    private void createData() {

        setUi();

        isLoading = true;
        offset = 0;
        list.clear();
        presenterComment.countAllReviewList(this, infoHotel.getSn());
        presenterComment.findUserReviewList(this, infoHotel.getSn(), offset, limit);
    }

    private void setUi() {
        HelperRating.setRating((float) infoHotel.getAverageMark(), star1, star2, star3, star4, star5);
    }

    private void loadMore() {
        presenterComment.findUserReviewList(this, infoHotel.getSn(), offset, limit);
    }

    //ListView Scroll
    private void scrollListView() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!isLoading && firstVisibleItem + visibleItemCount + 10 >= totalItemCount && totalItemCount != 0) {
                    loadMore();
                }
            }
        });
    }


    @Override
    public void viewHotelDetail() {

    }

    @Override
    public void countAllRevireList(int count) {
        txtTotal.setText(count + "");
    }

    @Override
    public void findUserReviewList(List<UserReviewForm> listFindUserReviewList) {

        if (listFindUserReviewList.size() > 0) {

            list.addAll(listFindUserReviewList);
            adapter.notifyDataSetChanged();

            offset = adapter.getCount();

            if (offset < limit) {
                isLoading = true;
            } else {
                isLoading = false;
            }

        } else {

            //Set End List
            isLoading = true;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                Intent dashboard = new Intent(this, Dashboard.class);
                startActivity(dashboard);
                finish();
                break;
            case R.id.menu:
                showMenu();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        isRunning = null;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Dialag.getInstance().show(this, false,false, getString(R.string.txt_quit), getString(R.string.txt_3_1_no), getString(R.string.txt_10_yes), new CallbackDialag() {
            @Override
            public void ok() {
                //Quit
                Intent exit = new Intent(Comment.this, Quit.class);
                startActivity(exit);
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }
}
