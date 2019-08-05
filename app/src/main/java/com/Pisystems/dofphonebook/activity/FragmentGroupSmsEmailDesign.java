package com.Pisystems.dofphonebook.activity;


        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.Pisystems.dofphonebook.R;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;


public class FragmentGroupSmsEmailDesign extends Fragment {

    public FragmentGroupSmsEmailDesign() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
public static Context mContext;
    ListView mainListView;
    CheckBox checkall;
    ArrayList<String> degcheckedID= new ArrayList<String>();
    ArrayList<String> deg = new ArrayList<String>();
    ArrayList<Integer> count = new ArrayList<Integer>();
    View rootView;
    ArrayList<String> degName = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.groupsmsemail_design, container, false);
      //  MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);
        mContext=getActivity();
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
    public static void nxtActivity(String deg) {
        Intent i = new Intent(mContext, MainActivity.class);
        i.putExtra("page_name", "groupsmsemailfavlist");
        i.putExtra("deg_user", deg);
        mContext.startActivity(i);
    }

    public ArrayList<String> getUserEmailByDesign(int designation_id)
    {
        ArrayList<String> email = new ArrayList<String>();

        for(User u : SplashActivity.allUser)
        {
            if(u.getDesignationID() == designation_id)
            {

                email.add(u.getEmail());
            }
        }

        return email;
    }

    public ArrayList<String> getUserPhonweByDesign(int designation_id)
    {
        ArrayList<String> phone = new ArrayList<String>();

        for(User u : SplashActivity.allUser)
        {
            if(u.getDesignationID() == designation_id)
            {

                phone.add(u.getPhone1());
            }
        }

        return phone;
    }

    public String getDesignationBN(int designation_id)
    {
        for(Designation d : SplashActivity.allDesignation)
        {
            if(d.getDesign_id() == designation_id)
            {
                return d.getDesign_bn();
            }
        }
        return "";
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            degcheckedID = new ArrayList<String>();
            deg = new ArrayList<String>();
            count = new ArrayList<Integer>();
            degName = new ArrayList<String>();
            mainListView = (ListView) rootView.findViewById(R.id.userList);
            checkall = (CheckBox) rootView.findViewById(R.id.groupsmsdesigncheckall);
            checkall.setChecked(false);
            final MyDBHandler db = new MyDBHandler(getActivity(), null, null, 1);
            MyCustomAdapterGroupSmsDeg.checkedID = new ArrayList<String>();
            FragmentGroupSmsEmail.finalRecipient = new ArrayList<String>();
            HashMap<String, Integer> map = db.getDesignation();

            TextView groupsmsdesigntitle = (TextView) rootView.findViewById(R.id.groupsmsdesigntitle);
            String fontPath = "fonts/SolaimanLipi.ttf";
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
            groupsmsdesigntitle.setTypeface(tf);

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                deg.add(key);
                count.add(value);
                //degcheckedID.add(key);
                degName.add(getDesignationBN(Integer.parseInt(key)));
                //System.out.println(key+" "+value);
            }

            MyCustomAdapterGroupSmsDeg adapter = new MyCustomAdapterGroupSmsDeg(getActivity(), deg, count, degName);
            mainListView.setAdapter(adapter);
            ImageView groupsmsdegesms = (ImageView) rootView.findViewById(R.id.groupsmsdegesms);
            ImageView groupsmsdegemail = (ImageView) rootView.findViewById(R.id.groupsmsdegemail);

            groupsmsdegemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MyCustomAdapterGroupSmsDeg.checkedID.size() > 0) {
                        ArrayList<String> emailList = new ArrayList<String>();
                        for (int i = 0; i < MyCustomAdapterGroupSmsDeg.checkedID.size(); i++) {
                            emailList.addAll(getUserEmailByDesign(Integer.parseInt(MyCustomAdapterGroupSmsDeg.checkedID.get(i))));
                            //System.out.println(MyCustomAdapterGroupSmsDeg.checkedID.get(i)+" totalNum:"+phoneNumber.size());
                        }

                        String[] userArry = new String[emailList.size()];
                        for (int i = 0; i < emailList.size(); i++) {
                            userArry[i] = emailList.get(i);
                        }


                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, userArry);
                        email.putExtra(Intent.EXTRA_SUBJECT, "");
                        email.putExtra(Intent.EXTRA_TEXT, "");
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "অনুগ্রহক পূর্বক কোন পদবী সিলেক্ট করুন", Toast.LENGTH_LONG).show();
                    }
                }
            });

            groupsmsdegesms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MyCustomAdapterGroupSmsDeg.checkedID.size() > 0) {
                        try {
                            ArrayList<String> phoneNumber = new ArrayList<String>();
                            for (int i = 0; i < MyCustomAdapterGroupSmsDeg.checkedID.size(); i++) {
                                phoneNumber.addAll(getUserPhonweByDesign(Integer.parseInt(MyCustomAdapterGroupSmsDeg.checkedID.get(i))));
                                // System.out.println(MyCustomAdapterGroupSmsDeg.checkedID.get(i) + " totalNum:" + phoneNumber.size());
                            }

                            FragmentGroupSmsEmail.finalRecipient = phoneNumber;

                            if (phoneNumber.size() > 0) {
                                Intent intent = new Intent(mContext, MainActivity.class);
                                intent.putExtra("page_name", "groupsmsnewmessage");
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "আপনার কাংখিত পদবী/পদবীসমূহতে কোন তথ্য উপস্থিত নাই", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                        }
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "অনুগ্রহক পূর্বক কোন পদবী সিলেক্ট করুন", Toast.LENGTH_LONG).show();
                    }
                }
            });

            checkall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkall.isChecked()) {
                        //MyCustomAdapterGroupSmsDeg.checkedID = degcheckedID;
                        HashMap<String, Integer> map = db.getDesignation();

                        for (Map.Entry<String, Integer> entry : map.entrySet()) {
                            String key = entry.getKey();
                            int value = entry.getValue();
                            deg.add(key);
                            count.add(value);
                            MyCustomAdapterGroupSmsDeg.checkedID.add(key);
                            degName.add(getDesignationBN(Integer.parseInt(key)));
                        }

                        MyCustomAdapterGroupSmsDeg adapter = new MyCustomAdapterGroupSmsDeg(getActivity(), deg, count, degName);
                        mainListView.setAdapter(adapter);
                    } else {
                        MyCustomAdapterGroupSmsDeg.checkedID = new ArrayList<String>();
                        HashMap<String, Integer> map = db.getDesignation();

                        for (Map.Entry<String, Integer> entry : map.entrySet()) {
                            String key = entry.getKey();
                            int value = entry.getValue();
                            deg.add(key);
                            count.add(value);
                            //MyCustomAdapterGroupSmsDeg.checkedID .add(key);
                            degName.add(getDesignationBN(Integer.parseInt(key)));
                        }

                        MyCustomAdapterGroupSmsDeg adapter = new MyCustomAdapterGroupSmsDeg(getActivity(), deg, count, degName);
                        mainListView.setAdapter(adapter);
                    }
                }
            });
        }catch (Exception e){}
    }
    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }

}
