package com.appromobile.partnerapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.CallbackDialogInput;
import com.appromobile.partnerapp.dialog.CallbackDialogListView;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.dialog.DialogInput;
import com.appromobile.partnerapp.dialog.DialogListView;
import com.appromobile.partnerapp.model.api.CancelSettingDto;
import com.appromobile.partnerapp.model.api.HotelSettingDto;
import com.appromobile.partnerapp.model.api.ResSettingDto;
import com.appromobile.partnerapp.model.api.ResSettingForm;
import com.appromobile.partnerapp.model.storage.InfoHotel;
import com.appromobile.partnerapp.presenter.Presenter.PresenterSetting;
import com.appromobile.partnerapp.presenter.view.ViewSetting;
import com.appromobile.partnerapp.storage.Storage;


public class Setting extends BaseActivity implements View.OnClickListener, ViewSetting {

    public static Setting isRunning;
    //Toolbar
    //Toolbar
    private Toolbar toolbar;
    private ImageView toolbarMenu, toolbarHome;
    private TextView toolbarTitle;
    //Toolbar
    //Toolbar

    private TextView txtCheckout, txtOvernightFrom, txtOvernightTo, txtNumberLines, txtCancelBefore;
    private ImageView imgCancelAuto, imgCancelManual, imgConfirmAuto, imgConfirmManual, imgWeekendOn, imgWeekendOff;
    private static final int AUTO = 1;
    private static final int BYPASS = 2;
    private static int cancelStatus = 1;
    private static int weekendStatus = 1;

    private PresenterSetting presenterSetting = new PresenterSetting(this);
    private InfoHotel infoHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
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
        toolbarTitle.setText(getString(R.string.txt_1_2_setting));
    }

    private void init() {

        infoHotel = Storage.getInstance(this).getInfoHotel();

        txtCheckout = (TextView) findViewById(R.id.textView_setting_check_out_time);
        txtCheckout.setOnClickListener(this);
        txtOvernightFrom = (TextView) findViewById(R.id.textView_setting_overnight_time_from);
        txtOvernightFrom.setOnClickListener(this);
        txtOvernightTo = (TextView) findViewById(R.id.textView_setting_overnight_time_to);
        txtOvernightTo.setOnClickListener(this);
        txtNumberLines = (TextView) findViewById(R.id.textView_setting_number_lines);
        txtNumberLines.setOnClickListener(this);
        txtCancelBefore = (TextView) findViewById(R.id.textView_setting_cancel_status_before);
        txtCancelBefore.setOnClickListener(this);

        imgCancelAuto = (ImageView) findViewById(R.id.imageView_setting_cancel_auto);
        imgCancelAuto.setImageResource(R.drawable.box);
        imgCancelAuto.setOnClickListener(this);
        imgCancelManual = (ImageView) findViewById(R.id.imageView_setting_cancel_manual);
        imgCancelManual.setImageResource(R.drawable.box);
        imgCancelManual.setOnClickListener(this);
        imgConfirmAuto = (ImageView) findViewById(R.id.imageView_setting_confirm_auto);
        imgConfirmAuto.setImageResource(R.drawable.box);
        imgConfirmAuto.setOnClickListener(this);
        imgConfirmManual = (ImageView) findViewById(R.id.imageView_setting_confirm_manual);
        imgConfirmManual.setImageResource(R.drawable.box);
        imgConfirmManual.setOnClickListener(this);
        imgWeekendOn = (ImageView) findViewById(R.id.imageView_setting_weekend_on);
        imgWeekendOn.setImageResource(R.drawable.box);
        imgWeekendOn.setOnClickListener(this);
        imgWeekendOff = (ImageView) findViewById(R.id.imageView_setting_weekend_off);
        imgWeekendOff.setImageResource(R.drawable.box);
        imgWeekendOff.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    private void loadData() {
        presenterSetting.findReservationSetting(this, infoHotel.getSn());
        presenterSetting.findHotelSettingForm(this);
    }

    //Lines Display
    private void showListView(final TextView textView, int range) {
        DialogListView.getInstance().show(this, range, new CallbackDialogListView() {
            @Override
            public void listViewSelect(String txt, int type) {
                //Return from dialog
                if (type == 0) { //Lines Display
                    updateHotelSettingForm(textView, txt);
                } else {
                    updateReservation(textView, txt, weekendStatus);
                }

            }
        });
    }

    private void showInput() {
        DialogInput.getInstance().show(this, txtCancelBefore.getText().toString(), new CallbackDialogInput() {
            @Override
            public void getInput(String input) {
                if (input != null) {
                    //Return from dialog
                    if (cancelStatus == AUTO) {
                        updateCancelStatus(cancelStatus, input);
                    } else {
                        updateCancelStatus(cancelStatus, input);
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_setting_check_out_time:
                showListView(txtCheckout, 24);
                break;
            case R.id.textView_setting_overnight_time_from:
                showListView(txtOvernightFrom, 24);
                break;
            case R.id.textView_setting_overnight_time_to:
                showListView(txtOvernightTo, 24);
                break;
            case R.id.textView_setting_number_lines:
                //set 0 = 5-10-15-20-25-30
                showListView(txtNumberLines, 0);
                break;
            case R.id.textView_setting_cancel_status_before:
                showInput();
                break;
            case R.id.imageView_setting_cancel_auto:
                updateCancelStatus(AUTO, txtCancelBefore.getText().toString());
                cancelStatus = AUTO;
                break;
            case R.id.imageView_setting_cancel_manual:
                updateCancelStatus(BYPASS, txtCancelBefore.getText().toString());
                cancelStatus = BYPASS;
                break;
            case R.id.imageView_setting_confirm_auto:
                updateConfirmStatus(AUTO);
                break;
            case R.id.imageView_setting_confirm_manual:
                updateConfirmStatus(BYPASS);
                break;
            case R.id.imageView_setting_weekend_on:
                updateReservation(null, null, AUTO);
                weekendStatus = AUTO;
                break;
            case R.id.imageView_setting_weekend_off:
                updateReservation(null, null, BYPASS);
                weekendStatus = BYPASS;
                break;
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

    private void updateCancelStatus(int status, String input) {
        CancelSettingDto cancelSettingDto = new CancelSettingDto();
        cancelSettingDto.setAcceptCancel(status);
        cancelSettingDto.setCancelByPassHours(Integer.parseInt(input));
        presenterSetting.updateCancelledSetting(this, cancelSettingDto, txtCancelBefore, input, imgCancelAuto, imgCancelManual, status);
    }

    private void updateConfirmStatus(int status) {
        if (status == AUTO) {
            presenterSetting.updateAutoReserved(this, true, imgConfirmAuto, imgConfirmManual);
        } else {
            presenterSetting.updateAutoReserved(this, false, imgConfirmAuto, imgConfirmManual);
        }
    }

    private void updateHotelSettingForm(TextView textView, String numberOfRecord) {
        HotelSettingDto hotelSettingDto = new HotelSettingDto();
        hotelSettingDto.setNumberOfRecord(Integer.parseInt(numberOfRecord));

        presenterSetting.updateHotelSettingForm(this, hotelSettingDto, textView, numberOfRecord);
    }

    private void updateReservation(TextView textView, String value, int status) {
        ResSettingDto resSettingDto = new ResSettingDto();
        if (textView != null && textView == txtCheckout) {
            resSettingDto.setCheckOutOneday(Integer.parseInt(value));
            resSettingDto.setStartOvernight(Integer.parseInt(txtOvernightFrom.getText().toString()));
            resSettingDto.setEndOvernight(Integer.parseInt(txtOvernightTo.getText().toString()));
        } else if (textView != null && textView == txtOvernightFrom) {
            resSettingDto.setCheckOutOneday(Integer.parseInt(txtCheckout.getText().toString()));
            resSettingDto.setStartOvernight(Integer.parseInt(value));
            resSettingDto.setEndOvernight(Integer.parseInt(txtOvernightTo.getText().toString()));
        } else if (textView != null && textView == txtOvernightTo) {
            resSettingDto.setCheckOutOneday(Integer.parseInt(txtCheckout.getText().toString()));
            resSettingDto.setStartOvernight(Integer.parseInt(txtOvernightFrom.getText().toString()));
            resSettingDto.setEndOvernight(Integer.parseInt(value));
        }else {
            resSettingDto.setCheckOutOneday(Integer.parseInt(txtCheckout.getText().toString()));
            resSettingDto.setStartOvernight(Integer.parseInt(txtOvernightFrom.getText().toString()));
            resSettingDto.setEndOvernight(Integer.parseInt(txtOvernightTo.getText().toString()));
        }
        if (status == AUTO) {
            resSettingDto.setDisableWeekend(true);
            presenterSetting.updateReservationSetting(this, resSettingDto, textView, value, imgWeekendOn, imgWeekendOff, AUTO);
        } else if (status == BYPASS) {
            resSettingDto.setDisableWeekend(false);
            presenterSetting.updateReservationSetting(this, resSettingDto, textView, value, imgWeekendOn, imgWeekendOff, BYPASS);
        }
    }


    @Override
    public void findReservationSetting(ResSettingForm resSettingForm) {
        txtCheckout.setText(resSettingForm.getCheckOutOneday() + "");
        txtOvernightFrom.setText(resSettingForm.getStartOvernight() + "");
        txtOvernightTo.setText(resSettingForm.getEndOvernight() + "");
        txtCancelBefore.setText(resSettingForm.getCancelByPassHours() + "");

        if (resSettingForm.getAcceptCancel() == 1) { //auto
            imgCancelAuto.setImageResource(R.drawable.box_press);
            imgCancelManual.setImageResource(R.drawable.box);
            cancelStatus = AUTO;
        } else {
            imgCancelAuto.setImageResource(R.drawable.box);
            imgCancelManual.setImageResource(R.drawable.box_press);
            cancelStatus = BYPASS;
        }

        if (resSettingForm.getAcceptReserved() == 1) { //auto
            imgConfirmAuto.setImageResource(R.drawable.box_press);
            imgConfirmManual.setImageResource(R.drawable.box);
        } else {
            imgConfirmAuto.setImageResource(R.drawable.box);
            imgConfirmManual.setImageResource(R.drawable.box_press);
        }

        if (resSettingForm.isDisableWeekend()) { //on
            imgWeekendOn.setImageResource(R.drawable.box_press);
            imgWeekendOff.setImageResource(R.drawable.box);
            weekendStatus = AUTO;
        } else {
            imgWeekendOn.setImageResource(R.drawable.box);
            imgWeekendOff.setImageResource(R.drawable.box_press);
            weekendStatus = BYPASS;
        }
    }

    @Override
    public void findHotelSettingForm(int numbetOfRecord) {
        txtNumberLines.setText(numbetOfRecord + "");
    }

    @Override
    public void updateHotelSettingFormOk() {
        Toast.makeText(this, getString(R.string.txt_8_update_successful), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateReservationSettingOk() {
        Toast.makeText(this, getString(R.string.txt_8_update_successful), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCancelledSettingOk() {
        Toast.makeText(this, getString(R.string.txt_8_update_successful), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAutoReservedOk() {
        Toast.makeText(this, getString(R.string.txt_8_update_successful), Toast.LENGTH_SHORT).show();
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
                Intent exit = new Intent(Setting.this, Quit.class);
                startActivity(exit);
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }
}

