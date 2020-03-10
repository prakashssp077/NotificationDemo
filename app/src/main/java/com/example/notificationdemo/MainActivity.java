package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btNotification = findViewById(R.id.notify);

        btNotification.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String message = "This is a notification example";
                Drawable drawable = ContextCompat.getDrawable(MainActivity.this,R.drawable.amazon); //small icon
                Bitmap bitmap =((BitmapDrawable)drawable).getBitmap();

                //inbox style messages
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                inboxStyle.addLine("message 1");
                inboxStyle.addLine("message 2");
                inboxStyle.addLine("message 3");
                inboxStyle.addLine("message 4");
                inboxStyle.setSummaryText("+2 more");

                //assign bigtext notification
                NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
                bigTextStyle.bigText("Welcome to tutlane, it provides a tutorials related to" +
                        " all technologies in software industry. Here we covered " +
                        "complete tutorials from basic to adavanced topics from " +
                        "all technologies");
                bigTextStyle.setSummaryText("By prakash");

                //this code is set a picture to a image
                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
                bigPictureStyle.bigPicture(bitmap);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this )
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Notification")
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        //.setStyle(inboxStyle)
                        .setStyle(bigPictureStyle)
                       // .setStyle(bigTextStyle)
                        .setLargeIcon(bitmap);

                Intent intent =new Intent(MainActivity.this , NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0 ,intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                //set notification sound
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(uri);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());
            }
        });
    }
}
