package com.Pisystems.dofphonebook.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import com.Pisystems.dofphonebook.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


public class MyService extends Service {

    protected Integer NOTIFICATION_ID = 23213123; // Some random integer

    private LoadNotification loadNotification;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final MyDBHandler db = new MyDBHandler(getBaseContext(),null,null,1);
        SystemInfo cSystemInfo = db.getSystemInfo();


        List<User> users = db.getAreaUserByID(cSystemInfo.getUserID());
        String userName="";
        String UserDesignation="";
        for (User cUser : users) {
            userName=cUser.getUser_name();
            UserDesignation=cUser.getDesignation();
        }
        loadNotification = new LoadNotification(userName, UserDesignation);
        loadNotification.notifyMessage();

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
        return START_STICKY;
    }

    class LoadNotification {

        private String titleMessage;
        private String textMessage;


        public LoadNotification(String titleMessage, String textMessage) {
            this.titleMessage = titleMessage;
            this.textMessage = textMessage;
        }

        public void notifyMessage() {
            NotificationCompat.Builder builder = getNotificationBuilder(SplashActivity.class);
            startForeground(NOTIFICATION_ID, builder.build());

        }

        protected NotificationCompat.Builder getNotificationBuilder(Class clazz) {
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

            builder.setSmallIcon(R.drawable.dof);  // icon id of the image

            builder.setContentTitle(this.titleMessage)
                    .setContentText(this.textMessage)
                    .setContentInfo("Department of Fisheries");

            Intent foregroundIntent = new Intent(getApplicationContext(), clazz);

            foregroundIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, foregroundIntent, 0);

            builder.setContentIntent(contentIntent);
            return builder;
        }

    }
}