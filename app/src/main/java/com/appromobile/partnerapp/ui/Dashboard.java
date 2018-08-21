package com.appromobile.partnerapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.model.api.ReservationStatisticForm;
import com.appromobile.partnerapp.model.storage.InfoHotel;
import com.appromobile.partnerapp.presenter.Presenter.PresenterDashboard;
import com.appromobile.partnerapp.presenter.view.ViewDashboard;
import com.appromobile.partnerapp.dialog.Loading;
import com.appromobile.partnerapp.storage.Storage;

public class Dashboard extends BaseActivity implements View.OnClickListener, ViewDashboard {
    public static Dashboard isRunning;

    //Toolbar
    //Toolbar
    private Toolbar toolbar;
    private ImageView toolbarMenu, toolbarHome;
    private TextView toolbarTitle;
    //Toolbar
    //Toolbar

    private TextView txtToday, txtMonth, txtRevenue, txtReservation, txtWaiting, txtConfirm, txtCheckin, txtCancel;
    private LinearLayout lnReservation, lnWaiting, lnConfirm, lnCheckin, lnCancel;
    PresenterDashboard presenterDashboard = new PresenterDashboard(this);

    private int todayRevenu = 0;
    private int monthRevenu = 0;
    private boolean isToday = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
        toolbarHome.setVisibility(View.INVISIBLE);
        toolbarTitle = (TextView) findViewById(R.id.text);
        InfoHotel infoHotel = Storage.getInstance(this).getInfoHotel();
        if (infoHotel.getName() != null) {
            toolbarTitle.setText(infoHotel.getName());
        }
    }

    //Init
    private void init() {
        txtToday = (TextView) findViewById(R.id.textView_dashboard_today);
        txtToday.setOnClickListener(this);
        txtMonth = (TextView) findViewById(R.id.textView_dashboard_month);
        txtMonth.setOnClickListener(this);
        txtRevenue = (TextView) findViewById(R.id.textView_dashboard_total_revenue);
        txtReservation = (TextView) findViewById(R.id.textView_dashboard_total_reservation);
        txtWaiting = (TextView) findViewById(R.id.textView_dashboard_total_waiting);
        txtConfirm = (TextView) findViewById(R.id.textView_dashboard_total_confirm);
        txtCheckin = (TextView) findViewById(R.id.textView_dashboard_total_checkin);
        txtCancel = (TextView) findViewById(R.id.textView_dashboard_total_cancel);

        lnReservation = (LinearLayout) findViewById(R.id.linear_dashboard_reservation);
        lnReservation.setOnClickListener(this);
        lnWaiting = (LinearLayout) findViewById(R.id.linear_dashboard_wating);
        lnWaiting.setOnClickListener(this);
        lnConfirm = (LinearLayout) findViewById(R.id.linear_dashboard_confirm);
        lnConfirm.setOnClickListener(this);
        lnCheckin = (LinearLayout) findViewById(R.id.linear_dashboard_checkin);
        lnCheckin.setOnClickListener(this);
        lnCancel = (LinearLayout) findViewById(R.id.linear_dashboard_cancel);
        lnCancel.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    private void loadData() {
        presenterDashboard.findBookingStatisticForOwner(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_dashboard_today:
                isToday = true;
                setTotalRevenu();
                break;
            case R.id.textView_dashboard_month:
                isToday = false;
                setTotalRevenu();
                break;
            case R.id.linear_dashboard_reservation:
                gotoManagementReservation();
                break;
            case R.id.linear_dashboard_wating:
                break;
            case R.id.linear_dashboard_confirm:
                break;
            case R.id.linear_dashboard_checkin:
                break;
            case R.id.linear_dashboard_cancel:
                break;
            case R.id.menu:
                showMenu();
                break;
        }
    }

    private void gotoManagementReservation() {
        Intent reservation = new Intent(this, ManageReservation.class);
        startActivity(reservation);
        finish();
    }

    @Override
    public void getData(ReservationStatisticForm reservationStatisticForm) {
        txtReservation.setText(
                reservationStatisticForm.getWaitConfirm() +
                        reservationStatisticForm.getConfirmed() +
                        reservationStatisticForm.getCheckIn() +
                        reservationStatisticForm.getCanceled() + "");

        txtWaiting.setText(reservationStatisticForm.getWaitConfirm() + "");
        txtConfirm.setText(reservationStatisticForm.getConfirmed() + "");
        txtCheckin.setText(reservationStatisticForm.getCheckIn() + "");
        txtCancel.setText(reservationStatisticForm.getCanceled() + "");

        //store temp
        todayRevenu = reservationStatisticForm.getTodayRevenue();
        monthRevenu = reservationStatisticForm.getCurMonthRevenue();

        setTotalRevenu();
    }

    private void setTotalRevenu() {
        if (isToday) {
            txtToday.setText(getString(R.string.txt_1_2_today));
            txtMonth.setText(getString(R.string.txt_1_2_this_month));
            txtRevenue.setText(todayRevenu + "");
        } else {
            txtToday.setText(getString(R.string.txt_1_2_this_month));
            txtMonth.setText(getString(R.string.txt_1_2_today));
            txtRevenue.setText(monthRevenu + "");
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
                Intent exit = new Intent(Dashboard.this, Quit.class);
                startActivity(exit);
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }

}
