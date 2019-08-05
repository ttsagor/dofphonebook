package com.Pisystems.dofphonebook.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Pisystems.dofphonebook.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sagor on 3/21/2016.
 */
public class MyCustomAdapterGroupSmsArea extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    ArrayList<Integer> id = new  ArrayList<Integer>();
    int cAreaID=0;
    Map<Integer, Integer> totalArea = new HashMap<Integer, Integer>();
    Map<Integer, Integer> totalUser = new HashMap<Integer, Integer>();
    public static boolean animationFlag = true;
    public static ArrayList<Integer> checkedID = new ArrayList<Integer>();
    LayoutInflater inflater;
    String fontPath = "fonts/SolaimanLipi.ttf";
    Typeface tf;
    //change made

    public MyCustomAdapterGroupSmsArea(ArrayList<String> list, ArrayList<Integer> id ,Context context,int cAreaID,Map<Integer, Integer> totalArea,  Map<Integer, Integer> totalUser) {
        this.list = list;
        this.context = context;
        this.id = id;
        this.cAreaID = cAreaID;
        this.totalArea = totalArea;
        this.totalUser = totalUser;
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
        //checkedID = new ArrayList<Integer>();

    }
    private static class rowHolder{
        public TextView areaName;
        public CheckBox areaCheck;
        public LinearLayout listbaruser;
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
            FragmentGroupSMSAreaWise.listCountFlag++;
            view = inflater.inflate(R.layout.groupsmsarea, null);


            holder.areaName  = (TextView) view.findViewById(R.id.areaName);
            holder.areaCheck = (CheckBox) view.findViewById(R.id.areaCheck);
            holder.listbaruser = (LinearLayout) view.findViewById(R.id.groupsmsareaemaillayout);


            holder.areaName.setTypeface(tf);

            view.setTag(holder);
        }
        else
        {
            holder = (rowHolder) view.getTag();
        }
        holder.areaName.setText(list.get(position));
        //holder.areaButton.setId(id.get(position));
        final Animation animationRL = AnimationUtils.loadAnimation(context, R.anim.translate);
        final Animation animationLR = AnimationUtils.loadAnimation(context, R.anim.translatelrn);
        final Animation animationRLr = AnimationUtils.loadAnimation(context, R.anim.tanslater);
        final Animation animationLRr = AnimationUtils.loadAnimation(context, R.anim.tanslatelrr);

        /*if(animationFlag){
            holder.areaName.startAnimation(animationLR);
            animationFlag=false;
        }
        else{
            holder.areaName.startAnimation(animationRL);
            animationFlag=true;
        }*/
        if(checkedID.contains(id.get(position)))
        {
            holder.areaCheck.setChecked(true);
        }
        else
        {
            holder.areaCheck.setChecked(false);
        }
        final CheckBox cb =  holder.areaCheck;

        holder.areaCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (cb.isChecked()) {
                        if(!checkedID.contains(id.get(position)))
                        {
                            checkedID.add(id.get(position));
                        }
                    } else {
                        if(checkedID.contains(id.get(position)))
                        {
                            checkedID.remove(checkedID.indexOf(id.get(position)));
                        }
                    }
                }
                catch (Exception e){}
               /* for(int i=0;i<checkedID.size();i++) {
                   System.out.println("TOTAL:" + checkedID.size() + " THERE:" + checkedID.get(i));
                 }*/
            }
        });

        holder.listbaruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentGroupSMSAreaWise.nxtActivity(id.get(position), cAreaID, totalArea, totalUser);
            }
        });
        return view;
    }





}