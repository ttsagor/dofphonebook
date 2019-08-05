package com.Pisystems.dofphonebook.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Pisystems.dofphonebook.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneNumberValidation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_validation);
        final MyDBHandler db = new MyDBHandler(this,null,null,1);
        final EditText userphonenumber = (EditText) findViewById(R.id.userphonenumber);
        final Button phonenumberconfirm = (Button) findViewById(R.id.phonenumberconfirm);
        final Context mContext = this;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

        userphonenumber.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        phonenumberconfirm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(getCurrentFocus()!=null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                if (userphonenumber.getText().toString().length() > 0) {
                    User cUser1 = new User();
                    User cUser2= new User();
                    try {
                        cUser1 = db.getUserByPhone1(userphonenumber.getText().toString());
                        cUser2 = db.getUserByPhone2(userphonenumber.getText().toString());
                    }
                    catch(Exception e){}
                    if (cUser1.getPhone1() == null && cUser2.getPhone2() == null) {
                        if (isNetworkAvailable()) {

                        }
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext);
                        dlgAlert.setMessage("নম্বরটি অনুমোদিত নয় অনুগ্রহপূর্বক যথাযথ কর্তৃপক্ষের সাথে যোগাযোগ করুন ");
                        dlgAlert.setTitle("");
                        dlgAlert.setPositiveButton("বন্ধ করুন", null);
                        dlgAlert.setCancelable(false);

                        dlgAlert.setPositiveButton("বন্ধ করুন", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        });
                        dlgAlert.create().show();
                    } else {


                        String userPhoneNumber = "";
                        int userID = 0;
                        if (cUser1.getPhone1() != null) {
                            userID = cUser1.getUser_id();
                            userPhoneNumber = cUser1.getPhone1();
                        } else if (cUser2.getPhone2() != null) {
                            userID = cUser2.getUser_id();
                            userPhoneNumber = cUser2.getPhone2();
                        }
                        final int FuserID = userID;
                        final String key = rendomNumberGenerator("abcdefghijklmnopqrstuvwxyz123456789");
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(userPhoneNumber, null, "Welcome to Digital Phonebook.Your access key is : " + key, null, null);
                        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext);

                        dlgAlert.setMessage("মোবাইল নাম্বার যাচাই প্রক্রিয়া চলছে \n অনুগ্রহকপূর্বক অপেক্ষা করুন");
                        //dlgAlert.setPositiveButton("বন্ধ", null);
                        dlgAlert.setCancelable(false);

                        /*dlgAlert.setPositiveButton("বন্ধ", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        });*/
                        final AlertDialog alert = dlgAlert.create();
                        alert.show();
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    while (true) {
                                        sleep(1000);
                                        Boolean completeFlag = false;
                                        //System.out.println("In thread ");
                                        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

                                        if (cursor.moveToFirst()) { // must check the result to prevent exception
                                            do {
                                                String msgData = "";
                                                msgData = cursor.getString(cursor.getColumnIndex("body"));
                                                String inboxKey = "";
                                                if (msgData.contains("Welcome to Digital Phonebook.Your access key is : ")) {
                                                    inboxKey = msgData.substring(msgData.length() - 20);
                                                    //System.out.println(inboxKey);
                                                    if (inboxKey.equals(key)) {

                                                        completeFlag = true;

                                                        SystemInfo nSystemInfo = new SystemInfo();
                                                        nSystemInfo.setDataLoaded(1);
                                                        nSystemInfo.setUserID(FuserID);
                                                        nSystemInfo.setUserLastUpdate("2010-04-01 23:00:00");
                                                        nSystemInfo.setAreaCodeLastUpdate("2010-04-01 23:00:00");
                                                        nSystemInfo.setAreaInfoLastUpdate("2010-04-01 23:00:00");
                                                        nSystemInfo.setNoticeLastUpdate("2010-01-01 23:00:00");
                                                        nSystemInfo.setLastUpdate("2010-04-01 23:00:00");
                                                        nSystemInfo.setDesignationLastUpdate("2010-04-01 23:00:00");
                                                        nSystemInfo.setUserinfoUpdating(0);
                                                        nSystemInfo.setAreacodeUpdating(0);
                                                        nSystemInfo.setAreainfoUpdating(0);
                                                        nSystemInfo.setDesignUpdating(0);
                                                        SplashActivity.firstTimePopUpFlage = true;
                                                        SplashActivity.firstTimePopUpFlageDrawer = true;
                                                        //System.out.println(nSystemInfo.getDesignationLastUpdate());
                                                        db.insertSystem(nSystemInfo);
                                                        //Toast.makeText(mContext, "মোবাইল নাম্বার যাচাই সম্পূর্ণ", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            } while (cursor.moveToNext());
                                        } else {
                                            // empty box, no SMS
                                        }
                                        if (completeFlag){
                                            Intent intent = new Intent(PhoneNumberValidation.this,
                                                    MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(intent);
                                            alert.dismiss();
                                            PhoneNumberValidation.this.finish();
                                            break;}
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        thread.start();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phone_number_validation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private String rendomNumberGenerator(String charSet)
    {
        char[] chars = charSet.toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }
}
