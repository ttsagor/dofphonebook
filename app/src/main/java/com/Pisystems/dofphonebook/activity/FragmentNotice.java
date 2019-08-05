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
        import android.widget.ListView;
        import android.widget.TextView;

        import com.Pisystems.dofphonebook.R;
        import java.util.ArrayList;
        import java.util.List;


public class FragmentNotice extends Fragment {

    public FragmentNotice() {
        // Required empty public constructor
    }
    private static Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();


    }
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.noticeboard, container, false);
        // Inflate the layout for this fragment
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        try {
            MyDBHandler db = new MyDBHandler(getActivity(), null, null, 1);

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


            List<Notice> notice = db.getAllNotice();
            ArrayList<Integer> id = new ArrayList<>();
            ArrayList<String> title = new ArrayList<>();
            ArrayList<String> date = new ArrayList<>();
            ArrayList<String> ref_numb = new ArrayList<>();
            ArrayList<Integer> seen = new ArrayList<>();

            TextView noticebroadhometitle = (TextView) rootView.findViewById(R.id.noticebroadhometitle);

            String fontPath = "fonts/SolaimanLipi.ttf";
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
            noticebroadhometitle.setTypeface(tf);

            for (Notice cNotice : notice) {


                String[] sep = cNotice.getNotice_date_time().split(" ");
                id.add(cNotice.getNotice_id());
                title.add(cNotice.getNotice_title());
                date.add(englishToBanglaNumber(sep[0]));
                seen.add(cNotice.getSeen_stat());
                ref_numb.add(englishToBanglaNumber(cNotice.getRef_number()));
                //System.out.println("notice:" + cNotice.getNotice_title() + " seen:" + cNotice.getSeen_stat());
            }
            ListView noticeList = (ListView) rootView.findViewById(R.id.noticeList);
            MyCustomAdapterNoticeList adapter = new MyCustomAdapterNoticeList(getActivity(), id, title, date, seen, ref_numb);
            noticeList.setAdapter(adapter);
        }catch (Exception e){}
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static void nxtActivity(int noticeID) {
        MyDBHandler db = new MyDBHandler(mContext, null, null, 1);
        db.updateNoticeSeen(noticeID);
        Intent i = new Intent(mContext, MainActivity.class);
        i.putExtra("page_name", "singlenotice");
        i.putExtra("noticeID", noticeID);
        mContext.startActivity(i);

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
}
