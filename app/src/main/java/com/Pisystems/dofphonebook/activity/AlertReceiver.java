package com.Pisystems.dofphonebook.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.Pisystems.dofphonebook.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AlertReceiver extends BroadcastReceiver {
    public AlertReceiver() {
    }
    Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        mContext=context;
        final MyDBHandler db = new MyDBHandler(context,null,null,1);
        SystemInfo systemInfo = db.getSystemInfo();

        if(isNetworkAvailable())
        {
            final JSON asyncTask =new JSON();
            asyncTask.noticeService=true;
            asyncTask.db=db;
            asyncTask.mContex=context;
            // System.out.println(systemInfo.getNoticeLastUpdate());
            String date="";
            try {
                date= URLEncoder.encode(systemInfo.getNoticeLastUpdate(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            asyncTask.execute("http://service.digital-phonebook.com/noticeService.php" + "?date="+date);


        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }
    public void createNotification(Context context,String msg,String msgText,String msgAlert)
    {
        PendingIntent notifiIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        try {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.dof)
                    .setContentTitle(msg)
                    .setTicker(msgAlert)
                    .setContentText(msgText);
            mBuilder.setContentIntent(notifiIntent);
            mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
            mBuilder.setAutoCancel(true);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1,mBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
