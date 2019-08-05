package com.Pisystems.dofphonebook.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Pisystems.dofphonebook.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sagor on 3/21/2016.
 */
public class MyCustomAdapterGroupSmsDeg extends BaseAdapter implements ListAdapter {
    private ArrayList<Integer> count = new ArrayList<Integer>();
    private Context context;
    private ArrayList<String> desgination = new ArrayList<String>();
    //public static ArrayList<String> unCheckedID = new ArrayList<String>();
    public static ArrayList<String> checkedID = new ArrayList<String>();
    public static ArrayList<String> degName = new ArrayList<String>();
    LayoutInflater inflater;
    String fontPath = "fonts/SolaimanLipi.ttf";
    Typeface tf;

    public MyCustomAdapterGroupSmsDeg(Context context,ArrayList<String> desgination,ArrayList<Integer> count,ArrayList<String> degName) {
        this.count = count;
        this.context = context;
        this.desgination = desgination;
        this.degName=degName;
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
    }
    private static class rowHolder{
        public TextView user_name;
        public TextView user_deg;
        public CheckBox  user_chck;
        public LinearLayout groupsmsemaildeglayout;
    }
    @Override
    public int getCount() {
        return count.size();
    }

    @Override
    public Object getItem(int pos) {
        return count.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        rowHolder holder = new rowHolder();
        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.gruop_sms_des_list, null);

            holder.user_name = (TextView)view.findViewById(R.id.groupUserName);
            holder.user_deg = (TextView)view.findViewById(R.id.groupUserDeg);
            holder.user_chck = (CheckBox)view.findViewById(R.id.groupsmsfavcheckBox);
            holder.groupsmsemaildeglayout = (LinearLayout)view.findViewById(R.id.groupsmsemaildeglayout);
            holder.user_name.setTypeface(tf);
            holder.user_deg.setTypeface(tf);
            view.setTag(holder);
        }
        else
        {
            holder = (rowHolder) view.getTag();
        }
         holder.user_name.setText(degName.get(position));
         holder.user_deg.setText("মোট কর্মকর্তা "+String.valueOf(count.get(position)));

       // System.out.println("deg "+desgination.get(position));
        if(checkedID.contains(desgination.get(position)))
        {
            holder.user_chck.setChecked(true);
        }
        else
        {
            holder.user_chck.setChecked(false);
        }
        final CheckBox cb =  holder.user_chck;
        holder.user_chck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (cb.isChecked()) {
                        if(!checkedID.contains(desgination.get(position)))
                        {
                            checkedID.add(desgination.get(position));
                        }
                    } else {
                        if(checkedID.contains(desgination.get(position)))
                        {checkedID.remove(desgination.get(position));}
                    }
                }
                catch (Exception e)
                {}
               /* for(int i=0;i<checkedID.size();i++) {
                   System.out.println("TOTAL:" + checkedID.size() + " THERE:" + checkedID.get(i));
                 }*/
            }
        });

        holder.groupsmsemaildeglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               FragmentGroupSmsEmailDesign.nxtActivity(desgination.get(position));
            }
        });


        return view;
    }
}