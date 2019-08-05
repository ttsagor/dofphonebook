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
        import java.util.HashSet;
        import java.util.List;
        import java.util.Map;
        import java.util.Set;


public class FragmentGroupSMSAreaWise extends Fragment {

    public static int listCountFlag;
    private static Context mContext;
    int cAreaID;
    View rootView;
    public FragmentGroupSMSAreaWise() {
        this.cAreaID=MainActivity.cAreaID;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    ListView mainListView;
    int areaSkipCode=0;
    CheckBox selectallareawise;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.groupsmsareawise, container, false);
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
    public static void nxtActivity(int pID,int cAreaID,Map<Integer, Integer> totalArea,  Map<Integer, Integer> totalUser) {


        if(cAreaID==pID){

            if(totalUser.get(pID)>1){

                Intent i = new Intent(mContext,  MainActivity.class);
                i.putExtra("area_id", pID);

                i.putExtra("page_name", "groupsmsemailfavlist");
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
                Toast.makeText(mContext.getApplicationContext(), "কোন তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();
                //  Intent i = new Intent(mContext, NoUserFound.class);
                // mContext.startActivity(i);
            }

        }
        else if(totalArea.get(pID)==0){

            if(totalUser.get(pID)>1){
                Intent i = new Intent(mContext,  MainActivity.class);
                i.putExtra("area_id", pID);
                i.putExtra("page_name", "groupsmsemailfavlist");
                mContext.startActivity(i);

            }
            else if(totalUser.get(pID)==1){
                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("page_name", "singleUser");
                i.putExtra("area_id", pID);
                mContext.startActivity(i);
            }
            else {
                Toast.makeText(mContext.getApplicationContext(), "কোন তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();
                /*Intent i = new Intent(mContext, NoUserFound.class);
                mContext.startActivity(i);*/
            }

        }

        else {

            Intent i = new Intent(mContext, MainActivity.class);
            i.putExtra("page_name", "groupsmsemailareawise");
            i.putExtra("area_id", pID);
            System.out.println(pID);
            mContext.startActivity(i);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        TextView groupsmsareawisetitle = (TextView) rootView.findViewById(R.id.groupsmsareawisetitle);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        groupsmsareawisetitle.setTypeface(tf);

        try {
            mainListView = (ListView) rootView.findViewById(R.id.groupsmsarealist);
            selectallareawise = (CheckBox) rootView.findViewById(R.id.selectallareawise);
            selectallareawise.setChecked(false);
            MyCustomAdapterGroupSmsArea.checkedID = new ArrayList<Integer>();
            Map<Integer, Integer> totalArea = new HashMap<Integer, Integer>();
            Map<Integer, Integer> totalUser = new HashMap<Integer, Integer>();
            final MyDBHandler db = new MyDBHandler(getActivity(), null, null, 1);
            FragmentGroupSmsEmail.finalRecipient = new ArrayList<String>();
            /*ArrayList<Integer> skipHeadOffice = new ArrayList<>();

            skipHeadOffice.add(616);
            skipHeadOffice.add(617);
            skipHeadOffice.add(618);
            skipHeadOffice.add(619);
            skipHeadOffice.add(620);
            skipHeadOffice.add(621);
            skipHeadOffice.add(622);
            skipHeadOffice.add(623);
            skipHeadOffice.add(624);
            skipHeadOffice.add(625);
            skipHeadOffice.add(626);
            skipHeadOffice.add(627);
            skipHeadOffice.add(628);
            skipHeadOffice.add(629);
            skipHeadOffice.add(630);
            skipHeadOffice.add(631);
            skipHeadOffice.add(632);
            skipHeadOffice.add(633);
            skipHeadOffice.add(634);
            skipHeadOffice.add(635);
            skipHeadOffice.add(636);
            skipHeadOffice.add(637);
            skipHeadOffice.add(638);
            skipHeadOffice.add(639);
            skipHeadOffice.add(640);
            skipHeadOffice.add(641);
            skipHeadOffice.add(642);
            skipHeadOffice.add(643);
            skipHeadOffice.add(644);
            skipHeadOffice.add(645);
            skipHeadOffice.add(646);
            skipHeadOffice.add(647);
            skipHeadOffice.add(648);
            skipHeadOffice.add(649);
            skipHeadOffice.add(650);
            skipHeadOffice.add(651);
            skipHeadOffice.add(652);
            skipHeadOffice.add(653);
            skipHeadOffice.add(654);
            skipHeadOffice.add(655);
            skipHeadOffice.add(656);
            skipHeadOffice.add(657);
            skipHeadOffice.add(658);
            skipHeadOffice.add(659);
            skipHeadOffice.add(660);
            skipHeadOffice.add(661);
            skipHeadOffice.add(662);
            skipHeadOffice.add(663);
            skipHeadOffice.add(664);
            skipHeadOffice.add(665);
            skipHeadOffice.add(666);
            skipHeadOffice.add(669);
            skipHeadOffice.add(670);
            skipHeadOffice.add(671);
            skipHeadOffice.add(673);
            skipHeadOffice.add(674);
            skipHeadOffice.add(675);
            skipHeadOffice.add(676);
            skipHeadOffice.add(678);
            skipHeadOffice.add(679);
            skipHeadOffice.add(680);
            skipHeadOffice.add(681);
            skipHeadOffice.add(682);
            skipHeadOffice.add(683);
            skipHeadOffice.add(685);
            skipHeadOffice.add(684);*/


            List<areaCode> areas = db.getAreaCode(cAreaID);


            ArrayList<String> list = new ArrayList<String>();
            ArrayList<Integer> id = new ArrayList<Integer>();

            int infoCode = 0;
            area_info areaInfo = db.getAreaInfo(cAreaID);
            try {
                infoCode = Integer.parseInt(areaInfo.getArea_details());
            } catch (Exception e) {
            }

            if (cAreaID != 0 && (infoCode==5 || infoCode==6 || infoCode==1 || infoCode==2)) {
                List<areaCode> parentArea = db.getAreaCodeParent(cAreaID);
                for (areaCode area : parentArea) {

                    if (infoCode == 5 || infoCode == 6) {
                        list.add(area.getAreaName() + " বিভাগীয় দপ্তর");

                    } else if (infoCode == 1 || infoCode == 2) {
                        list.add(area.getAreaName() + " জেলা দপ্তর");
                    }

                    //list.add(area.getAreaName()+" কার্যালয়");
                    areaSkipCode = area.getAreaID();
                    id.add(area.getAreaID());
                }
            }

            for (areaCode area : areas) {
                list.add(area.getAreaName());
                id.add(area.getAreaID());
                totalArea.put(area.getAreaID(), db.numberOfRows(area.getAreaID()));
                totalUser.put(area.getAreaID(), db.numberOfRowsUser(area.getAreaID()));
                //db.getAreaCodeRec(area.getAreaID());
                // System.out.println();

            }
            totalUser.put(cAreaID, db.numberOfRowsUser(cAreaID));
            MyCustomAdapterGroupSmsArea adapter = new MyCustomAdapterGroupSmsArea(list, id, getActivity(), cAreaID, totalArea, totalUser);
            mainListView.setAdapter(adapter);
            ImageView groupsmsareasms = (ImageView) rootView.findViewById(R.id.groupsmsareasms);
            ImageView groupsmsareaemail = (ImageView) rootView.findViewById(R.id.groupsmsareaemail);


            groupsmsareasms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (MyCustomAdapterGroupSmsArea.checkedID.size() > 0) {
                        ArrayList<String> phoneNumber = new ArrayList<String>();
                        ArrayList<Integer> area = new ArrayList<Integer>();
                        for (int i = 0; i < MyCustomAdapterGroupSmsArea.checkedID.size(); i++) {
                            if (areaSkipCode == MyCustomAdapterGroupSmsArea.checkedID.get(i)) {
                                area.add(areaSkipCode);
                            } else {
                                try {
                                    area.addAll(db.getAreaCodeRec(MyCustomAdapterGroupSmsArea.checkedID.get(i)));
                                } catch (Exception e) {
                                }
                            }

                        }


                        // add elements to al, including duplicates
                        Set<Integer> hs = new HashSet<>();
                        hs.addAll(area);
                        area.clear();
                        area.addAll(hs);

                        phoneNumber.addAll(db.getUserNumberByArea(area));
                        FragmentGroupSmsEmail.finalRecipient = phoneNumber;

                        if (phoneNumber.size() > 0) {

                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.putExtra("page_name", "groupsmsnewmessage");
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "আপনার কাংখিত এলাকা/এলাকাসমূহ কোন তথ্য উপস্থিত নাই", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "অনুগ্রহক পূর্বক কোন  এলাকা/এলাকাসমূহ সিলেক্ট করুন", Toast.LENGTH_LONG).show();
                    }
                }
            });

            groupsmsareaemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MyCustomAdapterGroupSmsArea.checkedID.size() > 0) {
                        ArrayList<Integer> area = new ArrayList<Integer>();
                        ArrayList<String> emailList = new ArrayList<String>();
                        for (int i = 0; i < MyCustomAdapterGroupSmsArea.checkedID.size(); i++) {
                            if (areaSkipCode == MyCustomAdapterGroupSmsArea.checkedID.get(i)) {
                                area.add(areaSkipCode);
                            } else {
                                area.addAll(db.getAreaCodeRec(MyCustomAdapterGroupSmsArea.checkedID.get(i)));
                            }
                        }

                        Set<Integer> hs = new HashSet<>();
                        hs.addAll(area);
                        area.clear();
                        area.addAll(hs);

                        emailList.addAll(db.getUserEmailByArea(area));


                        if (emailList.size() > 0) {
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
                            Toast.makeText(getActivity().getApplicationContext(), "আপনার কাংখিত এলাকা/এলাকাসমূহ কোন তথ্য উপস্থিত নাই", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "অনুগ্রহক পূর্বক কোন  এলাকা/এলাকাসমূহ সিলেক্ট করুন", Toast.LENGTH_LONG).show();
                    }
                }
            });


            selectallareawise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectallareawise.isChecked()) {
                        MyCustomAdapterGroupSmsArea.checkedID = new ArrayList<Integer>();
                        Map<Integer, Integer> totalArea = new HashMap<Integer, Integer>();
                        Map<Integer, Integer> totalUser = new HashMap<Integer, Integer>();
                        final MyDBHandler db = new MyDBHandler(getActivity(), null, null, 1);

                        /*ArrayList<Integer> skipHeadOffice = new ArrayList<>();

                        skipHeadOffice.add(616);
                        skipHeadOffice.add(617);
                        skipHeadOffice.add(618);
                        skipHeadOffice.add(619);
                        skipHeadOffice.add(620);
                        skipHeadOffice.add(621);
                        skipHeadOffice.add(622);
                        skipHeadOffice.add(623);
                        skipHeadOffice.add(624);
                        skipHeadOffice.add(625);
                        skipHeadOffice.add(626);
                        skipHeadOffice.add(627);
                        skipHeadOffice.add(628);
                        skipHeadOffice.add(629);
                        skipHeadOffice.add(630);
                        skipHeadOffice.add(631);
                        skipHeadOffice.add(632);
                        skipHeadOffice.add(633);
                        skipHeadOffice.add(634);
                        skipHeadOffice.add(635);
                        skipHeadOffice.add(636);
                        skipHeadOffice.add(637);
                        skipHeadOffice.add(638);
                        skipHeadOffice.add(639);
                        skipHeadOffice.add(640);
                        skipHeadOffice.add(641);
                        skipHeadOffice.add(642);
                        skipHeadOffice.add(643);
                        skipHeadOffice.add(644);
                        skipHeadOffice.add(645);
                        skipHeadOffice.add(646);
                        skipHeadOffice.add(647);
                        skipHeadOffice.add(648);
                        skipHeadOffice.add(649);
                        skipHeadOffice.add(650);
                        skipHeadOffice.add(651);
                        skipHeadOffice.add(652);
                        skipHeadOffice.add(653);
                        skipHeadOffice.add(654);
                        skipHeadOffice.add(655);
                        skipHeadOffice.add(656);
                        skipHeadOffice.add(657);
                        skipHeadOffice.add(658);
                        skipHeadOffice.add(659);
                        skipHeadOffice.add(660);
                        skipHeadOffice.add(661);
                        skipHeadOffice.add(662);
                        skipHeadOffice.add(663);
                        skipHeadOffice.add(664);
                        skipHeadOffice.add(665);
                        skipHeadOffice.add(666);
                        skipHeadOffice.add(669);
                        skipHeadOffice.add(670);
                        skipHeadOffice.add(671);
                        skipHeadOffice.add(673);
                        skipHeadOffice.add(674);
                        skipHeadOffice.add(675);
                        skipHeadOffice.add(676);
                        skipHeadOffice.add(678);
                        skipHeadOffice.add(679);
                        skipHeadOffice.add(680);
                        skipHeadOffice.add(681);
                        skipHeadOffice.add(682);
                        skipHeadOffice.add(683);
                        skipHeadOffice.add(685);
                        skipHeadOffice.add(684);*/


                        List<areaCode> areas = db.getAreaCode(cAreaID);
                        ArrayList<String> list = new ArrayList<String>();
                        ArrayList<Integer> id = new ArrayList<Integer>();
                        int infoCode = 0;
                        area_info areaInfo = db.getAreaInfo(cAreaID);
                        try {
                            infoCode = Integer.parseInt(areaInfo.getArea_details());
                        } catch (Exception e) {
                        }

                        if (cAreaID != 0 && (infoCode==5 || infoCode==6 || infoCode==1 || infoCode==2)) {
                            List<areaCode> parentArea = db.getAreaCodeParent(cAreaID);
                            for (areaCode area : parentArea) {

                                if (infoCode == 5 || infoCode == 6) {
                                    list.add(area.getAreaName() + " বিভাগীয় দপ্তর");

                                } else if (infoCode == 1 || infoCode == 2) {
                                    list.add(area.getAreaName() + " জেলা দপ্তর");
                                }
                                MyCustomAdapterGroupSmsArea.checkedID.add(area.getAreaID());
                                areaSkipCode = area.getAreaID();
                                id.add(area.getAreaID());
                            }
                        }

                        for (areaCode area : areas) {
                            list.add(area.getAreaName());
                            id.add(area.getAreaID());
                            totalArea.put(area.getAreaID(), db.numberOfRows(area.getAreaID()));
                            totalUser.put(area.getAreaID(), db.numberOfRowsUser(area.getAreaID()));
                            MyCustomAdapterGroupSmsArea.checkedID.add(area.getAreaID());
                            //db.getAreaCodeRec(area.getAreaID());
                            // System.out.println();

                        }
                        totalUser.put(cAreaID, db.numberOfRowsUser(cAreaID));
                        MyCustomAdapterGroupSmsArea adapter = new MyCustomAdapterGroupSmsArea(list, id, getActivity(), cAreaID, totalArea, totalUser);
                        mainListView.setAdapter(adapter);
                    } else {
                        MyCustomAdapterGroupSmsArea.checkedID = new ArrayList<Integer>();
                        Map<Integer, Integer> totalArea = new HashMap<Integer, Integer>();
                        Map<Integer, Integer> totalUser = new HashMap<Integer, Integer>();
                        final MyDBHandler db = new MyDBHandler(getActivity(), null, null, 1);

                        //ArrayList<Integer> skipHeadOffice = new ArrayList<>();

                        /*skipHeadOffice.add(616);
                        skipHeadOffice.add(617);
                        skipHeadOffice.add(618);
                        skipHeadOffice.add(619);
                        skipHeadOffice.add(620);
                        skipHeadOffice.add(621);
                        skipHeadOffice.add(622);
                        skipHeadOffice.add(623);
                        skipHeadOffice.add(624);
                        skipHeadOffice.add(625);
                        skipHeadOffice.add(626);
                        skipHeadOffice.add(627);
                        skipHeadOffice.add(628);
                        skipHeadOffice.add(629);
                        skipHeadOffice.add(630);
                        skipHeadOffice.add(631);
                        skipHeadOffice.add(632);
                        skipHeadOffice.add(633);
                        skipHeadOffice.add(634);
                        skipHeadOffice.add(635);
                        skipHeadOffice.add(636);
                        skipHeadOffice.add(637);
                        skipHeadOffice.add(638);
                        skipHeadOffice.add(639);
                        skipHeadOffice.add(640);
                        skipHeadOffice.add(641);
                        skipHeadOffice.add(642);
                        skipHeadOffice.add(643);
                        skipHeadOffice.add(644);
                        skipHeadOffice.add(645);
                        skipHeadOffice.add(646);
                        skipHeadOffice.add(647);
                        skipHeadOffice.add(648);
                        skipHeadOffice.add(649);
                        skipHeadOffice.add(650);
                        skipHeadOffice.add(651);
                        skipHeadOffice.add(652);
                        skipHeadOffice.add(653);
                        skipHeadOffice.add(654);
                        skipHeadOffice.add(655);
                        skipHeadOffice.add(656);
                        skipHeadOffice.add(657);
                        skipHeadOffice.add(658);
                        skipHeadOffice.add(659);
                        skipHeadOffice.add(660);
                        skipHeadOffice.add(661);
                        skipHeadOffice.add(662);
                        skipHeadOffice.add(663);
                        skipHeadOffice.add(664);
                        skipHeadOffice.add(665);
                        skipHeadOffice.add(666);
                        skipHeadOffice.add(669);
                        skipHeadOffice.add(670);
                        skipHeadOffice.add(671);
                        skipHeadOffice.add(673);
                        skipHeadOffice.add(674);
                        skipHeadOffice.add(675);
                        skipHeadOffice.add(676);
                        skipHeadOffice.add(678);
                        skipHeadOffice.add(679);
                        skipHeadOffice.add(680);
                        skipHeadOffice.add(681);
                        skipHeadOffice.add(682);
                        skipHeadOffice.add(683);
                        skipHeadOffice.add(685);
                        skipHeadOffice.add(684);*/


                        List<areaCode> areas = db.getAreaCode(cAreaID);
                        ArrayList<String> list = new ArrayList<String>();
                        ArrayList<Integer> id = new ArrayList<Integer>();
                        int infoCode = 0;
                        area_info areaInfo = db.getAreaInfo(cAreaID);
                        try {
                            infoCode = Integer.parseInt(areaInfo.getArea_details());
                        } catch (Exception e) {
                        }

                        if (cAreaID != 0 && (infoCode==5 || infoCode==6 || infoCode==1 || infoCode==2)) {
                            List<areaCode> parentArea = db.getAreaCodeParent(cAreaID);
                            for (areaCode area : parentArea) {

                                if (infoCode == 5 || infoCode == 6) {
                                    list.add(area.getAreaName() + " বিভাগীয় দপ্তর");

                                } else if (infoCode == 1 || infoCode == 2) {
                                    list.add(area.getAreaName() + " জেলা দপ্তর");
                                }

                                areaSkipCode = area.getAreaID();
                                id.add(area.getAreaID());
                            }
                        }

                        for (areaCode area : areas) {
                            list.add(area.getAreaName());
                            id.add(area.getAreaID());
                            totalArea.put(area.getAreaID(), db.numberOfRows(area.getAreaID()));
                            totalUser.put(area.getAreaID(), db.numberOfRowsUser(area.getAreaID()));
                            //db.getAreaCodeRec(area.getAreaID());
                            // System.out.println();

                        }
                        totalUser.put(cAreaID, db.numberOfRowsUser(cAreaID));
                        MyCustomAdapterGroupSmsArea adapter = new MyCustomAdapterGroupSmsArea(list, id, getActivity(), cAreaID, totalArea, totalUser);
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
