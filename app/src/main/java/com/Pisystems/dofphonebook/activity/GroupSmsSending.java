package com.Pisystems.dofphonebook.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.Pisystems.dofphonebook.R;
public class GroupSmsSending extends AppCompatActivity {

    String messageInt="";
    List<String> MobNumber = new ArrayList<String>();
    TextView smsSendingtotalRecipnt;
    TextView smsSendingtotalSend;
    TextView smsSendingtotalPending;
    //TextView smsSendingtotalSendSucessful;
    TextView smsSendingtotalfailed;
    TextView textView2;
    ProgressBar progressBar1;
    LinearLayout processBarHolderLayout;
    Button smssendinggohome;
    List<String> sentMobNumber = new ArrayList<String>();
    List<String> failMobNumber = new ArrayList<String>();

    BroadcastReceiver sendBroadcastReceiver = new SentReceiver();
    BroadcastReceiver deliveryBroadcastReciever = new DeliverReceiver();;
    String sms_sent="sms sent";
    String sms_delivered="sms_delivered";
    String sms_not_delivered="sms_not_delivered";

    int smsSendingtotalSendCount=0;
    int smsSendingtotalPendingCount=0;
    int smsSendingtotalSendSucessfulCount=0;
    int smsSendingtotalfailedCount=0;
    long currentTime;
    boolean smsSendFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
       // ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
        setContentView(R.layout.activity_group_sms_sending);

        smsSendingtotalSend = (TextView)findViewById(R.id.smsSendingtotalSend);
        smsSendingtotalPending = (TextView)findViewById(R.id.smsSendingtotalPending);
        //smsSendingtotalSendSucessful = (TextView)findViewById(R.id.smsSendingtotalSendSucessful);
        smsSendingtotalRecipnt = (TextView)findViewById(R.id.smsSendingtotalRecipnt);
        smsSendingtotalfailed = (TextView)findViewById(R.id.smsSendingtotalfailed);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        processBarHolderLayout = (LinearLayout) findViewById(R.id.processBarHolderLayout);
        textView2 = (TextView)findViewById(R.id.textView2);
        smssendinggohome = (Button) findViewById(R.id.smssendinggohome);
        smssendinggohome.setVisibility(View.INVISIBLE);
        smssendinggohome.setClickable(false);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            messageInt =(String) b.get("message");
        }

        MobNumber = new ArrayList<String>();
        for(int i=0;i<FragmentGroupSmsEmail.finalRecipient.size();i++)
        {
            if(FragmentGroupSmsEmail.finalRecipient.get(i).length()==11)
            {
                MobNumber.add(FragmentGroupSmsEmail.finalRecipient.get(i));
            }
        }

        smssendinggohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupSmsSending.this,
                        MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        smsSendingtotalRecipnt.setText(smsSendingtotalRecipnt.getText() + String.valueOf(MobNumber.size()));
        smsSendingtotalPendingCount = MobNumber.size();
        smsSendingtotalPending.setText(": "+String.valueOf(smsSendingtotalPendingCount));
        checkIfDone();

        currentTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<MobNumber.size();i++)
                {

                    while (smsSendFlag)
                    {}
                    smsSendFlag = true;
                    try {
                        if (messageInt.length() >= 160) {
                            try {
                                sendMultiPartSmS(MobNumber.get(i), messageInt);
                            }catch (Exception e){}
                        } else {
                            try {
                                sendMultiPartSmS(MobNumber.get(i), messageInt);
                            }catch (Exception e){}
                        }
                    }catch (Exception e){Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_SHORT).show();}
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    searchInbox();
                }catch (Exception e){}
            }
        }).start();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GroupSmsSending.this,
                MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(GroupSmsSending.this,
                    MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    private void sendMultiPartSmS(String phoneNumber, String message) {
        Context ctx = this;
        SmsManager sm = SmsManager.getDefault();
        final String SMS_SEND_ACTION = "CTS_SMS_SEND_ACTION";
        final String SMS_DELIVERY_ACTION = "CTS_SMS_DELIVERY_ACTION";

        IntentFilter sendIntentFilter = new IntentFilter(SMS_SEND_ACTION);
        IntentFilter receiveIntentFilter = new IntentFilter(SMS_DELIVERY_ACTION);

        PendingIntent sentPI = PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_SEND_ACTION), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_DELIVERY_ACTION), 0);

        //final String phone_no = ph_no;

        ArrayList<String> parts = sm.divideMessage(message);

        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();

        for (int i = 0; i < parts.size(); i++) {
            sentIntents.add(PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_SEND_ACTION), 0));
            deliveryIntents.add(PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_DELIVERY_ACTION), 0));
        }

        sm.sendMultipartTextMessage(phoneNumber, null, parts, sentIntents, deliveryIntents);
        //System.out.println("sendMultipartTextMessage :" + phoneNumber);

    }

    class DeliverReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent arg1) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    smsSendingtotalSendSucessfulCount++;
                    //smsSendingtotalSendSucessful.setText(": "+smsSendingtotalSendSucessfulCount);
                    //checkIfDone();
                    break;
                case Activity.RESULT_CANCELED:
                    smsSendingtotalfailedCount++;
                    smsSendingtotalfailed.setText(": "+smsSendingtotalfailedCount);
                    //checkIfDone();
                    break;
            }

        }
    }

    class SentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent arg1) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    smsSendingtotalPendingCount--;
                    smsSendingtotalPending.setText(": "+smsSendingtotalPendingCount);
                    smsSendingtotalSendCount++;
                    smsSendingtotalSend.setText(": "+smsSendingtotalSendCount);
                    checkIfDone();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    smsSendingtotalPendingCount--;
                    smsSendingtotalPending.setText(": "+smsSendingtotalPendingCount);
                    smsSendingtotalfailedCount++;
                    smsSendingtotalfailed.setText(": "+smsSendingtotalfailedCount);
                    checkIfDone();
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    smsSendingtotalPendingCount--;
                    smsSendingtotalPending.setText(": "+smsSendingtotalPendingCount);
                    smsSendingtotalfailedCount++;
                    smsSendingtotalfailed.setText(": "+smsSendingtotalfailedCount);
                    checkIfDone();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    smsSendingtotalPendingCount--;
                    smsSendingtotalPending.setText(": "+smsSendingtotalPendingCount);
                    smsSendingtotalfailedCount++;
                    smsSendingtotalfailed.setText(": "+smsSendingtotalfailedCount);
                    checkIfDone();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    smsSendingtotalPendingCount--;
                    smsSendingtotalPending.setText(": "+smsSendingtotalPendingCount);
                    smsSendingtotalfailedCount++;
                    smsSendingtotalfailed.setText(": "+smsSendingtotalfailedCount);
                    checkIfDone();
                    break;

            }

        }
    }
    private void searchInbox()
    {

        try {
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String msgDataNumber = cursor.getString(cursor.getColumnIndex("address"));
                    long msgDatadate = Long.parseLong(cursor.getString(cursor.getColumnIndex("date")));
                    if (!sentMobNumber.contains(msgDataNumber) && currentTime <= msgDatadate && MobNumber.contains(msgDataNumber)) {
                        sentMobNumber.add(msgDataNumber);
                        smsSendingtotalPendingCount--;
                        smsSendingtotalSendCount++;

                        GroupSmsSending.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                smsSendingtotalPending.setText(": " + smsSendingtotalPendingCount);
                                smsSendingtotalSend.setText(": " + smsSendingtotalSendCount);
                            }//public void run() {
                        });
                        System.out.println("sent number:" + msgDataNumber  + " time: " + msgDatadate);
                        checkIfDone();
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){}

        try {
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/failed"), null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String msgDataNumber = cursor.getString(cursor.getColumnIndex("address"));
                    long msgDatadate = Long.parseLong(cursor.getString(cursor.getColumnIndex("date")));
                    if (!failMobNumber.contains(msgDataNumber) && currentTime <= msgDatadate && MobNumber.contains(msgDataNumber)) {
                        failMobNumber.add(msgDataNumber);
                        GroupSmsSending.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                smsSendingtotalPending.setText(": " + smsSendingtotalPendingCount);
                                smsSendingtotalfailed.setText(": " + smsSendingtotalfailedCount);
                            }//public void run() {
                        });
                        System.out.println("failed number:" + msgDataNumber  + " time: " + msgDatadate);
                        smsSendingtotalPendingCount--;
                        smsSendingtotalfailedCount++;
                        checkIfDone();
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){}
        try {
            Thread.sleep(2000);
        }catch (Exception e){}
        searchInbox();
    }

    private void checkIfDone()
    {
       /* smsSendFlag = false;
        if(smsSendingtotalPendingCount<=0)
        //if((smsSendingtotalSendCount)>=MobNumber.size() || smsSendingtotalfailedCount>=MobNumber.size())
        {
           try {
               progressBar1.setVisibility(View.GONE);
           }
           catch (Exception e){}
           try {
               ImageView imageView = new ImageView(this);
               imageView.setImageResource(R.drawable.right_sign);
               imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                       LinearLayout.LayoutParams.WRAP_CONTENT));
               processBarHolderLayout.addView(imageView);
           }
           catch (Exception e){}
           finally {
               textView2.setText("ক্ষুদেবার্তা প্রেরন সম্পূর্ণ \n" +
                       "ডেলিভারি সফল/ব্যর্থ তথ্যের জন্য অপেক্ষা করুন (Optional)  ");
               smssendinggohome.setVisibility(View.VISIBLE);
               smssendinggohome.setClickable(true);
           }
        }*/

        smsSendFlag = false;
        if(smsSendingtotalPendingCount<=0)
        {
            GroupSmsSending.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar1.setVisibility(View.GONE);
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setImageResource(R.drawable.right_sign);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    processBarHolderLayout.addView(imageView);
                    textView2.setText("ক্ষুদেবার্তা প্রেরন সম্পূর্ণ");
                    smssendinggohome.setVisibility(View.VISIBLE);
                    smssendinggohome.setClickable(true);
                }//public void run() {
            });

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }


}
