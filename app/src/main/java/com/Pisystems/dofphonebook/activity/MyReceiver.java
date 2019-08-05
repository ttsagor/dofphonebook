package com.Pisystems.dofphonebook.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent myIntent = new Intent(context, com.Pisystems.dofphonebook.activity.MyService.class);
            context.startService(myIntent);
        }
        //Toast.makeText(context.getApplicationContext(), "অনুগ্রহক পূর্বক কোন  এলাকা/এলাকাসমূহ সিলেক্ট করুন", Toast.LENGTH_LONG).show();
    }

}
