package com.appromobile.partnerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.adapter.FaqAdapter;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.model.api.FqaInformationForm;
import com.appromobile.partnerapp.presenter.Presenter.PresenterFaq;
import com.appromobile.partnerapp.presenter.view.ViewFaq;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswer extends BaseActivity implements View.OnClickListener, ViewFaq {
    public static QuestionAnswer isRunning;
    //Toolbar
    //Toolbar
    private Toolbar toolbar;
    private ImageView toolbarMenu, toolbarHome;
    private TextView toolbarTitle;
    //Toolbar
    //Toolbar
    private ListView listView;
    private FaqAdapter adapter;
    private List<FqaInformationForm> list;

    PresenterFaq presenterFaq = new PresenterFaq(this);

    private final static int limit = 20;
    private static int offset = 0;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);
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
        toolbarTitle.setText(getString(R.string.txt_10_faq));
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listView_faq);
        list = new ArrayList<>();
        adapter = new FaqAdapter(this, list);
        listView.setAdapter(adapter);
        scrollListView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        createData();
    }

    //Create Data
    private void createData() {
        isLoading = true;
        offset = 0;
        list.clear();

        presenterFaq.findLimitFaqInfomationList(this, offset, limit);
    }

    private void loadMore() {
        isLoading = true;
        presenterFaq.findLimitFaqInfomationList(this, offset, limit);
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
    public void findLimitFaqInfomationList(List<FqaInformationForm> listFaq) {
        if (listFaq.size() > 0) {

            list.addAll(listFaq);
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
                Intent exit = new Intent(QuestionAnswer.this, Quit.class);
                startActivity(exit);
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }
}
