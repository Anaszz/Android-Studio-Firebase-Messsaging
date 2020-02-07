package com.example.myshop;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CANAL = "MynotifCanal";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String myMessage = remoteMessage.getNotification().getBody();
        Log.d("FirebaseMessage", "vous venez de recevoir une notification:" + myMessage);


        //action
        //rediriger vers une page web
       /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=L_jWHffIx5E"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

       */
        //rediriger vers une activity
        Intent intent = new Intent(getApplicationContext(), NotifActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //creer une notif

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CANAL);
        notificationBuilder.setContentTitle("Ma super notif !");
        notificationBuilder.setContentText(myMessage);

        //ajouter l'action

        notificationBuilder.setContentIntent(pendingIntent);

        //creer la vibration

        long[] vibrationPattern = {500, 1000};
        notificationBuilder.setVibrate(vibrationPattern);

        // une icone
        notificationBuilder.setSmallIcon(R.drawable.kaaris);

        //envoyer la notif

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelID = getString(R.string.notification_channel_id);
            String channelTitle = getString(R.string.notification_channel_title);
            String channelDescription = getString(R.string.notification_channel_desc);
            NotificationChannel channel = new NotificationChannel(channelID, channelTitle, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelID);
        }
        notificationManager.notify(1, notificationBuilder.build());
    }


}
