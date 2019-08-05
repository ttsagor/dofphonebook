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
        import java.util.List;


public class FragmentGroupSmsEmailFavList extends Fragment {

    String userlist;
    String userlistD;
    int cAreaID;
    public static Context mContext;
    ListView mainListView;
    CheckBox checkallfavlist;
    View rootView;
    public FragmentGroupSmsEmailFavList() {
        // Required empty public constructor
        this.userlist= MainActivity.userlist;
        this.cAreaID=MainActivity.cAreaID;
        this.userlistD=MainActivity.userlistD;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.groupsmsemailfavlist, container, false);
        //MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);
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

    public static void nxtActivity(int userID) {
        Intent i = new Intent(mContext, MainActivity.class);
        i.putExtra("page_name", "singleUser");
        i.putExtra("user_id", userID);
        mContext.startActivity(i);
        //Intent i = new Intent(mContext, SingleUserActivity.class);
        // i.putExtra("user_id", userID);
        // mContext.startActivity(i);
    }

    public List<User> getUserByDesign(int designation_id)
    {
        ArrayList<User> users = new ArrayList<User>();

        for(User u : SplashActivity.allUser)
        {
            if(u.getDesignationID() == designation_id)
            {

                users.add(u);
            }
        }

        return users;
    }

    public List<User> getUserByArea(int area_id)
    {
        ArrayList<User> users = new ArrayList<User>();

        for(User u : SplashActivity.allUser)
        {
            if(u.getArea_id() == area_id)
            {

                users.add(u);
            }
        }

        return users;
    }


    public String getUserEmailByUserID(int user_id)
    {
        //ArrayList<String> email = new ArrayList<String>();

        for(User u : SplashActivity.allUser)
        {
            if(u.getUser_id() == user_id)
            {

                return u.getEmail();
            }
        }

        return null;
    }

    public String getUserPhoneByUserID(int user_id)
    {
        for(User u : SplashActivity.allUser)
        {
            if(u.getUser_id() == user_id)
            {

                return u.getPhone1();
            }
        }

        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainListView = (ListView) rootView.findViewById(R.id.userList);
        FragmentGroupSmsEmail.finalRecipient = new ArrayList<String>();
        checkallfavlist = (CheckBox) rootView.findViewById(R.id.checkallfavlist);
        checkallfavlist.setChecked(true);
        MyCustomAdapterGroupSmsFavList.unCheckedID = new ArrayList<Integer>();
        final MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> deg = new ArrayList<String>();
        ArrayList<Integer> id = new ArrayList<Integer>();

        List<User> users = null;
        TextView favlistheader = (TextView) rootView.findViewById(R.id.favlisthead);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        favlistheader.setTypeface(tf);

        if(userlist=="deg_user")
        {
            users =getUserByDesign(Integer.parseInt(userlistD));
            favlistheader.setText("পদবী অনুযায়ী");
        }
        else if(userlist=="favourite")
        {
            users =db.getFavouriteUser();
            favlistheader.setText("ফেবারিট তালিকাভিত্তিক");
        }
        else if(userlist=="area_id")
        {
            users = getUserByArea(cAreaID);
            favlistheader.setText("এলাকা / প্রতিষ্ঠানভিত্তিক");
        }


        for (User cUser : users) {
            list.add(cUser.getUser_name());
            deg.add(cUser.getDesignation());
            id.add(cUser.getUser_id());
        }

        MyCustomAdapterGroupSmsFavList adapter = new MyCustomAdapterGroupSmsFavList(getActivity(),list,deg,id);
        mainListView.setAdapter(adapter);
        ImageView groupsmsfavlistsms = (ImageView) rootView.findViewById(R.id.groupsmsfavlistsms);
        ImageView groupsmsfavlistemail = (ImageView) rootView.findViewById(R.id.groupsmsfavlistemail);

        groupsmsfavlistemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Integer> c = new ArrayList<Integer>(MyCustomAdapterGroupSmsFavList.checkedID.size());
                c.addAll(MyCustomAdapterGroupSmsFavList.checkedID);
                c.removeAll(MyCustomAdapterGroupSmsFavList.unCheckedID);

                if (c.size() > 0) {
                    String[] userArry = new String[c.size()];
                    for (int i = 0; i < c.size(); i++) {
                        userArry[i] = getUserEmailByUserID(c.get(i));
                    }


                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, userArry);
                    email.putExtra(Intent.EXTRA_SUBJECT, "");
                    email.putExtra(Intent.EXTRA_TEXT, "");
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "অনুগ্রহক পূর্বক কোন তথ্য সিলেক্ট করুন", Toast.LENGTH_LONG).show();
                }
            }
        });

        groupsmsfavlistsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Integer> c = new ArrayList<Integer> ();
                c.addAll(MyCustomAdapterGroupSmsFavList.checkedID);
                c.removeAll(MyCustomAdapterGroupSmsFavList.unCheckedID);
                if (c.size() > 0) {
                    ArrayList<String> phoneNumber = new ArrayList<String>();
                    for (int i = 0; i < c.size(); i++) {
                        phoneNumber.add(getUserPhoneByUserID(c.get(i)));
                    }
                    FragmentGroupSmsEmail.finalRecipient = phoneNumber;

                    if (phoneNumber.size() > 0) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        intent.putExtra("page_name", "groupsmsnewmessage");
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "আপনার কাংখিত এলাকা/এলাকাসমূহ কোন তথ্য উপস্থিত নাই", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "অনুগ্রহক পূর্বক কোন তথ্য সিলেক্ট করুন", Toast.LENGTH_LONG).show();
                }
            }
        });


        checkallfavlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkallfavlist.isChecked()) {
                    MyCustomAdapterGroupSmsFavList.unCheckedID = new ArrayList<Integer>();
                    ArrayList<String> list = new ArrayList<String>();
                    ArrayList<String> deg = new ArrayList<String>();
                    ArrayList<Integer> id = new ArrayList<Integer>();
                    List<User> users = null;
                    if (userlist == "deg_user") {
                        users = getUserByDesign(Integer.parseInt(userlistD));
                    } else if (userlist == "favourite") {
                        users = db.getFavouriteUser();
                    } else if (userlist == "area_id") {
                        users = getUserByArea(cAreaID);
                    }


                    for (User cUser : users) {
                        list.add(cUser.getUser_name());
                        deg.add(cUser.getDesignation());
                        id.add(cUser.getUser_id());
                    }

                    MyCustomAdapterGroupSmsFavList adapter = new MyCustomAdapterGroupSmsFavList(getActivity(), list, deg, id);
                    mainListView.setAdapter(adapter);
                } else {
                    ArrayList<String> list = new ArrayList<String>();
                    ArrayList<String> deg = new ArrayList<String>();
                    ArrayList<Integer> id = new ArrayList<Integer>();
                    List<User> users = null;
                    if (userlist == "deg_user") {
                        users = getUserByDesign(Integer.parseInt(userlistD));
                    } else if (userlist == "favourite") {
                        users = db.getFavouriteUser();
                    } else if (userlist == "area_id") {
                        users = getUserByArea(cAreaID);
                    }


                    for (User cUser : users) {
                        list.add(cUser.getUser_name());
                        deg.add(cUser.getDesignation());
                        id.add(cUser.getUser_id());
                        MyCustomAdapterGroupSmsFavList.unCheckedID.add(cUser.getUser_id());
                    }

                    MyCustomAdapterGroupSmsFavList adapter = new MyCustomAdapterGroupSmsFavList(getActivity(), list, deg, id);
                    mainListView.setAdapter(adapter);

                }

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }
}

