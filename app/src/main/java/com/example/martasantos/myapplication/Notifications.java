package com.example.martasantos.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.martasantos.myapplication.evento.CriarEvento;

/**
 * Created by martasantos on 19/01/18.
 */

public class Notifications {

    private static final int NOTIFICATIN_ID_OPEN_ACTIVITY=9;

    public static void openActivityNotification(Context context){
        NotificationCompat.Builder notif=new NotificationCompat.Builder(context);
        NotificationManager nm=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notifyIntent= new Intent(context, CriarEvento.class);

        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent= PendingIntent.getActivity(context,0,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        notif.setContentIntent(pendingIntent);

        notif.setAutoCancel(true);
        notif.setContentTitle("Evento Criado");
        notif.setContentText("Click Please");

        nm.notify(NOTIFICATIN_ID_OPEN_ACTIVITY,notif.build());
    }
}
