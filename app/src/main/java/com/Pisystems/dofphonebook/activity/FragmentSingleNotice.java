package com.Pisystems.dofphonebook.activity;


        import android.app.Activity;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Typeface;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.os.Handler;
        import android.os.Message;
        import android.provider.MediaStore;
        import android.support.v4.app.Fragment;
        import android.text.Html;
        import android.text.method.ScrollingMovementMethod;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.view.WindowManager;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.webkit.WebSettings;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.Pisystems.dofphonebook.R;
        import com.squareup.picasso.Picasso;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;


public class FragmentSingleNotice extends Fragment{

    int cNoticeID;
    public FragmentSingleNotice() {
        // Required empty public constructor
        cNoticeID = MainActivity.cAreaID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    Notice cNotice;
    String dateForShare="";
    String refForShare="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_single_notice, container, false);
        MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);

        if(SplashActivity.allDesignation.size()==0)
        {
            SplashActivity.allDesignation =db.getAllDesignation();
        }

        if(SplashActivity.allUser.size()==0)
        {
            SplashActivity.allUser = db.getAllUser();
        }

        if(SplashActivity.allArea.size()==0)
        {
            SplashActivity.allArea =db.getAllArea();
        }

        cNotice = db.geNotice(cNoticeID);
        TextView ref_number = (TextView) rootView.findViewById(R.id.ref_number);
        TextView title = (TextView) rootView.findViewById(R.id.singleNoticeTitle);
        TextView date = (TextView) rootView.findViewById(R.id.singleNoticeDate);
        TextView contains = (TextView) rootView.findViewById(R.id.singleNoticecontains);
        ImageButton singlenoticeshare = (ImageButton) rootView.findViewById(R.id.singlenoticeshare);
        TextView singleNoticeDetails = (TextView) rootView.findViewById(R.id.singleNoticeDetails);


        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        title.setTypeface(tf);
        date.setTypeface(tf);
        ref_number.setTypeface(tf);
        contains.setTypeface(tf);
        singleNoticeDetails.setTypeface(tf);

        String styledText = "<u><font color='blue'>বিস্তারিত দেখতে এইখানে ক্লিক করুন  </font></u>.";
        singleNoticeDetails.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);

        title.setText(cNotice.getNotice_title());
        String[] sep = cNotice.getNotice_date_time().split(" ");

        date.setText("তারিখঃ "+englishToBanglaNumber(sep[0]));
        dateForShare = "তারিখঃ "+englishToBanglaNumber(sep[0]);

        ref_number.setText("স্মারক নংঃ "+englishToBanglaNumber(cNotice.getRef_number()));
        refForShare = "স্মারক নংঃ "+englishToBanglaNumber(cNotice.getRef_number());

        contains.setText(cNotice.getNotice_contains());

        contains.setMovementMethod(new ScrollingMovementMethod());


        if(!(cNotice.getNotice_url().length()>0)) {
            singleNoticeDetails.setVisibility(View.GONE);
        }
        singlenoticeshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody ="";
                if(cNotice.getNotice_url().length()>0)
                {
                    shareBody = cNotice.getNotice_title()+"\n"+dateForShare + "\n" + refForShare + "\n" + cNotice.getNotice_contains() + "\n\n\n" + "Link: " + Uri.parse("http://docs.google.com/gview?embedded=true&url=" + cNotice.getNotice_url());
                }
                else
                {
                    shareBody = cNotice.getNotice_title()+"\n"+dateForShare + "\n" + refForShare + "\n" + cNotice.getNotice_contains();
                }
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, cNotice.getNotice_title());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        singleNoticeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(isNetworkAvailable()) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://docs.google.com/gview?embedded=true&url=" + cNotice.getNotice_url()));
                        startActivity(browserIntent);
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

    private String englishToBanglaNumber(String number)
    {
        number = number.replace('1','১');
        number = number.replace('2','২');
        number = number.replace('3','৩');
        number = number.replace('4','৪');
        number = number.replace('5','৫');
        number = number.replace('6','৬');
        number = number.replace('7','৭');
        number = number.replace('8','৮');
        number = number.replace('9','৯');
        number = number.replace('0','০');
        return number;
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
