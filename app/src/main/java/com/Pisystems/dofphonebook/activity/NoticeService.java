package com.Pisystems.dofphonebook.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.provider.SyncStateContract;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.Pisystems.dofphonebook.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static android.widget.Toast.*;

public class NoticeService extends Service {
    public NoticeService() {
       // startInforeground();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
       // throw new UnsupportedOperationException("Not yet implemented");
    }
Notification foregroundNotification;
    final int notificationId=1;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"started",Toast.LENGTH_LONG).show();
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
           // Log.i(LOG_TAG, "Received Start Foreground Intent ");
           // showNotification();
            Toast.makeText(this, "Service Started!", LENGTH_SHORT).show();


        final MyDBHandler db = new MyDBHandler(getBaseContext(),null,null,1);
        SystemInfo systemInfo = db.getSystemInfo();

        //if(isNetworkAvailable())
        {
            final JSON asyncTask =new JSON();
            asyncTask.noticeService=true;
            asyncTask.db=db;
            asyncTask.mContex=getApplicationContext();
            System.out.println(systemInfo.getNoticeLastUpdate());
            String date="";
            try {
                date= URLEncoder.encode(systemInfo.getNoticeLastUpdate(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            asyncTask.execute("http://digital-phonebook.com/service/get_notice_list" + "?last_update="+date);


        }
            /*JsonController jsonController = new JsonController("notice",systemInfo.getNoticeLastUpdate(),db);
            // while (true) {

            jsonController.runService("notice", systemInfo.getNoticeLastUpdate(), db);

            //System.out.println("JSON RESULT: " );
            while(jsonController.noticeRunning)
            {}
            if(jsonController.result.length()>0)
            {
                //createNotification(mContext, "Time's up ", "5 SEc ", "Alert");
                   oast.show();
                // }
                //break;
            }

        }

        PendingIntent notifiIntent = PendingIntent.getActivity(getBaseContext(), 0,
                new Intent(getBaseContext(), MainActivity.class), 0);

        try {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getBaseContext())
                    .setSmallIcon(R.drawable.dof)
                    .setContentTitle("Title")
                    .setTicker("alert")
                    .setContentText("text");
            mBuilder.setContentIntent(notifiIntent);
            mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
            mBuilder.setAutoCancel(true);

            NotificationManager mNotificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1,mBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }*/}
        return START_STICKY;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
