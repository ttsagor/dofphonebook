package com.Pisystems.dofphonebook.activity;


import android.Manifest;
        import android.app.Activity;
import android.content.Context;
import android.content.Intent;
        import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
        import android.os.Bundle;
        import android.provider.Contacts;
        import android.provider.ContactsContract;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.Pisystems.dofphonebook.R;
        import java.util.List;


public class FragmentSingleUser extends Fragment {

    int cAreaID;
    String signleUser;
    public FragmentSingleUser() {
        this.cAreaID=MainActivity.cAreaID;
        this.signleUser=MainActivity.signleUser;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    int userID=0;
    String areaForShare="";
    MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.single_user, container, false);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
        boolean contactFlag=false;
        boolean isFavouriteT=false;


        TextView name = (TextView) rootView.findViewById(R.id.singleusername);
        TextView designation = (TextView) rootView.findViewById(R.id.singleuserdesignation);
        TextView areaname = (TextView) rootView.findViewById(R.id.singleuserareaname);
        TextView email = (TextView) rootView.findViewById(R.id.singleuseremail);
        TextView phone1 = (TextView) rootView.findViewById(R.id.singleuserphone1);
        TextView phone2 = (TextView) rootView.findViewById(R.id.singleuserphone2);
        TextView lan = (TextView) rootView.findViewById(R.id.singleuserlan);
        TextView singleuserofficename = (TextView) rootView.findViewById(R.id.singleuserofficename);


        TextView singleusermaptitle = (TextView) rootView.findViewById(R.id.singleusermaptitle);
        TextView singleuserlantitle = (TextView) rootView.findViewById(R.id.singleuserlantitle);
        TextView singleusermobile2title = (TextView) rootView.findViewById(R.id.singleusermobile2title);
        TextView singleusermobile1title = (TextView) rootView.findViewById(R.id.singleusermobile1title);
        TextView singleuseremailtitle = (TextView) rootView.findViewById(R.id.singleuserofficename);

        LinearLayout phonenumber1layout = (LinearLayout) rootView.findViewById(R.id.phonenumber1layout);
        LinearLayout phonenumber2layout = (LinearLayout) rootView.findViewById(R.id.phonenumber2layout);
        LinearLayout lannumberlayout = (LinearLayout) rootView.findViewById(R.id.lannumberlayout);
        LinearLayout emailaddresslayout = (LinearLayout) rootView.findViewById(R.id.emailaddresslayout);
        LinearLayout holderlayout = (LinearLayout) rootView.findViewById(R.id.holderlayout);


        ImageView phone1call = (ImageView) rootView.findViewById(R.id.singleuserphone1call);
        ImageView phone1msg = (ImageView) rootView.findViewById(R.id.singleuserphone1msg);

        ImageView phone2call = (ImageView) rootView.findViewById(R.id.singleuserphone2call);
        ImageView phone2msg = (ImageView) rootView.findViewById(R.id.singleuserphone2msg);

        ImageView lancall = (ImageView) rootView.findViewById(R.id.singleuserlancall);
        ImageView feedback = (ImageView) rootView.findViewById(R.id.feedback);

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

        singleusermaptitle.setTypeface(tf);
        singleuserlantitle.setTypeface(tf);
        singleusermobile2title.setTypeface(tf);
        singleusermobile1title.setTypeface(tf);
        singleuseremailtitle.setTypeface(tf);

        List<User> users=null;

        final MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);

        if(signleUser=="area_id")
        {

            users = db.getAreaUser(cAreaID);
        }
        else if(signleUser=="user_id")
        {
            users = db.getAreaUserByID(cAreaID);
        }
        System.out.println("cAreaID : "+cAreaID);


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
            userID= cUser.getUser_id();
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
            //System.out.println("NAME:" + tNameENG);
        }
        area_info areaInfo = db.getAreaInfo(area_id);
        int infoCode=0;

        try {
            infoCode = Integer.parseInt(areaInfo.getArea_details());
        }catch (Exception e)
        {}
        System.out.println("parent :" + infoCode);

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
            areaForShare = "জেলা মৎস্য কর্মকর্তার দপ্তর"+"\n"+tArea;
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
            singleuserofficename.setText("উপজেলা মৎস্য কর্মকর্তার দপ্তর");
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
        //System.out.println(p1 +":"+ p2 + ":"+e +":"+ l);



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
        final ImageView saveButton = (ImageView) rootView.findViewById(R.id.savetophone);
        final ImageView favButton = (ImageView) rootView.findViewById(R.id.addtofav);
        final ImageView singleusershare = (ImageView) rootView.findViewById(R.id.singleusershare);
        final ImageView singleusermailto = (ImageView) rootView.findViewById(R.id.singleusermailto);

        final ImageView googlemap = (ImageView) rootView.findViewById(R.id.googlemap);

        if(db.numberOfRowsFavourite(id)>0)
        {
            isFavouriteT=true;
            favButton.setImageResource(R.drawable.favorite_on);
            favButton.setTag(android.R.drawable.btn_star_big_on);
        }
        else
        {
            favButton.setTag(R.drawable.favorite);
        }
        final boolean isFavourite = isFavouriteT;

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


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent addContactIntent = new Intent(Contacts.Intents.Insert.ACTION, Contacts.People.CONTENT_URI);
                    addContactIntent.putExtra(Contacts.Intents.Insert.NAME, nENG);
                    addContactIntent.putExtra(Contacts.Intents.Insert.PHONE, p1);
                   // addContactIntent.putExtra(Contacts.Intents.Insert.PHONE, p2);

                    addContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, p1);
                    addContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, "Mobile");
                    //addContactIntent.putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, p2);
                    //addContactIntent.putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE, "MOBILE-2");
                   // addContactIntent.putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE, l);
                    //addContactIntent.putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE, "WORK");

                   // addContactIntent.putExtra(Contacts.Intents.Insert.EMAIL,e);
                    //addContactIntent.putExtra(Contacts.Intents.Insert.JOB_TITLE,d);
                    //addContactIntent.putExtra(Contacts.Intents.Insert.COMPANY,a);
                    startActivity(addContactIntent);
                }catch(Exception e){
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                    //System.out.println( e.toString());
                }
            }
        });
        //db.getAreaLocation(0);
        googlemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(), "মানচিত্রে অবস্থান উপস্থিত নাই "+aLoc, Toast.LENGTH_LONG).show();
                System.out.println("location: " + aLoc);
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

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  MainActivity.class);
                i.putExtra("page_name", "feedback");
                i.putExtra("user_id",userID);
                startActivity(i);
            }
        });


        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if ((Integer) favButton.getTag() == android.R.drawable.btn_star_big_on) {
                        System.out.println(db.removeFavourite(id));
                        favButton.setImageResource(R.drawable.favorite);
                        favButton.setTag(android.R.drawable.btn_star_big_off);
                        Toast.makeText(getContext(), "Favourite Removed", Toast.LENGTH_LONG).show();
                    } else {
                        Favourite f = new Favourite();
                        f.setUser_id(id);
                        db.insertFavourite(f);
                        favButton.setImageResource(R.drawable.favorite_on);
                        favButton.setTag(android.R.drawable.btn_star_big_on);
                        Toast.makeText(getContext(), "Favourite Added", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //System.out.println(contactFlag);
        phone1call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + p1));
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        if(e.length() > 0)
        {
            singleusermailto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", e, null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                        startActivity(Intent.createChooser(emailIntent, "Send Email"));
                    }catch (Exception e)
                    {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        phone2call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   Intent intent = new Intent(Intent.ACTION_CALL);
                   intent.setData(Uri.parse("tel:" + p2));
                   startActivity(intent);
               }catch (Exception e)
               {
                   Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
               }
            }
        });

        phone1msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", p1);
                    startActivity(smsIntent);
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        phone2msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                   smsIntent.setType("vnd.android-dir/mms-sms");
                   smsIntent.putExtra("address", p2);
                   startActivity(smsIntent);
               }catch (Exception e){Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();}

            }
        });

        lancall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + l));
                    startActivity(intent);
                }catch (Exception e)
                {
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
