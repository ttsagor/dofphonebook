package com.Pisystems.dofphonebook.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Pisystems.dofphonebook.R;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    boolean doubleBackToExitPressedOnce = false;
Context mContext;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        rootView = inflater.inflate(R.layout.fragment_home, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    // Start the service


    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onResume() {
        super.onResume();



        final MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);
        LinearLayout phonebook = (LinearLayout) rootView.findViewById(R.id.phonebook);
        LinearLayout notice = (LinearLayout) rootView.findViewById(R.id.notice);
        LinearLayout groupsmsemail = (LinearLayout) rootView.findViewById(R.id.groupsmsemail);
        LinearLayout help = (LinearLayout) rootView.findViewById(R.id.help);
        LinearLayout fav = (LinearLayout) rootView.findViewById(R.id.favorite);
        //startService(new View(getActivity()));
        final Animation animationFadeInr = AnimationUtils.loadAnimation(getActivity(), R.anim.tanslater);
        final Animation animationFadeInlrr = AnimationUtils.loadAnimation(getActivity(), R.anim.tanslatelrr);
        final Animation animationFadeIntbr = AnimationUtils.loadAnimation(getActivity(), R.anim.translatetbr);
        final Animation animationFadeInbtr = AnimationUtils.loadAnimation(getActivity(), R.anim.translatebtr);


        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();

        if(SplashActivity.firstTimePopUpFlage)
        {
            new AlertDialog.Builder(getContext())
                    .setTitle("Welcome \n to Digital Phonebook")
                    .setMessage("ভুল তথ্যের  সংশোধন অথবা পরিবর্তনের জন্য মেনুবারের ফিডব্যাক অপশনে গিয়ে সঠিক তথ্য  প্রদান করুন")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setCancelable(false)
                    .show();
            SplashActivity.firstTimePopUpFlage = false;


        }


        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if(doubleBackToExitPressedOnce)
                        {
                            ActivityCompat.finishAffinity(getActivity());
                            System.exit(0);
                            return false;
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Press again to exit", Toast.LENGTH_SHORT).show();
                            doubleBackToExitPressedOnce=true;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    doubleBackToExitPressedOnce=false;
                                }
                            }, 2000);
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        phonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  MainActivity.class);
                i.putExtra("area_id", 0);
                i.putExtra("page_name", "phonebook");
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  MainActivity.class);
                i.putExtra("page_name", "notice");
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        groupsmsemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  MainActivity.class);
                i.putExtra("page_name", "groupsmsemail");
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  MainActivity.class);
                i.putExtra("page_name", "help");
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.numberOfRowsFavouriteall() > 0) {
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    i.putExtra("page_name", "userlist");
                    i.putExtra("favourite", "userlist");
                    startActivity(i);
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "ফেবারিট লিস্টে কোন নাম্বার উপস্তিত নেই", Toast.LENGTH_LONG).show();
                }
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });
    }
}
