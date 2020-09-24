package com.example.thepranami.notebook.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.thepranami.notebook.R;

public class ServiceOnSuccess extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service Started...", Toast.LENGTH_SHORT).show();
        notifyMe();
    }


    public void notifyMe(){
//        NotificationManager notificationManager = (NotificationManager)getSystemService(Service.NOTIFICATION_SERVICE);
//        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(getApplicationContext())
//                .setSmallIcon(R.drawable.ic_add_data)
//                .setContentTitle("MyData")
//                .setContentText("One new data inserted")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        Intent notiIntent = new Intent(getApplicationContext(), ServiceOnSuccess.class);
//
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
//                notiIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        notiBuilder.setContentIntent(pendingIntent);
//        notificationManager.notify(0, notiBuilder.build());
    }
}
