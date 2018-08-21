package com.appromobile.partnerapp.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.ui.Dashboard;
import com.appromobile.partnerapp.ui.Splash;
import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;

/**
 * Created by Chau Huynh on 05/03/02017.
 */

public class ReceiverService extends GcmListenerService {
    public static final String RECAll = "Recall";

    public ReceiverService() {
        super();
    }

    //This method will be called on every new message received
    @Override
    public void onMessageReceived(String from, Bundle data) {
        //Getting the message from the bundle
        String message = data.getString("title");

        //String message = data.getString("title");
        //Gson gson = new Gson();

        //Displaying a notiffication with the message
        sendNotification(message);
    }

    //Toast
    private void sendNotification(String message) {
        Intent intent = new Intent(this, Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        //Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, noBuilder.build());
    }
}
