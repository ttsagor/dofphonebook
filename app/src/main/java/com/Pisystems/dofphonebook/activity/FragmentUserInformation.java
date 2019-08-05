package com.Pisystems.dofphonebook.activity;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.AssetManager;
        import android.content.res.ColorStateList;
        import android.graphics.Bitmap;
        import android.graphics.Matrix;
        import android.graphics.Rect;
        import android.graphics.Typeface;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.pdf.PdfRenderer;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.ParcelFileDescriptor;
        import android.provider.Contacts;
        import android.provider.ContactsContract;
        import android.support.v4.app.Fragment;
        import android.util.Base64;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.webkit.WebView;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.joanzapata.pdfview.PDFView;
        import com.joanzapata.pdfview.listener.OnPageChangeListener;

        import com.Pisystems.dofphonebook.R;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.util.ArrayList;
        import java.util.List;


public class FragmentUserInformation extends Fragment {

    public FragmentUserInformation() {
        // Required empty public constructor
    }
    int userID=0;
    String areaForShare="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.userinformation, container, false);
        final MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);

        TextView name = (TextView) rootView.findViewById(R.id.singleusername);
        TextView designation = (TextView) rootView.findViewById(R.id.singleuserdesignation);
        TextView areaname = (TextView) rootView.findViewById(R.id.singleuserareaname);
        TextView email = (TextView) rootView.findViewById(R.id.singleuseremail);
        TextView phone1 = (TextView) rootView.findViewById(R.id.singleuserphone1);
        TextView phone2 = (TextView) rootView.findViewById(R.id.singleuserphone2);
        TextView lan = (TextView) rootView.findViewById(R.id.singleuserlan);
        TextView singleuserofficename = (TextView) rootView.findViewById(R.id.singleuserofficename);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        name.setTypeface(tf);
        designation.setTypeface(tf);
        areaname.setTypeface(tf);
        email.setTypeface(tf);
        phone1.setTypeface(tf);
        phone2.setTypeface(tf);
        lan.setTypeface(tf);
        singleuserofficename.setTypeface(tf);



        LinearLayout phonenumber1layout = (LinearLayout) rootView.findViewById(R.id.phonenumber1layout);
        LinearLayout phonenumber2layout = (LinearLayout) rootView.findViewById(R.id.phonenumber2layout);
        LinearLayout lannumberlayout = (LinearLayout) rootView.findViewById(R.id.lannumberlayout);
        LinearLayout emailaddresslayout = (LinearLayout) rootView.findViewById(R.id.emailaddresslayout);
        LinearLayout holderlayout = (LinearLayout) rootView.findViewById(R.id.holderlayout);
        ImageView feedback = (ImageView) rootView.findViewById(R.id.feedback);

        SystemInfo cSystemInfo = db.getSystemInfo();
        List<User> users = db.getAreaUserByID(cSystemInfo.getUserID());
        String tName="";
        String tNameENG="";
        String tDeg="";
        String tEmail="";
        String tArea="";
        int area_id=0;
        String phone1number="";
        String phone2number="";
        String lannumber="";

        int parent_id=0;
        int tID=0;
        for (User cUser : users) {
            userID=cUser.getUser_id();
            name.setText(cUser.getUser_name());
            designation.setText(cUser.getDesignation());
            areaname.setText(db.getAreaName(cUser.getArea_id()));
            parent_id=db.getParentID(cUser.getArea_id());
            email.setText(cUser.getEmail());
            phone1.setText(cUser.getPhone1());
            phone2.setText(cUser.getPhone2());
            lan.setText(cUser.getOffice_num());


            tName=cUser.getUser_name();
            tDeg=cUser.getDesignation();
            tEmail=cUser.getEmail();
            tArea=db.getAreaName(cUser.getArea_id());
            phone1number= cUser.getPhone1();
            phone2number = cUser.getPhone2();
            lannumber = cUser.getOffice_num();
            tID=cUser.getUser_id();
            tNameENG=cUser.getUser_name_eng();
            area_id = cUser.getArea_id();
        }
        area_info areaInfo = db.getAreaInfo(area_id);
        int infoCode=0;

        try {
            infoCode = Integer.parseInt(areaInfo.getArea_details());
        }catch (Exception e)
        {}


        if(infoCode==9)
        {
            singleuserofficename.setText("প্রধান কার্যালয়");
            areaname.setText("মৎস্য অধিদপ্তর,মৎস্য ভবন,ঢাকা ");
            areaForShare = "প্রধান কার্যালয়"+"\n"+"মৎস্য অধিদপ্তর,মৎস্য ভবন,ঢাকা ";
        }
        else if(infoCode==1)
        {
            singleuserofficename.setText("জেলা মৎস্য কর্মকর্তার দপ্তর");
            areaname.setText(tArea);
            areaForShare = "জেলা মৎস্য কর্মকর্তার  দপ্তর"+"\n"+tArea;
        }
        else if(infoCode==2)
        {
            singleuserofficename.setText("জেলা মৎস্য কর্মকর্তার দপ্তর");
            areaname.setText(areaInfo.getAddress());
            areaForShare = "জেলা মৎস্য কর্মকর্তার দপ্তর"+"\n"+areaInfo.getAddress();
        }
        else if(infoCode==3)
        {
            singleuserofficename.setText("উপজেলা মৎস্য কর্মকর্তার দপ্তর");
            areaname.setText(tArea+","+db.getAreaName(parent_id));
            areaForShare = "উপজেলা মৎস্য কর্মকর্তার দপ্তর"+"\n"+tArea+","+db.getAreaName(parent_id);
        }
        else if(infoCode==4)
        {
            singleuserofficename.setText("উপজেলা মৎস্য কর্মকর্তা দপ্তর");
            areaname.setText(areaInfo.getAddress());
            areaForShare = "উপজেলা মৎস্য কর্মকর্তার দপ্তর"+"\n"+areaInfo.getAddress();
        }
        else if(infoCode==5)
        {
            singleuserofficename.setText("বিভাগীয় মৎস্য কর্মকর্তার দপ্তর");
            areaname.setText(tArea);
            areaForShare = "বিভাগীয় মৎস্য কর্মকর্তার দপ্তর"+"\n"+tArea;
        }
        else if(infoCode==6)
        {
            singleuserofficename.setText("বিভাগীয় মৎস্য কর্মকর্তার দপ্তর");
            areaname.setText(areaInfo.getAddress());
            areaForShare = "বিভাগীয় মৎস্য কর্মকর্তার দপ্তর"+"\n"+areaInfo.getAddress();
        }
        else if(infoCode==7)
        {
            singleuserofficename.setText(tArea);
            areaname.setText("মৎস্য অধিদপ্তর,মৎস্য ভবন,ঢাকা ");
            areaForShare = tArea+"\n"+"মৎস্য অধিদপ্তর,মৎস্য ভবন,ঢাকা ";
        }
        else if(infoCode==8)
        {
            singleuserofficename.setText(tArea);
            areaname.setText(areaInfo.getAddress());
            areaForShare = tArea+"\n"+areaInfo.getAddress();
        }
        else
        {
            // singleuserofficename.setText("মৎস্য কর্মকর্তা দপ্তর");
            singleuserofficename.setText("");
            areaname.setText(tArea);
            areaForShare = ""+"\n"+tArea;
        }


        final String n = tName;
        final String nENG = tNameENG;
        final String d = tDeg;
        final String e = tEmail;
        final String a = areaForShare;

        final String p1 = phone1number;
        final String p2 = phone2number;
        final String l = lannumber;
        final int id = tID;
        final String aLoc = db.getAreaLocation(area_id);

        if(p1.length() == 0)
        {
            holderlayout.removeView(phonenumber1layout);
        }
        if(p2.length() == 0)
        {
            holderlayout.removeView(phonenumber2layout);
        }
        if(e.length() == 0)
        {
            holderlayout.removeView(emailaddresslayout);
        }
        if(l.length() == 0)
        {
            holderlayout.removeView(lannumberlayout);
        }
        final ImageView singleusershare = (ImageView) rootView.findViewById(R.id.singleusershare);
        final ImageView googlemap = (ImageView) rootView.findViewById(R.id.googlemap);


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  MainActivity.class);
                i.putExtra("page_name", "feedback");
                i.putExtra("user_id",userID);
                startActivity(i);
            }
        });

        singleusershare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String line="";
                    if(n.length()>0)
                    {
                        line+=""+n+"\n";
                    }
                    if(d.length()>0)
                    {
                        line+=""+d+"\n";
                    }
                    if(a.length()>0)
                    {
                        line+=""+a+"\n";
                    }
                    if(p1.length()>0)
                    {
                        line+="মোবাইলঃ "+p1+"\n";
                    }
                    if(p2.length()>0)
                    {
                        line+="মোবাইলঃ"+p2+"\n";
                    }
                    if(l.length()>0)
                    {
                        line+="ফোনঃ "+l+"\n";
                    }
                    if(e.length()>0)
                    {
                        line+="ইমেইলঃ "+e+"\n";
                    }
                    String shareBody = line;
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "যোগাযোগের তথ্য  "+n+"("+d+","+a+")");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }catch(Exception e){
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    //System.out.println( e.toString());
                }
            }
        });

        //db.getAreaLocation(0);
        googlemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (aLoc == "" || aLoc == "00" || !(aLoc.length()>2)) {
                        Toast.makeText(getContext(), "মানচিত্রে অবস্থান উপস্থিত নাই", Toast.LENGTH_LONG).show();
                    }
                    else if(!isNetworkAvailable())
                    {
                        Toast toast = Toast.makeText(getActivity(), "অনুগ্রহক পূর্বক ইন্টারনেট সংযোগ চালু করেন", Toast.LENGTH_SHORT);
                        TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                        if( t != null){
                            t.setGravity(Gravity.CENTER);
                        }
                        toast.show();
                    }
                    else {
                        String label = areaForShare;
                        String uriBegin = "geo: " + aLoc;
                        String query = " " + aLoc + "(" + label + ")";
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


