package com.appromobile.partnerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.model.other.ChildInfoPromotion;
import com.appromobile.partnerapp.model.other.GroupInfoPromotion;
import com.appromobile.partnerapp.adapter.InfoPromotionAdapter;
import com.appromobile.partnerapp.model.api.PromotionForm;
import com.appromobile.partnerapp.presenter.Presenter.PresenterPromotion;
import com.appromobile.partnerapp.presenter.view.ViewPromotion;


import java.util.ArrayList;
import java.util.List;

public class InfoPromotion extends BaseActivity implements ViewPromotion, View.OnClickListener {
    public static InfoPromotion isRunning;
    //Toolbar
    //Toolbar
    private Toolbar toolbar;
    private ImageView toolbarMenu, toolbarHome;
    private TextView toolbarTitle;
    //Toolbar
    //Toolbar

    private ExpandableListView expandableListView;

    private ArrayList<GroupInfoPromotion> group;
    private InfoPromotionAdapter adapter;

    private PresenterPromotion presenterPromotion = new PresenterPromotion(this);

    private final static int limit = 20;
    private static int offset = 0;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_promotion);
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
        toolbarTitle.setText(getString(R.string.txt_1_2_promotion));
    }

    private void init() {
        expandableListView = (ExpandableListView) findViewById(R.id.expand_listVIew);
        group = new ArrayList<>();
        adapter = new InfoPromotionAdapter(this, group);
        expandableListView.setAdapter(adapter);
        scrollListView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        createData();
    }

    //Load Data
    private void createData() {
        loadNewData();
    }

    //Load New Data
    private void loadNewData() {
        isLoading = true;
        offset = 0;
        presenterPromotion.findLimitPromotionListForPartner(this, offset, limit);
    }

    //Load More
    private void loadMore() {
        isLoading = true;
        presenterPromotion.findLimitPromotionListForPartner(this, offset, limit);
    }

    //ListView Scroll
    private void scrollListView() {
        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!isLoading && firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0) {
                    loadMore();
                }
            }
        });
    }

    @Override
    public void findLimitPromotionListForPartner(List<PromotionForm> listPromotion) {
        if (listPromotion.size() > 0) {
            for (int i = 0; i < listPromotion.size(); i++) {

                GroupInfoPromotion groupInfoPromotion = new GroupInfoPromotion();
                groupInfoPromotion.setTitle(listPromotion.get(i).getTitle());
                groupInfoPromotion.setContent(listPromotion.get(i).getContent());
                groupInfoPromotion.setStatus(listPromotion.get(i).getStatus());
                groupInfoPromotion.setApply(listPromotion.get(i).isApply());

                ChildInfoPromotion childInfoPromotion = new ChildInfoPromotion();
                childInfoPromotion.setDiscount(listPromotion.get(i).getDiscount());
                childInfoPromotion.setApplyStart(listPromotion.get(i).getApplyStart());
                childInfoPromotion.setApplyEnd(listPromotion.get(i).getApplyEnd());
                childInfoPromotion.setCouponStart(listPromotion.get(i).getCouponStart());
                childInfoPromotion.setCouponEnd(listPromotion.get(i).getCouponEnd());

                groupInfoPromotion.childs.add(childInfoPromotion);

                group.add(groupInfoPromotion);
            }

            adapter.notifyDataSetChanged();

            offset = adapter.getGroupCount();

            if (offset < limit) {
                isLoading = true;
            } else {
                isLoading = false;
            }

        } else {
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
                Intent exit = new Intent(InfoPromotion.this, Quit.class);
                startActivity(exit);
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }
}
