package com.Pisystems.dofphonebook.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Pisystems.dofphonebook.R;


public class FriendsFragment extends Fragment {

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        LinearLayout about_us_phone = (LinearLayout) rootView.findViewById(R.id.about_us_phone);
        LinearLayout about_us_email = (LinearLayout) rootView.findViewById(R.id.about_us_email);
        LinearLayout about_us_web = (LinearLayout) rootView.findViewById(R.id.about_us_web);
        LinearLayout about_us_map = (LinearLayout) rootView.findViewById(R.id.about_us_map);


        about_us_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(isNetworkAvailable()) {

                        String url = "http://www.rezanreza.com";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    else{
                        Toast toast = Toast.makeText(getActivity(), "অনুগ্রহক পূর্বক ইন্টারনেট সংযোগ চালু করেন", Toast.LENGTH_SHORT);
                        TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                        if( t != null){
                            t.setGravity(Gravity.CENTER);
                        }
                        toast.show();
                    }
                }catch (Exception e)
                {}
            }
        });

        about_us_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "rezaandrezabd@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(Intent.createChooser(emailIntent, "Send Email"));
                }catch (Exception e)
                {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });



        about_us_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + "01914811118"));
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        about_us_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!isNetworkAvailable())
                    {
                        Toast toast = Toast.makeText(getActivity(), "অনুগ্রহক পূর্বক ইন্টারনেট সংযোগ চালু করেন", Toast.LENGTH_SHORT);
                        TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                        if( t != null){
                            t.setGravity(Gravity.CENTER);
                        }
                        toast.show();
                    }
                    else {
                        String label = "Reza & Reza Solutions";
                        String uriBegin = "geo: " + "23.773819, 90.366298";
                        String query = " " + "23.773819, 90.366298" + "(" + label + ")";
                        String encodedQuery = Uri.encode(query);
                        String uriString = uriBegin + "?q=" + encodedQuery;
                        Uri uri = Uri.parse(uriString);
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });




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


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
