package com.Pisystems.dofphonebook.activity;


        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.ActivityNotFoundException;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.KeyEvent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.speech.RecognizerIntent;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.SearchView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.Pisystems.dofphonebook.R;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Locale;
        import java.util.Map;


public class phonebookFragment extends Fragment {

    public phonebookFragment() {
        this.cAreaID = MainActivity.cAreaID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private int  cAreaID=0;
    public static int listCountFlag;
    private static Context mContext;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<Integer> id = new ArrayList<Integer>();
    Map<Integer, Integer> totalArea = new HashMap<Integer, Integer>();
    Map<Integer, Integer> totalUser = new HashMap<Integer, Integer>();
    List<areaCode> areas;
    MyDBHandler mydb;
    EditText searcheditbox;
    ListView mainListView;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=getActivity();
        rootView = inflater.inflate(R.layout.fragment_phonebook, container, false);
        return rootView;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void change(String cond,ListView mainListView)
    {
        if(cond.length()>0) {
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> deg = new ArrayList<String>();
            ArrayList<Integer> id = new ArrayList<Integer>();
            ArrayList<String> phone1 = new ArrayList<String>();
            ArrayList<String> phone2 = new ArrayList<String>();
            ArrayList<String> email = new ArrayList<String>();
            ArrayList<String> lan = new ArrayList<String>();
            ArrayList<String> area = new ArrayList<String>();

            List<User> users = new ArrayList<User>();
            for(User u : SplashActivity.allUser)
            {
                if(u.getUser_name_eng().toUpperCase().contains(cond.toUpperCase()) || u.getUser_name().contains(cond) || u.getPhone1().contains(cond) || u.getPhone2().contains(cond) || u.getOffice_num().contains(cond) || u.getEmail().contains(cond) || u.getDesignation().contains(cond) || getAreaById(u.getArea_id()).getAreaName().contains(cond) || getAreaById(u.getArea_id()).getAreaNameEng().toUpperCase().contains(cond.toUpperCase()))
                {
                    users.add(u);
                }
            }

            try {
                  for (User cUser : users) {
                    list.add(cUser.getUser_name());
                    deg.add(cUser.getDesignation());
                    id.add(cUser.getUser_id());
                    phone1.add(cUser.getPhone1());
                    phone2.add(cUser.getPhone2());
                    email.add(cUser.getEmail());
                    lan.add(cUser.getOffice_num());
                    area.add(getAreaById(cUser.getArea_id()).getAreaName());
                }
                MyCustomAdapterSearch adapter = new MyCustomAdapterSearch(mContext, list, deg, id, phone1, phone2, email, lan,area);
                mainListView.setAdapter(adapter);

                // db.close();
            }
            catch (Exception e){}

            //ListView mainListView = (ListView) findViewById(R.id.areaList);

        }
        else
        {
             /*list = new ArrayList<String>();
             id = new ArrayList<Integer>();
             totalArea = new HashMap<Integer, Integer>();
             totalUser = new HashMap<Integer, Integer>();

            for (areaCode area : areas) {
                list.add(area.getAreaName());
                id.add(area.getAreaID());
                totalArea.put(area.getAreaID(), db.numberOfRows(area.getAreaID()));
                totalUser.put(area.getAreaID(), db.numberOfRowsUser(area.getAreaID()));
            }
            totalUser.put(cAreaID, db.numberOfRowsUser(cAreaID));
            MyCustomAdapter adapter = new MyCustomAdapter(list,id, getActivity(),cAreaID,totalArea,totalUser);
            mainListView.setAdapter(null);
            mainListView.setAdapter(adapter);*/
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static void nxtActivity(int pID,int cAreaID,Map<Integer, Integer> totalArea,  Map<Integer, Integer> totalUser) {


        if(cAreaID==pID){

            if(totalUser.get(pID)>1){

                Intent i = new Intent(mContext,  MainActivity.class);
                i.putExtra("area_id", pID);

                i.putExtra("page_name", "userlist");
                mContext.startActivity(i);
               /* Intent i = new Intent(mContext, user_list.class);
                i.putExtra("area_id", pID);
                System.out.println(pID);
                mContext.startActivity(i);*/

            }
            else if(totalUser.get(pID)==1){
                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("page_name", "singleUser");
                i.putExtra("area_id", pID);
                mContext.startActivity(i);
            }
            else {
                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("page_name", "noUserFound");
                mContext.startActivity(i);
            }

        }
        else if(totalArea.get(pID)==0){

            if(totalUser.get(pID)>1){
                Intent i = new Intent(mContext,  MainActivity.class);
                i.putExtra("area_id", pID);
                i.putExtra("page_name", "userlist");
                mContext.startActivity(i);

            }
            else if(totalUser.get(pID)==1){
                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("page_name", "singleUser");
                i.putExtra("area_id", pID);
                mContext.startActivity(i);
            }
            else {
                    Intent i = new Intent(mContext, MainActivity.class);
                    i.putExtra("page_name", "noUserFound");
                    mContext.startActivity(i);
            }

        }

        else {

            Intent i = new Intent(mContext, MainActivity.class);
            i.putExtra("page_name", "phonebook");
            i.putExtra("area_id", pID);
            System.out.println(pID);
            mContext.startActivity(i);

        }
    }

    public static void nxtActivity(int userID) {
        Intent i = new Intent(mContext, MainActivity.class);
        i.putExtra("page_name", "singleUser");
        i.putExtra("user_id", userID);
        mContext.startActivity(i);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(requestCode==1)
        {
            try {
                ArrayList<String> results;
                results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String text = results.get(0).replace("'", "");
                searcheditbox.setText(text);
            }
            catch (Exception e){}
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);
        mydb=db;



        //ArrayList<Integer> skipHeadOffice=new ArrayList<>();
       list = new ArrayList<String>();
       id = new ArrayList<Integer>();
       totalArea = new HashMap<Integer, Integer>();
       totalUser = new HashMap<Integer, Integer>();
       areas = new ArrayList<areaCode>();;
        searcheditbox = (EditText) rootView.findViewById(R.id.searcheditbox);


        area_info areaInfo = db.getAreaInfo(cAreaID);

        TextView phonebookheader = (TextView) rootView.findViewById(R.id.phonebookheader);
        ImageButton phonebookvoicesearch = (ImageButton) rootView.findViewById(R.id.phonebookvoicesearch);
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        searcheditbox.setTypeface(tf);
        phonebookheader.setTypeface(tf);
        int infoCode=0;

        try {
            infoCode = Integer.parseInt(areaInfo.getArea_details());
        }catch (Exception e)
        {}



        areas = db.getAreaCode(cAreaID);
        //mainListView = (ListView) rootView.findViewById(R.id.areaList);

        //System.out.println("cata:"+infoCode);
        String pAreaName="";
        if(db.getAreaName(cAreaID) == "" || db.getAreaName(cAreaID) == null)
        {
            phonebookheader.getLayoutParams().height = 50;
        }
        else if(cAreaID!=0 && (infoCode==5 || infoCode==6 || infoCode==1 || infoCode==2))
        {
            List<areaCode> parentArea = db.getAreaCodeParent(cAreaID);
            for (areaCode area : parentArea) {

                pAreaName = area.getAreaName();

                if(infoCode==5 || infoCode==6)
                {
                    list.add("বিভাগীয় দপ্তর");
                }
                else if(infoCode==1 || infoCode==2)
                {
                    list.add("জেলা দপ্তর");
                }
                id.add(area.getAreaID());
            }
        }

        if(cAreaID==617)
        {
            phonebookheader.setText("মৎস্য অধিদপ্তর");
        }
        else if(cAreaID==619)
        {
            phonebookheader.setText("মৎস্য অধিদপ্তর প্রকল্পসমূহ");
        }
        else if(cAreaID==622)
        {
            phonebookheader.setText("মৎস্য অধিদপ্তর বিভাগসমূহ");
        }
        else if(infoCode==5 || infoCode==6)
        {
            phonebookheader.setText(pAreaName+" বিভাগের দপ্তরসমূহ");
        }
        else if(infoCode==1 || infoCode==2)
        {
            phonebookheader.setText(pAreaName+" জেলার দপ্তরসমূহ");
        }
        else
        {
            phonebookheader.setText(db.getAreaName(cAreaID));
        }

        if(searcheditbox.getText().toString().length()>0)
        {
            change(searcheditbox.getText().toString(),mainListView);
        }
        else
        {

            for (areaCode area : areas) {
                list.add(area.getAreaName());
                id.add(area.getAreaID());
                totalArea.put(area.getAreaID(), db.numberOfRows(area.getAreaID()));
                totalUser.put(area.getAreaID(), db.numberOfRowsUser(area.getAreaID()));
            }
            totalUser.put(cAreaID, db.numberOfRowsUser(cAreaID));
            mainListView = (ListView) rootView.findViewById(R.id.areaList);
            MyCustomAdapter adapter = new MyCustomAdapter(list, id, getActivity(), cAreaID, totalArea, totalUser);
            mainListView.setAdapter(adapter);
        }
        searcheditbox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        phonebookvoicesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please Speak Up");
                i.putExtra(RecognizerIntent.EXTRA_RESULTS, 1);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);


                try {
                    startActivityForResult(i, 1);
                } catch (Exception a) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext);
                    dlgAlert.setMessage("");
                    dlgAlert.setTitle("আপনার ডিভাইস কণ্ঠস্বর শনাক্তকরন সমর্থন করেনা");
                    dlgAlert.setPositiveButton("বন্ধ", null);
                    dlgAlert.setCancelable(false);

                    dlgAlert.setPositiveButton("ওকে", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dlgAlert.create().show();
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
                change(text,mainListView);
            }
        });
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
