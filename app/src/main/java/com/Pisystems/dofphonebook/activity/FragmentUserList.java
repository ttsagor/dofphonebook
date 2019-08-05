package com.Pisystems.dofphonebook.activity;


        import android.Manifest;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.app.Fragment;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.Pisystems.dofphonebook.R;

        import java.util.ArrayList;
        import java.util.List;


public class FragmentUserList extends Fragment {
    private static Context mContext;
    private String userLisr;
    private int cAreaID;
    public FragmentUserList() {
        // Required empty public constructor
        this.userLisr=MainActivity.userList;
        this.cAreaID=MainActivity.cAreaID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);

    }
    MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);
    List<User> usersSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_userlist, container, false);

        try {
            // Inflate the layout for this fragment

            final ListView mainListView = (ListView) rootView.findViewById(R.id.userList);
            final MyDBHandler db = new MyDBHandler(getActivity(), null, null, 1);
            usersSearch = new ArrayList<User>();
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> deg = new ArrayList<String>();
            ArrayList<String> phone = new ArrayList<String>();
            ArrayList<Integer> id = new ArrayList<Integer>();
            List<User> users = null;
            if (userLisr == "area_id") {
                users = getUserByArea(cAreaID);
                usersSearch = users;
            } else if (userLisr == "favourite") {
                users = db.getFavouriteUser();
                usersSearch = users;
            }

            for (User cUser : users) {
                list.add(cUser.getUser_name());
                deg.add(cUser.getDesignation());
                id.add(cUser.getUser_id());

                if(cUser.getPhone1().length()>0)
                {
                    phone.add(cUser.getPhone1());
                }
                else if(cUser.getPhone2().length()>0)
                {
                    phone.add(cUser.getPhone1());
                }
                else  if(cUser.getOffice_num().length()>0)
                {
                    phone.add(cUser.getOffice_num());
                }
                else
                {
                    phone.add("");
                }
            }

            TextView fragmentuserlistareatext = (TextView) rootView.findViewById(R.id.fragmentuserlistareatext);
            EditText searcheditbox = (EditText) rootView.findViewById(R.id.searcheditboxmultiuser);
            if(userLisr == "favourite")
            {
                fragmentuserlistareatext.setText("ফেবারিট লিস্ট");
            }
            else {
                int infoCode = 0;
                area_info areaInfo = db.getAreaInfo(cAreaID);
                try {
                    infoCode = Integer.parseInt(areaInfo.getArea_details());
                } catch (Exception e) {
                }
                if (infoCode == 1 || infoCode == 2) {
                    fragmentuserlistareatext.setText("জেলা দপ্তর," + db.getAreaName(cAreaID));
                } else if (infoCode == 3 || infoCode == 4) {
                    fragmentuserlistareatext.setText("উপজেলা দপ্তর," + db.getAreaName(cAreaID));
                } else if (infoCode == 5 || infoCode == 6) {
                    fragmentuserlistareatext.setText("বিভাগীয় দপ্তর," + db.getAreaName(cAreaID));
                } else {
                    fragmentuserlistareatext.setText(db.getAreaName(cAreaID));
                }

            }




            String fontPath = "fonts/SolaimanLipi.ttf";
            Typeface tf;
            tf = Typeface.createFromAsset(mContext.getAssets(), fontPath);
            fragmentuserlistareatext.setTypeface(tf);
            searcheditbox.setTypeface(tf);
            searcheditbox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        hideKeyboard(v);
                    }
                }
            });

            searcheditbox.addTextChangedListener(new TextWatcher() {

                boolean ignoreChange = false;

                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    String text = s.toString();
                    text = text.replace("'", "");
                    change(text, mainListView);
                }
            });

            MyCustomAdapterUser adapter = new MyCustomAdapterUser(getActivity(), list, deg, id, phone);
            mainListView.setAdapter(adapter);
        }catch (Exception e){}
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


    public void change(String cond,ListView mainListView)
    {
        if(cond.length()>0) {

            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> deg = new ArrayList<String>();
            ArrayList<String> phone = new ArrayList<String>();
            ArrayList<Integer> id = new ArrayList<Integer>();
            List<User> users = new ArrayList<User>();


            for(User u: usersSearch )
            {
                if(u.getUser_name_eng().toUpperCase().contains(cond.toUpperCase()) || u.getUser_name().contains(cond) || u.getPhone1().contains(cond) || u.getPhone2().contains(cond) || u.getOffice_num().contains(cond) || u.getEmail().contains(cond)|| u.getDesignation().contains(cond) || getAreaById(u.getArea_id()).getAreaName().contains(cond) || getAreaById(u.getArea_id()).getAreaNameEng().toUpperCase().contains(cond.toUpperCase()))
                {
                    users.add(u);
                }
            }

            for (User cUser : users) {
                list.add(cUser.getUser_name());
                deg.add(cUser.getDesignation());
                id.add(cUser.getUser_id());
                phone.add(cUser.getPhone1());
            }

            MyCustomAdapterUser adapter = new MyCustomAdapterUser(getActivity(),list,deg,id,phone);
            mainListView.setAdapter(adapter);

        }
        else
        {
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> deg = new ArrayList<String>();
            ArrayList<String> phone = new ArrayList<String>();
            ArrayList<Integer> id = new ArrayList<Integer>();

            for (User cUser : usersSearch) {
                list.add(cUser.getUser_name());
                deg.add(cUser.getDesignation());
                id.add(cUser.getUser_id());
                phone.add(cUser.getPhone1());
            }

            MyCustomAdapterUser adapter = new MyCustomAdapterUser(getActivity(),list,deg,id,phone);
            mainListView.setAdapter(adapter);
        }

    }

    public static void nxtActivity(int userID) {
        Intent i = new Intent(mContext, MainActivity.class);
        i.putExtra("page_name", "singleUser");
        i.putExtra("user_id", userID);
        System.out.println("singleUser:" + userID);
        mContext.startActivity(i);
    }

    public ArrayList<User> getUserByArea(int area_id)
    {
        ArrayList<User> User = new ArrayList<User>();

        for(User u : SplashActivity.allUser)
        {
            if(u.getArea_id() == area_id)
            {

                User.add(u);
            }
        }

        return User;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }

    private areaCode getAreaById(int area_id)
    {
        for (areaCode area : SplashActivity.allArea) {

            if(area.getAreaID() == area_id)
            {
                return area;
            }
        }
        return new areaCode();

    }
}
