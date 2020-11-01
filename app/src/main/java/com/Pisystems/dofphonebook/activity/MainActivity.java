package com.Pisystems.dofphonebook.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.Pisystems.dofphonebook.R;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private String page_name="";
    public static int  cAreaID=0;
    public static String  userList="";
    public static String  signleUser="";
    public static String  userlist="";
    public static String  userlistD="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        try {
            super.onCreate(savedInstanceState);
        }catch (Exception e){
            Intent intent = new Intent(MainActivity.this,
                    SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            MainActivity.this.finish();

        }

        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);



        if (!isMyServiceRunning(MyService.class)) {
            try {
                Intent i = new Intent(this, MyService.class);
                i.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                //startService(i);
            } catch (Exception e) {

            }
        }

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(SplashActivity.firstTimePopUpFlageDrawer)
        {
            DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerLayout.openDrawer(Gravity.LEFT);
            SplashActivity.firstTimePopUpFlageDrawer = false;
        }


        if(b!=null)
        {
            page_name =(String) b.get("page_name");
        }


        if(b!=null)
        {
            if(b.containsKey("area_id"))
            {
                cAreaID =(Integer) b.get("area_id");
                userList="area_id";
                signleUser="area_id";
                userlist="area_id";
            }
            if(b.containsKey("favourite"))
            {
                //System.out.println("here");
                userList="favourite";
                userlist="favourite";
               // System.out.println(userList);
            }
            if(b.containsKey("user_id"))
            {
                cAreaID =(Integer) b.get("user_id");
                signleUser="user_id";
            }
            if(b.containsKey("noticeID"))
            {
                cAreaID =(Integer) b.get("noticeID");
            }
            if(b.containsKey("deg_user"))
            {
                userlistD = (String) b.get("deg_user");
                userlist="deg_user";
            }

        }
        //JsonController jsonController = new JsonController("userinfo","0000-00-00 00:00:00");
        if(page_name!="")
        {
            Fragment fragment = null;
            switch (page_name) {
                case "home":
                    fragment = new HomeFragment();
                    break;
                case "phonebook":
                    fragment = new phonebookFragment();
                    break;
                case "notice":
                    fragment = new FragmentNotice();
                    break;
                case "help":
                    fragment = new FragmentHelp();
                    break;
                case "userlist":
                    fragment = new FragmentUserList();
                    break;
                case "singleUser":
                    fragment = new FragmentSingleUser();
                    break;
                case "singlenotice":
                    fragment = new FragmentSingleNotice();
                    break;
                case "groupsmsemail":
                    fragment = new FragmentGroupSmsEmail();
                    break;
                case "groupsmsemailareawise":
                    fragment = new FragmentGroupSMSAreaWise();
                    break;
                case "groupsmsemaildesignwise":
                    fragment = new FragmentGroupSmsEmailDesign();
                    break;
                case "groupsmsemailfavlist":
                    fragment = new FragmentGroupSmsEmailFavList();
                    break;
                case "noUserFound":
                    fragment = new NoUserFound();
                    break;
                case "feedback":
                    fragment = new FragmentFeedback();
                    break;
                case "groupsmsnewmessage":
                    fragment = new GroupSmsNewMessage();
                    break;
                default:
                    break;
            }

             String title = "মৎস্য অধিদপ্তর বাংলাদেশ";
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(title);
            }
        }
        else {
            displayView(0);
        }



    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new FragmentUserInformation();
                    break;
                case 2:
                    fragment = new FragmentSettings();
                    break;
                case 3:
                    cAreaID=0;
                    fragment = new FragmentFeedback();
                    break;
                case 4:
                    fragment = new FriendsFragment();
                break;
                default:
                    break;
            }

        String title = "মৎস্য অধিদপ্তর বাংলাদেশ";
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}