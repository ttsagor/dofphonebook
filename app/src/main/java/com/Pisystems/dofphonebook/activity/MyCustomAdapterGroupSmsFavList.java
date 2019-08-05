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
public class MyCustomAdapterGroupSmsFavList extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private ArrayList<String> desgination = new ArrayList<String>();
    private ArrayList<Integer> id = new ArrayList<Integer>();
    public static ArrayList<Integer> unCheckedID = new ArrayList<Integer>();
    public static ArrayList<Integer> checkedID = new ArrayList<Integer>();
    LayoutInflater inflater;
    String fontPath = "fonts/SolaimanLipi.ttf";
    Typeface tf;

    public MyCustomAdapterGroupSmsFavList(Context context,ArrayList<String> list, ArrayList<String> desgination,ArrayList<Integer> id) {
        this.list = list;
        this.context = context;
        this.desgination = desgination;
        this.id=id;
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
        checkedID=id;
    }
    private static class rowHolder{
        public TextView user_name;
        public TextView user_deg;
        public CheckBox  user_chck;
        public LinearLayout groupsmsemailfavlistlaoyout;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
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
            view = inflater.inflate(R.layout.user_group_fav, null);

            holder.user_name = (TextView)view.findViewById(R.id.groupUserName);
            holder.user_deg = (TextView)view.findViewById(R.id.groupUserDeg);
            holder.user_chck = (CheckBox)view.findViewById(R.id.groupsmsfavcheckBox);
            holder.groupsmsemailfavlistlaoyout = (LinearLayout)view.findViewById(R.id.groupsmsemailfavlistlaoyout);

            holder.user_name.setTypeface(tf);
            holder.user_deg.setTypeface(tf);

            view.setTag(holder);
        }
        else
        {
            holder = (rowHolder) view.getTag();
        }

        //Handle TextView and display string from your list

       // ImageButton user_detail =(ImageButton)view.findViewById(R.id.multiUserdetail);

        holder.user_name.setText(list.get(position));
        holder.user_deg.setText(desgination.get(position));

        if(unCheckedID.contains(id.get(position)))
        {
            holder.user_chck.setChecked(false);
        }
        else
        {
            holder.user_chck.setChecked(true);
        }
        //System.out.println("USER:" + id.get(position));
        final CheckBox cb =  holder.user_chck;
        holder.user_chck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (cb.isChecked()) {
                        if (unCheckedID.contains(id.get(position))) {
                            unCheckedID.remove(unCheckedID.indexOf(id.get(position)));
                        }
                    } else {
                        //System.out.println("UNCHECKED:" + id.get(position) + " " + list.get(position));
                        if (!unCheckedID.contains(id.get(position))) {
                            unCheckedID.add(id.get(position));
                        }
                        //checkedID.remove((Integer) id.get(position));
                    }
                }
                catch (Exception e){}
                //System.out.println("TOTAL:" + checkedID.size() + " Removed:" + checkedID.indexOf(id.get(position)));
                //for(int i=0;i<unCheckedID.size();i++) {
                 //   System.out.println("TOTAL:" + unCheckedID.size() + " THERE:" + unCheckedID.get(i));
               // }
                //user_list.nxtActivity(id.get(position));
            }
        });

        holder.groupsmsemailfavlistlaoyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 FragmentGroupSmsEmailFavList.nxtActivity(id.get(position));
            }
        });

        return view;
    }
}