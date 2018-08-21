package com.appromobile.partnerapp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.Space;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.communicator.Communicator;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;
import com.appromobile.partnerapp.presenter.Presenter.PresenterSplash;
import com.appromobile.partnerapp.presenter.view.ViewSplash;
import com.appromobile.partnerapp.push.RegistrationIntentService;
import com.appromobile.partnerapp.storage.Storage;
import com.appromobile.partnerapp.utils.Helper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class Splash extends AppCompatActivity implements ViewSplash {
    public static Splash isRunning;
    public static String TAG = "Splash: ";
    private BroadcastReceiver registrationIntentService;
    private PresenterSplash presenterSplash = new PresenterSplash(this);
    private String tokenGCM = "";
    private int hotelSn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        isRunning = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Check Communicator
        checkCommunicator();
        //Register
        LocalBroadcastManager.getInstance(this).registerReceiver(registrationIntentService,
                new IntentFilter(RegistrationIntentService.REGISTRATION_SUCCESS));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unRegisterReceiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(registrationIntentService);
    }

    private void checkCommunicator() {
        //Check WiFi On Off
        if (Communicator.getInstance().isWifi(this)) {
            //Check Internet
            if (Communicator.getInstance().isConnect()) {

                //Check Succeccfull
                getTokenGCM();

            } else {
                //Show Alert
                Communicator.getInstance().Alert(Splash.this, getString(R.string.app_name), getString(R.string.app_need_to_connect_internet_please_turn_on_the_internet), getString(R.string.txt_1_2_setting), "");
            }
        } else {
            //Show Alert
            Communicator.getInstance().Alert(Splash.this, getString(R.string.app_name), getString(R.string.app_need_to_connect_internet_please_turn_on_the_internet), getString(R.string.txt_1_2_setting), getString(R.string.txt_1_2_setting));

        }
    }

    private void checkVersion() {
        presenterSplash.findApiSetting(this);
    }

    private void getTokenGCM() {
        registrationIntentService = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //If the broadcast has received with success
                //that means device is registered successfully
                if (intent.getAction().equals(RegistrationIntentService.REGISTRATION_SUCCESS)) {
                    //Getting the registration token from the intent
                    tokenGCM = intent.getStringExtra("token");

                    Log.d(TAG, "Token_GCM ---> " + tokenGCM);

                    presenterSplash.checkAutoLogin(Splash.this);


                } else {
                    //if the intent is not with success then displaying error messages
                    Log.d(TAG, "Token_GCM --->   Fail");

                    presenterSplash.checkAutoLogin(Splash.this);
                }
            }
        };

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        //if play service is not available
        if (ConnectionResult.SUCCESS != resultCode) {
            Log.d(TAG, "GCM not available");
            //Google Play Service not support
            //Continue with no token Push.
            Dialag.getInstance().show(this, false,false, getString(R.string.no_push), getString(R.string.quit), getString(R.string.txt_10_yes), new CallbackDialag() {
                @Override
                public void ok() {
                    presenterSplash.checkAutoLogin(Splash.this);
                }

                @Override
                public void cancel() {
                    //Quit
                    Intent exit = new Intent(Splash.this, Quit.class);
                    startActivity(exit);
                    finish();
                }
            });

        } else {
            //Starting intent to register device
            Log.d(TAG, "GCM Register");
            Intent itent = new Intent(Splash.this, RegistrationIntentService.class);
            startService(itent);
        }
    }

    private void gotoLogin() {
        Intent login = new Intent(this, Login.class);
        login.putExtra("TOKENGCM", tokenGCM);
        startActivity(login);
        finish();
    }

    private void updateStaffToken() {
        StaffDeviceInfoDto staffDeviceInfoDto = new StaffDeviceInfoDto();
        staffDeviceInfoDto.setMobileUserId(Helper.getDeviceId(this));
        staffDeviceInfoDto.setLanguage(Helper.getLanguages());
        staffDeviceInfoDto.setOs(Helper.getOz());
        staffDeviceInfoDto.setPartnerVersion(Helper.getPartnerVerzion());
        staffDeviceInfoDto.setOsVersion(Helper.getOsVersion());
        staffDeviceInfoDto.setTokenId(tokenGCM);

        presenterSplash.updateStaffToken(this, staffDeviceInfoDto);
    }

    //Get Name Hotel
    private void viewHotelDetail() {
        presenterSplash.viewHotelDetail(this, hotelSn);
    }

    private void gotoDashboard() {
        //getHotelName
        Intent dashboard = new Intent(this, Dashboard.class);
        startActivity(dashboard);
        finish();
    }

    @Override
    public void autoLoginTrue() {
        //Call Login
        presenterSplash.loginViaMobile(this);
    }

    @Override
    public void autoLoginFalse() {
        //Intent Login + tokenGCM
        gotoLogin();
    }

    @Override
    public void loginFail() {
        //Auto Login Fail
        gotoLogin();
    }

    @Override
    public void loginOk(String session, int sn) {
        //Store Session
        Storage.getInstance(this).setAuth(session);

        Log.d("SESSION", session);

        //Store hotelSn
        hotelSn = sn;

        //updateStaffToken
        updateStaffToken();
    }

    @Override
    public void updateStaffTokenOk() {
        //get Name Hotel
        viewHotelDetail();
    }

    @Override
    public void updateStaffTokenFail() {

    }

    @Override
    public void viewHotelDetailOk() {
        //CheckVersion
        checkVersion();
    }

    @Override
    public void viewHotelDetailFail() {
    }

    @Override
    public void checkVersionContinue() {

        //Go to Dashboard
        gotoDashboard();

    }

    @Override
    public void checkVersionUpdate() {
        Dialag.getInstance().show(this, false,false, getString(R.string.update), getString(R.string.txt_3_1_no), getString(R.string.txt_10_yes), new CallbackDialag() {
            @Override
            public void ok() {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }

            @Override
            public void cancel() {

            }
        });
    }

    @Override
    public void checkVersionForceUpdate() {
        Dialag.getInstance().show(this, false,false, getString(R.string.update), null, getString(R.string.txt_10_yes), new CallbackDialag() {
            @Override
            public void ok() {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }

            @Override
            public void cancel() {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        TAG = null;
        isRunning = null;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Dialag.getInstance().show(this, false,false, getString(R.string.txt_quit), getString(R.string.txt_3_1_no), getString(R.string.txt_10_yes), new CallbackDialag() {
            @Override
            public void ok() {
                //Quit
                Intent exit = new Intent(Splash.this, Quit.class);
                startActivity(exit);
                finish();
            }

            @Override
            public void cancel() {

            }
        });
    }

}
