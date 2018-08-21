package com.appromobile.partnerapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appromobile.partnerapp.R;

public class Quit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit);

        if (Splash.isRunning != null) {
            Splash.isRunning.finish();
        } else if (Login.isRunning != null) {
            Login.isRunning.finish();
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

        finish();
    }
}
