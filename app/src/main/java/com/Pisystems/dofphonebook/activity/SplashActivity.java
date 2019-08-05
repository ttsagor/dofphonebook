package com.Pisystems.dofphonebook.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.Pisystems.dofphonebook.R;

public class SplashActivity extends AppCompatActivity {
    MyDBHandler db = new MyDBHandler(this,null,null,1);
    public static boolean userInfoComplete=false;
    public static boolean areaCodeComplete=false;
    public static boolean designationComplete=false;
    public static boolean areaInfoComplete=false;
    public static ArrayList<User> allUser = new ArrayList<User>();
    public static ArrayList<areaCode> allArea = new ArrayList<areaCode>();
    public static ArrayList<Designation> allDesignation = new ArrayList<Designation>();
    public static boolean firstTimePopUpFlage = false;
    public static boolean firstTimePopUpFlageDrawer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView image = (ImageView)findViewById(R.id.splash1);
        final ImageView image1 = (ImageView)findViewById(R.id.splash);
        // final ImageView image2 = (ImageView)findViewById(R.id.splash3);

        TextView t1 = (TextView) findViewById(R.id.textView);
        TextView t2 = (TextView) findViewById(R.id.textView2);
        TextView t3 = (TextView) findViewById(R.id.textView3);
        TextView t4 = (TextView) findViewById(R.id.textView4);
        TextView t5 = (TextView) findViewById(R.id.textView5);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf = Typeface.createFromAsset(this.getAssets(), fontPath);
        t1.setTypeface(tf);
        t2.setTypeface(tf);
        t3.setTypeface(tf);
        t4.setTypeface(tf);
        t5.setTypeface(tf);


        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.tranlatebt);
        image.startAnimation(animationFadeIn);
        image1.startAnimation(animationFadeIn);
        t1.startAnimation(animationFadeIn);
        t2.startAnimation(animationFadeIn);
        t3.startAnimation(animationFadeIn);
        t4.startAnimation(animationFadeIn);
        t5.startAnimation(animationFadeIn);
        SystemInfo cSystemInfo = db.getSystemInfo();
        final Context mContext = this;

        if(cSystemInfo.getDataLoaded()==1 )
        {

            firstTimePopUpFlage = true;
            Thread splashTread;
            splashTread = new Thread() {
                @Override
                public void run() {
                    try {
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_userInfoUpdating,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areacodeUpadting,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areainfoUpadting,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_designUpdating,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_dataLoaded,2);
                        allDesignation =db.getAllDesignation();
                        allUser = db.getAllUser();
                        allArea =db.getAllArea();
                        int waited = 0;
                        // Splash screen pause time
                        while (waited < 2000) {
                            sleep(100);
                            waited += 100;
                        }
                        Intent intent = new Intent(SplashActivity.this,
                                MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    } catch (InterruptedException e) {
                        // do nothing
                    } finally {
                        SplashActivity.this.finish();
                    }

                }
            };
            splashTread.start();
        }
        else if(cSystemInfo.getDataLoaded()==2 )
        {
            firstTimePopUpFlage = true;
            Thread splashTread;
            splashTread = new Thread() {
                @Override
                public void run() {
                    try {
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_userInfoUpdating,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areacodeUpadting,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areainfoUpadting,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_designUpdating,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_dataLoaded,3);
                        allDesignation =db.getAllDesignation();
                        allUser = db.getAllUser();
                        allArea =db.getAllArea();
                        int waited = 0;
                        // Splash screen pause time
                        while (waited < 2000) {
                            sleep(100);
                            waited += 100;
                        }
                        Intent intent = new Intent(SplashActivity.this,
                                MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    } catch (InterruptedException e) {
                        // do nothing
                    } finally {
                        SplashActivity.this.finish();
                    }

                }
            };
            splashTread.start();
        }

        else if(cSystemInfo.getDataLoaded()==3 )
        {
            //firstTimePopUpFlage = true;
            Thread splashTread;
            splashTread = new Thread() {
                @Override
                public void run() {
                    try {
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_userInfoUpdating,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areacodeUpadting,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areainfoUpadting,0);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_designUpdating,0);
                        //db.updateSystemInfoUpdating(MyDBHandler.COLUMN_dataLoaded,4);
                        allDesignation =db.getAllDesignation();
                        allUser = db.getAllUser();
                        allArea =db.getAllArea();
                        int waited = 0;
                        // Splash screen pause time
                        while (waited < 2000) {
                            sleep(100);
                            waited += 100;
                        }
                        Intent intent = new Intent(SplashActivity.this,
                                MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    } catch (InterruptedException e) {
                        // do nothing
                    } finally {
                        SplashActivity.this.finish();
                    }

                }
            };
            splashTread.start();
        }
        else {
            Thread splashTread;
            splashTread = new Thread() {
                @Override
                public void run() {

                        ArrayList<String> areas = loadAssetTextAsString(mContext, "area_code.csv");
                        ArrayList<String> users = loadAssetTextAsString(mContext, "user_info.csv");
                        ArrayList<String> area_info = loadAssetTextAsString(mContext, "area_info.csv");
                        ArrayList<String> designations = loadAssetTextAsString(mContext, "designation.csv");

                        for (String area : areas) {
                            String[] separated = area.split(";");
                            areaCode cArea = new areaCode();
                            cArea.setAreaID(Integer.parseInt(separated[0]));
                            cArea.setParentID(Integer.parseInt(separated[1]));
                            cArea.setAreaName(separated[3]);
                            cArea.setActive_stat(Integer.parseInt(separated[4]));
                            cArea.setLast_update(separated[5]);
                            cArea.setAreaNameEng(separated[2]);
                            try {
                                db.insertArea(cArea);
                            } catch (Exception e) {
                                System.out.println("ERROR IN:area" + e.toString());
                            }
                        }

                        System.out.println("area_info....");
                        for (String area : area_info) {
                            String[] separated = area.split(";");
                            area_info cArea = new area_info();
                            cArea.setArea_id(Integer.parseInt(separated[0]));
                            cArea.setArea_details(separated[1]);
                            cArea.setArea_location(separated[2]);
                            cArea.setActive_stat(Integer.parseInt(separated[3]));
                            cArea.setLast_update(separated[4]);
                            cArea.setAddress(separated[5]);


                            try {
                                db.insertAreaInfo(cArea);
                            } catch (Exception e) {
                                System.out.println("ERROR IN:" + e.toString());
                            }
                        }

                        System.out.println("users....");
                        for (String user : users) {
                            String[] separated = user.split(";");
                            User cUser = new User();
                            cUser.setUser_id(Integer.parseInt(separated[0]));
                            cUser.setUser_name(separated[2]);
                            cUser.setDesignation(separated[3]);
                            cUser.setArea_id(Integer.parseInt(separated[4]));
                            cUser.setPhone1(separated[5]);
                            cUser.setPhone2(separated[6]);
                            cUser.setOffice_num(separated[7]);
                            cUser.setEmail(separated[8]);
                            cUser.setActive_stat(Integer.parseInt(separated[9]));
                            cUser.setLast_update(separated[10]);
                            cUser.setUser_name_eng(separated[1]);
                            //System.out.println(cUser.getUser_id());
                            try {
                                db.insertUser(cUser);

                            } catch (Exception e) {
                                Log.e("ERROR", e.toString());
                            }
                        }

                        System.out.println("designations....");
                        for (String designation : designations) {

                            String[] separated = designation.split(";");
                            Designation desOBJ = new Designation();
                            desOBJ.setDesign_id(Integer.parseInt(separated[0]));
                            desOBJ.setDesign_en(separated[1]);
                            desOBJ.setDesign_bn(separated[2]);
                            desOBJ.setDesign_rank(separated[3]);
                            desOBJ.setActive_stats(Integer.parseInt(separated[4]));
                            desOBJ.setLast_update(separated[5]);

                            try {
                                db.insertDesignation(desOBJ);

                            } catch (Exception e) {
                                System.out.println("ERROR IN:" + e.toString());
                            }
                            // System.out.println(separated[0]+"/"+separated[1]+"/"+separated[3]+"/"+separated[4]+"/"+separated[5]+"/");
                        }
                        if(isNetworkAvailable()) {
                            areaInfoComplete = true;
                            areaCodeComplete= true;
                            designationComplete = true;
                            userInfoComplete = true;

                            Intent i = new Intent(SplashActivity.this, UpdateData.class);
                            startService(i);
                        }
                    allArea =db.getAllArea();
                    allDesignation =db.getAllDesignation();
                    allUser = db.getAllUser();
                    Intent intent = new Intent(SplashActivity.this,
                            PhoneNumberValidation.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            };
            splashTread.start();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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

    private ArrayList<String> loadAssetTextAsString(Context context, String name) {
        BufferedReader in = null;
        ArrayList<String> rows = new ArrayList<String>();
        try {
            StringBuilder buf = new StringBuilder();

            InputStream is = context.getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ( (str = in.readLine()) != null ) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                rows.add(str);
                buf.append(str);
            }
            return rows;
        } catch (IOException e) {
            // Log.e(TAG, "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    //Log.e(TAG, "Error closing asset " + name);
                }
            }
        }

        return null;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }
}
