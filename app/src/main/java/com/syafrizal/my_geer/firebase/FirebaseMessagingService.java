package com.syafrizal.my_geer.firebase;

import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.syafrizal.my_geer.Model.Notification;
import com.syafrizal.my_geer.config.Constants;

import java.util.List;

import io.paperdb.Paper;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{
    public static String TAG = FirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        Paper.init(this);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        List<Notification> notifications = Paper.book().read(Constants.PaperDB.NOTIFICATIONS);
        notifications.add(new Notification(
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(),
                remoteMessage.getData().get(Constants.Firebase.DATA_IMAGE)
        ));
        Paper.book().write(Constants.PaperDB.NOTIFICATIONS, notifications);

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }
}
