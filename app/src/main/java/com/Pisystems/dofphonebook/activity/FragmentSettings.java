package com.Pisystems.dofphonebook.activity;

        import android.app.Activity;
        import android.app.ActivityManager;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.ColorStateList;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v4.app.Fragment;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.Pisystems.dofphonebook.R;

        import java.io.ByteArrayOutputStream;
        import java.io.UnsupportedEncodingException;
        import java.net.URLEncoder;
        import java.util.ArrayList;
        import java.util.List;


public class FragmentSettings extends Fragment {

    public FragmentSettings() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    }
    MyDBHandler db ;
    ProgressBar updateingGif;
    public static Boolean updatingDataFlag=false;
    Button settingsUpdate;
    LinearLayout settinglayouthodler;
    TextView settingupdatetext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragmentsetting, container, false);
        settingsUpdate = (Button) rootView.findViewById(R.id.settingsUpdate);
        db = new MyDBHandler(getActivity(),null,null,1);
        updateingGif = (ProgressBar) rootView.findViewById(R.id.setting_loading_gif);
        settinglayouthodler = (LinearLayout) rootView.findViewById(R.id.settinglayouthodler);
        settingupdatetext = (TextView) rootView.findViewById(R.id.settingupdatetext);

        try {
            if (updatingDataFlag) {
                updateingGif.setVisibility(View.VISIBLE);
            } else {
                updateingGif.setVisibility(View.INVISIBLE);
            }
        }catch (Exception e){}
        try {
            SystemInfo cSystemInfo = db.getSystemInfo();
            if (cSystemInfo.getUserinfoUpdating() == 1 || cSystemInfo.getAreacodeUpdating() == 1 || cSystemInfo.getAreainfoUpdating() == 1 || cSystemInfo.getDesignUpdating() == 1) {
                setLoadingOn();

            }
        }catch (Exception e){}
        settingsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable())
                {
                    settingsUpdate.setClickable(false);
                    updatingDataFlag = true;
                    updateingGif.setVisibility(View.VISIBLE);

                    settingsUpdate.setVisibility(View.INVISIBLE);
                    final Handler updateTextView = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            updateingGif.setVisibility(View.INVISIBLE);
                            try {
                                SplashActivity.allUser = db.getAllUser();
                                SplashActivity.allDesignation = db.getAllDesignation();
                                SplashActivity.allArea = db.getAllArea();
                                settinglayouthodler.setBackgroundResource(R.drawable.right_sign);
                                settingupdatetext.setText("আপডেট প্রক্রিয়া শেষ হয়েছে");
                                ViewGroup.LayoutParams params = settinglayouthodler.getLayoutParams();
                                params.height = 150;
                                params.width = 250;
                                settinglayouthodler.setLayoutParams(params);
                                Toast toast = Toast.makeText(getActivity(), "আপডেট প্রক্রিয়া শেষ হয়েছে", Toast.LENGTH_SHORT);
                                toast.show();
                                updatingDataFlag=false;
                            }
                            catch (Exception e){}
                        }
                    };
                    try {
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_userInfoUpdating, 1);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areacodeUpadting, 1);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areainfoUpadting, 1);
                        db.updateSystemInfoUpdating(MyDBHandler.COLUMN_designUpdating, 1);
                    }catch (Exception e){}
                    Toast toast = Toast.makeText(getActivity(), "আপডেট প্রক্রিয়া শুরু হয়েছে", Toast.LENGTH_SHORT);
                    settingupdatetext.setText("আপডেট প্রক্রিয়া চলছে");
                    updatingDataFlag=true;
                    TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( t != null){
                        t.setGravity(Gravity.CENTER);
                    }
                    toast.show();
                    Intent i = new Intent(getContext(), UpdateData.class);
                    getActivity().getApplicationContext().startService(i);
                    Thread splashTread;
                    splashTread = new Thread() {
                        @Override
                        public void run() {

                            SystemInfo cSystemInfo = db.getSystemInfoJSON();
                            while(updatingDataFlag)
                            {
                                try
                                {
                                   sleep(10000);
                                   if(cSystemInfo.getUserinfoUpdating()==0 && cSystemInfo.getAreacodeUpdating()==0 && cSystemInfo.getAreainfoUpdating()==0 && cSystemInfo.getDesignUpdating()==0)
                                   {
                                       updatingDataFlag=false;
                                       break;
                                   }
                                    cSystemInfo = db.getSystemInfoJSON();
                                }
                                catch (Exception e)
                                {

                                }
                            }
                            try {
                                db.close();
                                updateTextView.obtainMessage().sendToTarget();
                            }catch (Exception e){}
                            updatingDataFlag = false;
                            settingsUpdate.setClickable(true);
                            //settingsUpdate.setEnabled(true);
                            //updateingGif.setVisibility(View.INVISIBLE);
                        }
                    };
                    splashTread.start();


                }
                else {

                    Toast toast = Toast.makeText(getActivity(), "অনুগ্রহক পূর্বক ইন্টারনেট সংযোগ চালু করুন", Toast.LENGTH_SHORT);
                    TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( t != null){
                        t.setGravity(Gravity.CENTER);
                    }
                    toast.show();

                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void setLoadingOn()
    {

        updatingDataFlag = true;
        updateingGif.setVisibility(View.VISIBLE);
        settingsUpdate.setClickable(false);
        settingsUpdate.setVisibility(View.INVISIBLE);
        settingupdatetext.setText("আপডেট প্রক্রিয়া চলছে");
        final Handler updateTextView = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                updateingGif.setVisibility(View.INVISIBLE);
                try {
                    SplashActivity.allDesignation = db.getAllDesignation();
                    SplashActivity.allUser = db.getAllUser();
                    SplashActivity.allArea = db.getAllArea();
                    Toast toast = Toast.makeText(getActivity(), "আপডেট প্রক্রিয়া শেষ হয়েছে", Toast.LENGTH_SHORT);
                    toast.show();
                    settingupdatetext.setText("আপডেট প্রক্রিয়া শেষ হয়েছে");
                    settinglayouthodler.setBackgroundResource(R.drawable.right_sign);
                    ViewGroup.LayoutParams params = settinglayouthodler.getLayoutParams();
                    params.height = 150;
                    params.width = 250;
                    settinglayouthodler.setLayoutParams(params);
                }
                catch (Exception e){}
            }
        };


        Thread splashTread;
        splashTread = new Thread() {
            @Override
            public void run() {

                SystemInfo cSystemInfo = db.getSystemInfoJSON();
                while(updatingDataFlag)
                {
                    try
                    {
                        sleep(10000);
                        if(cSystemInfo.getUserinfoUpdating()==0 && cSystemInfo.getAreacodeUpdating()==0 && cSystemInfo.getAreainfoUpdating()==0 && cSystemInfo.getDesignUpdating()==0)
                        {
                            updatingDataFlag=false;
                            break;
                        }
                        cSystemInfo = db.getSystemInfoJSON();
                    }
                    catch (Exception e)
                    {

                    }
                }
                try {
                    db.close();
                    updateTextView.obtainMessage().sendToTarget();
                }catch (Exception e){}
                settingsUpdate.setClickable(true);
                //settingsUpdate.setEnabled(true);
                updatingDataFlag = false;

                //updateingGif.setVisibility(View.INVISIBLE);
            }
        };
        splashTread.start();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }

}

