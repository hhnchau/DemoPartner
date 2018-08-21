package com.appromobile.partnerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;
import com.appromobile.partnerapp.model.api.StaffLoginDto;
import com.appromobile.partnerapp.model.storage.InfoLogin;
import com.appromobile.partnerapp.presenter.Presenter.PresenterLogin;
import com.appromobile.partnerapp.presenter.view.ViewLogin;
import com.appromobile.partnerapp.storage.Storage;
import com.appromobile.partnerapp.utils.Helper;

public class Login extends AppCompatActivity implements View.OnClickListener, ViewLogin {
    public static Login isRunning;
    private EditText edtUsername, edtPassword;
    private ImageView imgRemember;
    private Button btnLogin;
    private PresenterLogin presenterLogin = new PresenterLogin(this);
    private int hotelSn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isRunning = this;
        Init();
        closeAllActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadInfoLoginCache();
    }

    //Init
    private void Init() {
        edtUsername = (EditText) findViewById(R.id.edittext_login_username);
        edtUsername.setOnClickListener(this);
        edtPassword = (EditText) findViewById(R.id.edittex_login_password);
        edtPassword.setOnClickListener(this);
        imgRemember = (ImageView) findViewById(R.id.imageView_login_remember);
        imgRemember.setOnClickListener(this);
        imgRemember.setTag("False");
        btnLogin = (Button) findViewById(R.id.button_login_login);
        btnLogin.setOnClickListener(this);
    }

    private void closeAllActivity() {
        if (Splash.isRunning != null) {
            Splash.isRunning.finish();
        } else if (InfoPromotion.isRunning != null) {
            InfoPromotion.isRunning.finish();
        } else if (Comment.isRunning != null) {
            Comment.isRunning.finish();
        } else if (QuestionAnswer.isRunning != null) {
            QuestionAnswer.isRunning.finish();
        } else if (Setting.isRunning != null) {
            Setting.isRunning.finish();
        } else if (ManageReservation.isRunning != null) {
            ManageReservation.isRunning.finish();
        } else if (Dashboard.isRunning != null) {
            Dashboard.isRunning.finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edittext_login_username:
                //Keyboard.show(this, edtUsername);
                break;

            case R.id.edittex_login_password:
                //Keyboard.hide(this);
                break;

            case R.id.button_login_login:
                //Login Press
                checkInput();
                break;

            case R.id.imageView_login_remember:
                //Check Remember Press
                if (imgRemember.getTag().equals("False")) {
                    imgRemember.setTag("True");
                    imgRemember.setImageResource(R.drawable.box_press);
                } else {
                    imgRemember.setTag("False");
                    imgRemember.setImageResource(R.drawable.box);
                }
                break;
        }
    }

    private void checkInput() {
        if (edtUsername.getText().toString().trim().equals("")) {
            edtUsername.setError(getString(R.string.msg_0_please_enter_your_user_name));
            return;
        }
        if (edtPassword.getText().toString().trim().equals("")) {
            edtPassword.setError(getString(R.string.msg_0_please_enter_your_password));
            return;
        }
        if (!edtUsername.getText().toString().trim().equals("") || !edtPassword.getText().toString().trim().equals("")) {

            //Call Api Login
            StaffLoginDto staffLoginDto = new StaffLoginDto();
            staffLoginDto.setMobileUserId(Helper.getDeviceId(this));
            staffLoginDto.setUserId(edtUsername.getText().toString());
            staffLoginDto.setPassword(Helper.setMD5(edtPassword.getText().toString()));
            //Call Api
            presenterLogin.loginViaMobile(this, staffLoginDto);

        }
    }

    private void updateStaffToken() {
        StaffDeviceInfoDto staffDeviceInfoDto = new StaffDeviceInfoDto();
        staffDeviceInfoDto.setMobileUserId(Helper.getDeviceId(this));
        staffDeviceInfoDto.setLanguage(Helper.getLanguages());
        staffDeviceInfoDto.setOs(Helper.getOz());
        staffDeviceInfoDto.setPartnerVersion(Helper.getPartnerVerzion());
        staffDeviceInfoDto.setOsVersion(Helper.getOsVersion());
        //Push Notify
        String tokenGCM = getIntent().getStringExtra("TOKENGCM");
        if (tokenGCM != null) {
            staffDeviceInfoDto.setTokenId(tokenGCM);
            Log.d("Token_GCM ---> ", tokenGCM);
        }

        presenterLogin.updateStaffToken(this, staffDeviceInfoDto);
    }

    private void gotoDashboard() {
        //getHotelName
        Intent dashboard = new Intent(this, Dashboard.class);
        startActivity(dashboard);
        finish();
    }

    //Get Name Hotel
    private void viewHotelDetail() {
        presenterLogin.viewHotelDetail(this, hotelSn);
    }

    private void storeAutoLogin() {
        if (imgRemember.getTag().equals("False")) {
            Storage.getInstance(this).setAutoLogin(false);
        } else {
            Storage.getInstance(this).setAutoLogin(true);
        }
    }

    private void loadInfoLoginCache() {
        InfoLogin infoLogin = Storage.getInstance(this).getInfoLogin();
        if (infoLogin != null) {
            if (infoLogin.isRemember()) {
                imgRemember.setImageResource(R.drawable.box_press);
                imgRemember.setTag("True");
                if (infoLogin.getUsername() != null) {
                    edtUsername.setText(infoLogin.getUsername());
                }
//                if (infoLogin.getPassword() != null) {
//                    edtPassword.setText(infoLogin.getPassword());
//                }
            } else {
                imgRemember.setImageResource(R.drawable.box);
            }
        }
    }

    private void storeInfoLogin() {
        //Store Info Login
        InfoLogin infoLogin = new InfoLogin();
        infoLogin.setUsername(edtUsername.getText().toString());
        edtUsername.setText(null);
        infoLogin.setPassword(edtPassword.getText().toString());
        edtPassword.setText(null);
        if (imgRemember.getTag().equals("False")) {
            infoLogin.setRemember(false);
        } else {
            infoLogin.setRemember(true);
        }

        Storage.getInstance(this).setInfoLogin(infoLogin);
    }

    @Override
    public void loginOk(String session, int sn) {

        storeAutoLogin();

        storeInfoLogin();

        //Store Session
        Storage.getInstance(this).setAuth(session);

        Log.d("SESSION", session);

        //updateStaffToken
        updateStaffToken();

        //Store sn
        hotelSn = sn;

    }

    @Override
    public void loginFail() {
        Toast.makeText(this, getString(R.string.msg_0_incorrect_user_name_or_password_please_try_again), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateStaffTokenOk() {
        viewHotelDetail();
    }

    @Override
    public void updateStaffTokenFail() {

    }

    @Override
    public void viewHotelDetailOk() {
        //getHotelName
        gotoDashboard();
    }

    @Override
    public void viewHotelDetailFail() {
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
                Intent exit = new Intent(Login.this, Quit.class);
                startActivity(exit);
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }
}
