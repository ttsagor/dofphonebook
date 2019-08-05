package com.Pisystems.dofphonebook.activity;

        import android.app.Activity;
        import android.app.ActivityManager;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.PorterDuff;
        import android.graphics.drawable.ShapeDrawable;
        import android.graphics.drawable.shapes.RectShape;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.Toast;

        import com.Pisystems.dofphonebook.R;

        import java.io.UnsupportedEncodingException;
        import java.net.URLEncoder;


public class FragmentFeedback extends Fragment {
int userID=0;
    public FragmentFeedback() {
        // Required empty public constructor
        this.userID= MainActivity.cAreaID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final  View rootView = inflater.inflate(R.layout.feedback, container, false);
        final EditText et = (EditText) rootView.findViewById(R.id.feedbackedittext);
        final ImageView et1 = (ImageView) rootView.findViewById(R.id.sendfeedback);
        final LinearLayout l = (LinearLayout) rootView.findViewById(R.id.feedbackfulllayout);

        et.setBackgroundColor(Color.parseColor("#ffffff"));


        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    et.getLayoutParams().height=10;
                }
                else if(hasFocus)
                {
                    et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
                }
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                if ( et.getText().length()>0)
                {

                    if (isNetworkAvailable()) {
                        try {
                            MyDBHandler db = new MyDBHandler(getContext(), null, null, 1);
                            SystemInfo cSystemInfo = db.getSystemInfo();

                            final JSON asyncTask = new JSON();
                            String msgEncode = et.getText().toString();
                            asyncTask.sendFeedBack = true;
                            asyncTask.mContex = rootView.getContext();
                            try {
                                msgEncode = URLEncoder.encode(msgEncode, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            String query = "to=" + userID + "&from=" + cSystemInfo.getUserID() + "&message=" + msgEncode;
                            asyncTask.execute("http://digital-phonebook.com/service/insert_feedback" + "?" + query);
                            et.setText("");
                            if(userID>0)
                            {
                                getActivity().finish();
                            }
                            else
                            {
                                Intent i = new Intent(getActivity(),  MainActivity.class);
                                startActivity(i);
                                getActivity().finish();
                            }
                        }catch (Exception e){}
                }
                else {
                    Toast.makeText(rootView.getContext(), "অনুগ্রহক পূর্বক ইন্টারনেট সংযোগ চালু করুন", Toast.LENGTH_LONG).show();
                }
            }
            }
        });
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
                = (ConnectivityManager) getActivity().getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }
}
