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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import com.Pisystems.dofphonebook.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sagor on 3/21/2016.
 */
public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    ArrayList<Integer> id = new  ArrayList<Integer>();
    int cAreaID=0;
    Map<Integer, Integer> totalArea = new HashMap<Integer, Integer>();;
    Map<Integer, Integer> totalUser = new HashMap<Integer, Integer>();;
    public static boolean animationFlag = true;
    LayoutInflater inflater;
    String fontPath = "fonts/SolaimanLipi.ttf";
    Typeface tf;
    //change made

    public MyCustomAdapter(ArrayList<String> list, ArrayList<Integer> id ,Context context,int cAreaID,Map<Integer, Integer> totalArea,  Map<Integer, Integer> totalUser) {
        this.list = list;
        this.context = context;
        this.id = id;
        this.cAreaID = cAreaID;
        this.totalArea = totalArea;
        this.totalUser = totalUser;
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
    }
    private static class rowHolder{
        public Button areaButton;
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

    public static List<String> splitString(String msg, int lineSize) {
        List<String> res = new ArrayList<String>();

        Pattern p = Pattern.compile("\\b.{1," + (lineSize-1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);

        while(m.find()) {
            //System.out.println(m.group().trim());   // Debug
            res.add(m.group());
        }
        return res;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        rowHolder holder = new rowHolder();
        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            phonebookFragment.listCountFlag++;
            view = inflater.inflate(R.layout.areabutton, null);


            //holder.areaButton.setTypeface(tf);
            holder.areaButton  = (Button) view.findViewById(R.id.areaButton);
            view.setTag(holder);
        }
        else
        {
            holder = (rowHolder) view.getTag();
        }

        holder.areaButton.setId(id.get(position));
        holder.areaButton.setTypeface(tf);
        final Animation animationRL = AnimationUtils.loadAnimation(context, R.anim.translate);
        final Animation animationLR = AnimationUtils.loadAnimation(context, R.anim.translatelrn);
        final Animation animationRLr = AnimationUtils.loadAnimation(context, R.anim.tanslater);
        final Animation animationLRr = AnimationUtils.loadAnimation(context, R.anim.tanslatelrr);

        if(list.get(position).length()>25)
        {
            List<String> outputList = splitString(list.get(position),25);
            String output="";
            int line=0;
            for (String  s : outputList){
                line++;
                output+=s+"\n";
             }

            holder.areaButton.setText(output);

        }
        else {
            holder.areaButton.setText(list.get(position));
        }


        if(animationFlag){
            holder.areaButton.startAnimation(animationLR);
            animationFlag=false;
        }
        else{
            holder.areaButton.startAnimation(animationRL);
            animationFlag=true;
        }

        final Button tempButton = holder.areaButton;

        holder.areaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button cButton;
                //cButton = (Button) v.findViewById(id.get(position));
                //cButton.startAnimation(animationRLr);
                for (int cID : id) {
                    try {
                        //System.out.println("id:" + cID);
                        cButton = (Button) v.findViewById(cID);
                        cButton.startAnimation(animationRLr);
                    } catch (Exception e) {
                        //System.out.println("eid:" + cID);
                    }

                }

                    cButton = (Button) v.findViewById(id.get(position));
                    cButton.startAnimation(animationRLr);
                    animationFlag=false;

                //cButton = (Button) v.findViewById(id.get(position));
                //cButton.startAnimation(animationRLr);
                animationRLr.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        tempButton.setVisibility(View.GONE);
                        phonebookFragment.nxtActivity(id.get(position), cAreaID, totalArea, totalUser);
                    }
                });

            }
        });
        return view;
    }



}