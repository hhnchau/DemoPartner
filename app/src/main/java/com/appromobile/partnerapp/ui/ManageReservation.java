package com.appromobile.partnerapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.adapter.ManageReservationAdapter;
import com.appromobile.partnerapp.adapter.SpinnerAdapter;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.CallbackDialogCalendar;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.dialog.DialogCalendar;
import com.appromobile.partnerapp.model.api.ConfirmBookingDto;
import com.appromobile.partnerapp.model.api.UpdateBookingStatusDto;
import com.appromobile.partnerapp.model.api.UserBookingForm;
import com.appromobile.partnerapp.presenter.Presenter.PresenterManageReservation;
import com.appromobile.partnerapp.presenter.view.ViewManageReservation;
import com.appromobile.partnerapp.utils.Helper;
import com.appromobile.partnerapp.utils.HelperSpinner;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

public class ManageReservation extends BaseActivity implements View.OnClickListener, ViewManageReservation {
    public static ManageReservation isRunning;
    //Toolbar
    //Toolbar
    private Toolbar toolbar;
    private ImageView toolbarMenu, toolbarHome;
    private TextView toolbarTitle;
    //Toolbar
    //Toolbar

    private ListView listView;
    private ManageReservationAdapter adapter;
    private List<UserBookingForm> list;

    private LinearLayout lnCalendar;
    private TextView txtCalendar, txtTotal;

    private Spinner spinner;
    private List<String> listSpinner;
    private SpinnerAdapter spinnerAdapter;

    private PresenterManageReservation presenterManageReservation = new PresenterManageReservation(this);
    private CalendarDay calendarDayTag;

    private final static int limit = 20;
    private static int offset = 0;
    private boolean isLoading = false;
    private boolean loadNew = true;

    private static final int CHECK_IN = 2;
    private static final int CANCEL = 3;

    private static final int ID_ALL = 0;
    private static final int ID_CONFIRM = 1;
    private static final int ID_CHECK_IN = 2;
    private static final int ID_CANCEL = 3;
    private static final int ID_WAIT = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reservation);
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
        toolbarTitle.setText(getString(R.string.txt_1_2_reservation));
    }

    //Init
    private void init() {
        //create ListView
        listView = (ListView) findViewById(R.id.listView_manage_reservation);
        list = new ArrayList<>();
        adapter = new ManageReservationAdapter(this, list);
        listView.setAdapter(adapter);

        lnCalendar = (LinearLayout) findViewById(R.id.linearLayout_manage_reservation_calendar);
        lnCalendar.setOnClickListener(this);
        txtCalendar = (TextView) findViewById(R.id.textView_manage_reservation_calendar);

        txtTotal = (TextView) findViewById(R.id.textView_manage_reservation_total);

        //create Spinner
        spinner = (Spinner) findViewById(R.id.spiner_manage_reservation);
        listSpinner = new ArrayList<>();
        spinnerAdapter = new SpinnerAdapter(this, listSpinner);
        createDataSpinner();
        spinner.setAdapter(spinnerAdapter);

        onClickSpinner();
        scrollListView();
        onItemClick();
    }

    @Override
    protected void onResume() {
        super.onResume();

        createData();

    }

    //Load Default Data
    private void createData() {
        //getCurrent day
        upadteCalendar(Helper.getCurrentDate());
    }

    //Create Data Spinner
    private void createDataSpinner() {
        listSpinner.add(getString(R.string.txt_1_2_wait));
        listSpinner.add(getString(R.string.txt_1_2_confirm));
        listSpinner.add(getString(R.string.txt_1_2_checkin));
        listSpinner.add(getString(R.string.txt_1_2_cancel));
        listSpinner.add(getString(R.string.txt_3_1_all));
    }

    //Onclick Spinner
    private void onClickSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Load Data
                loadData();

                //Set Color
                HelperSpinner.setColor(txtTotal, position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Update Calendar
    private void upadteCalendar(CalendarDay calendarDay) {
        calendarDayTag = calendarDay;
        txtCalendar.setText(calendarDay.getDay() + "-" + calendarDay.getMonth());
    }

    //Call Api
    private void loadData() {
        loadNew = true;
        isLoading = true;
        offset = 0;
        list.clear();
        String day = calendarDayTag.getYear() + "-" + calendarDayTag.getMonth() + "-" + calendarDayTag.getDay();

        presenterManageReservation.findLimitReservationListViaHotel(this, getIdReservation(spinner.getSelectedItemPosition()), day, offset, limit);
        presenterManageReservation.countAllReservationListViaHotel(this, getIdReservation(spinner.getSelectedItemPosition()), day);
    }

    //Load More
    private void loadMore() {
        isLoading = true;
        String day = calendarDayTag.getYear() + "-" + calendarDayTag.getMonth() + "-" + calendarDayTag.getDay();
        presenterManageReservation.findLimitReservationListViaHotel(this, getIdReservation(spinner.getSelectedItemPosition()), day, offset, limit);
    }

    private int getIdReservation(int position) {
        int id;
        if (position == 0) {
            id = ID_WAIT;
        } else if (position == 1) {
            id = ID_CONFIRM;
        } else if (position == 2) {
            id = ID_CHECK_IN;
        }
        if (position == 3) {
            id = ID_CANCEL;
        } else {
            id = ID_ALL;
        }
        return id;
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

    //onItemClick ListView
    private void onItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (list.get(position).getConfirmed() == 0) {//wait
                    showDialogConfirm(position);
                } else if (list.get(position).getBookingStatus() == 1) {//confirmed
                    showDialogCheckin(position);
                }
            }
        });
    }

    //Show dialog Confirm
    private void showDialogConfirm(final int position) {
        Dialag.getInstance().show(this, false, true, getString(R.string.txt_3_1_want_confrm), getString(R.string.txt_3_1_no), getString(R.string.txt_10_yes), new CallbackDialag() {
            @Override
            public void ok() {
                ConfirmBookingDto confirmBookingDto = new ConfirmBookingDto();
                confirmBookingDto.setAccept(true);
                confirmBookingDto.setSn(list.get(position).getSn());
                presenterManageReservation.confirmReservationStatus(ManageReservation.this, confirmBookingDto, position);
            }

            @Override
            public void cancel() {
                ConfirmBookingDto confirmBookingDto = new ConfirmBookingDto();
                confirmBookingDto.setAccept(false);
                confirmBookingDto.setSn(list.get(position).getSn());
                presenterManageReservation.confirmReservationStatus(ManageReservation.this, confirmBookingDto, position);
            }
        });
    }

    //Show dialog Check-in
    private void showDialogCheckin(final int position) {
        Dialag.getInstance().show(this, false, true, getString(R.string.txt_3_1_want_checkin), getString(R.string.txt_3_1_cancel), getString(R.string.txt_3_1_receive), new CallbackDialag() {
            @Override
            public void ok() {
                UpdateBookingStatusDto updateBookingStatusDto = new UpdateBookingStatusDto();
                updateBookingStatusDto.setSn(list.get(position).getSn());
                updateBookingStatusDto.setToStatus(CHECK_IN);
                presenterManageReservation.updateReservationStatus(ManageReservation.this, updateBookingStatusDto, position);
            }

            @Override
            public void cancel() {
                UpdateBookingStatusDto updateBookingStatusDto = new UpdateBookingStatusDto();
                updateBookingStatusDto.setSn(list.get(position).getSn());
                updateBookingStatusDto.setToStatus(CANCEL);
                presenterManageReservation.updateReservationStatus(ManageReservation.this, updateBookingStatusDto, position);
            }
        });
    }

    //Show Dialog Calendar
    private void showDialogCalendar(CalendarDay calendarDay) {

        DialogCalendar.getInstance().show(this, calendarDay, new CallbackDialogCalendar() {
            @Override
            public void getCalendar(CalendarDay calendarDay) {
                //Calendar callback
                upadteCalendar(calendarDay);

                //Load Data
                loadData();

            }
        });
    }

    @Override
    public void listManageReservation(List<UserBookingForm> listManageReservation) {

        if (listManageReservation.size() != 0) {
            list.addAll(listManageReservation);
            adapter.notifyDataSetChanged();

            offset = adapter.getCount();

            isLoading = false;

        } else {
            if (loadNew) {
                //show no result
                TextView txt = (TextView) findViewById(R.id.view_no_result);
                listView.setEmptyView(txt);

                loadNew = false;
            } else {
                //Set End List
                isLoading = true;
            }
        }
    }

    @Override
    public void countAllReservationListViaHotel(int count) {
        txtTotal.setText(count + "");
    }

    @Override
    public void confirmReservationOk(int p) {
        Toast.makeText(ManageReservation.this, getString(R.string.txt_3_1_confirmed_successfully), Toast.LENGTH_SHORT).show();
        list.remove(p);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void confirmReservationFail(int p) {
        Toast.makeText(ManageReservation.this, getString(R.string.txt_3_1_cancel_successfull), Toast.LENGTH_SHORT).show();
        list.remove(p);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateReservationStatusOk(int p) {
        Toast.makeText(ManageReservation.this, getString(R.string.txt_3_1_check_in_successfull), Toast.LENGTH_SHORT).show();
        list.remove(p);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateReservationStatusFail(int p) {
        Toast.makeText(ManageReservation.this, getString(R.string.txt_3_1_cancel_successfull), Toast.LENGTH_SHORT).show();
        list.remove(p);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout_manage_reservation_calendar:
                showDialogCalendar(calendarDayTag);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = null;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Dialag.getInstance().show(this, false, false, getString(R.string.txt_quit), getString(R.string.txt_3_1_no), getString(R.string.txt_10_yes), new CallbackDialag() {
            @Override
            public void ok() {
                //Quit
                Intent exit = new Intent(ManageReservation.this, Quit.class);
                startActivity(exit);
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }
}
