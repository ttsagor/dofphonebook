package com.Pisystems.dofphonebook.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.Pisystems.dofphonebook.R;
import com.Pisystems.dofphonebook.adapter.NavigationDrawerAdapter;
import com.Pisystems.dofphonebook.model.NavDrawerItem;

public class FragmentDrawer extends Fragment {

    private static String TAG = FragmentDrawer.class.getSimpleName();

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private static String[] titles = null;
    private FragmentDrawerListener drawerListener;

    public FragmentDrawer() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();


        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
        }
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        TextView name = (TextView) layout.findViewById(R.id.drawerName);
        TextView designation = (TextView) layout.findViewById(R.id.drawerdesign);
        TextView singleuserofficename = (TextView) layout.findViewById(R.id.drawerOffice);
        TextView areaname = (TextView) layout.findViewById(R.id.drawerAddress);

        TextView drawerimp = (TextView) layout.findViewById(R.id.drawerimp);
        TextView drawerfid = (TextView) layout.findViewById(R.id.drawerfid);



        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        name.setTypeface(tf);
        designation.setTypeface(tf);
        singleuserofficename.setTypeface(tf);
        areaname.setTypeface(tf);
        drawerimp.setTypeface(tf);
        drawerfid.setTypeface(tf);

        MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);
        SystemInfo cSystemInfo = db.getSystemInfo();
        List<User> users = db.getAreaUserByID(cSystemInfo.getUserID());
        String tArea="";
        int area_id=0;
        int parent_id=0;
        for (User cUser : users) {
            name.setText(cUser.getUser_name());
            designation.setText(cUser.getDesignation());
            areaname.setText(db.getAreaName(cUser.getArea_id()));
            parent_id=db.getParentID(cUser.getArea_id());
            tArea=db.getAreaName(cUser.getArea_id());
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
        }
        else if(infoCode==1)
        {
            singleuserofficename.setText("জেলা মৎস্য কর্মকর্তা দপ্তর");
            areaname.setText(tArea);
        }
        else if(infoCode==2)
        {
            singleuserofficename.setText("জেলা মৎস্য কর্মকর্তা দপ্তর");
            areaname.setText(areaInfo.getAddress());
        }
        else if(infoCode==3)
        {
            singleuserofficename.setText("উপজেলা মৎস্য কর্মকর্তা দপ্তর");
            areaname.setText(tArea+","+db.getAreaName(parent_id));
        }
        else if(infoCode==4)
        {
            singleuserofficename.setText("উপজেলা মৎস্য কর্মকর্তা দপ্তর");
            areaname.setText(areaInfo.getAddress());
        }
        else if(infoCode==5)
        {
            singleuserofficename.setText("বিভাগীয় মৎস্য কর্মকর্তা দপ্তর");
            areaname.setText(tArea);
        }
        else if(infoCode==6)
        {
            singleuserofficename.setText("বিভাগীয় মৎস্য কর্মকর্তা দপ্তর");
            areaname.setText(areaInfo.getAddress());
            //phonebookheader.setText(pAreaName+" জেলার দপ্তরসমূহ");
        }
        else if(infoCode==7)
        {
            singleuserofficename.setText(tArea);
            areaname.setText("মৎস্য অধিদপ্তর,মৎস্য ভবন,ঢাকা ");
            //phonebookheader.setText(pAreaName+" জেলার দপ্তরসমূহ");
        }
        else if(infoCode==8)
        {
            singleuserofficename.setText(tArea);
            areaname.setText(areaInfo.getAddress());
            //phonebookheader.setText(pAreaName+" জেলার দপ্তরসমূহ");
        }
        else
        {
            // singleuserofficename.setText("মৎস্য কর্মকর্তা দপ্তর");
            singleuserofficename.setText("");
            areaname.setText(tArea);
        }

        adapter = new NavigationDrawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return layout;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                try {
                    InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    getActivity().invalidateOptionsMenu();
                } catch (Exception e) {

                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_name, getActivity().getTheme());
        mDrawerToggle.setHomeAsUpIndicator(drawable);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
}
