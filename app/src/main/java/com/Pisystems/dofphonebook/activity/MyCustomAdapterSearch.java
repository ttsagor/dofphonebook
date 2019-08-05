package com.Pisystems.dofphonebook.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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
public class MyCustomAdapterSearch extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private ArrayList<String> desgination = new ArrayList<String>();
    private ArrayList<Integer> id = new ArrayList<Integer>();
    private ArrayList<String> phone1 = new ArrayList<String>();
    private ArrayList<String> phone2 = new ArrayList<String>();
    private ArrayList<String> email = new ArrayList<String>();
    private ArrayList<String> lan= new ArrayList<String>();
    private ArrayList<String> area= new ArrayList<String>();
    LayoutInflater inflater;
    public MyCustomAdapterSearch(Context context,ArrayList<String> list, ArrayList<String> desgination,ArrayList<Integer> id,ArrayList<String> phone1,ArrayList<String> phone2,ArrayList<String> email,ArrayList<String> lan,ArrayList<String> area) {
        this.list = list;
        this.context = context;
        this.desgination = desgination;
        this.id=id;
        this.phone1=phone1;
        this.phone2=phone2;
        this.email=email;
        this.lan=lan;
        this.area=area;
    }
    private static class rowHolder{
        public TextView user_name;
        public TextView user_deg;
        public TextView  phone1T;
        public TextView  phone2T;
        public TextView  emailT;
        public TextView  lanT;
        public TextView  searchUserareaT;
        public LinearLayout searchlinarlayoutholder;
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
            view = inflater.inflate(R.layout.search_user, null);

            holder.searchlinarlayoutholder = (LinearLayout)view.findViewById(R.id.searchlinarlayoutholder);
            holder.user_name = (TextView)view.findViewById(R.id.searchUserName);
            holder.user_deg = (TextView)view.findViewById(R.id.searchUserDeg);
            holder.emailT = (TextView)view.findViewById(R.id.searcheamil);
            holder.phone1T = (TextView)view.findViewById(R.id.searchphone2);
            holder.phone2T = (TextView)view.findViewById(R.id.searchphone1);
            holder.lanT = (TextView)view.findViewById(R.id.searchlan);
            holder.searchUserareaT = (TextView)view.findViewById(R.id.searchUserarea);
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
        holder.searchUserareaT.setText(area.get(position));
        /*holder.phone1T.setText(phone1.get(position));
        holder.phone2T.setText(phone2.get(position));

        holder.lanT.setText(lan.get(position));
        holder.emailT.setText(email.get(position));*/

        if(phone1.get(position).length()>0)
        {
            holder.phone1T.setText(phone1.get(position));
        }
        else
        {
            holder.phone1T.setVisibility(View.GONE);
        }
        if(phone2.get(position).length()>0)
        {
            holder.phone2T.setText(phone2.get(position));
        }
        else
        {
            holder.phone2T.setVisibility(View.GONE);
        }
        if(lan.get(position).length()>0)
        {
            holder.lanT.setText(lan.get(position));
        }
        else
        {
            holder.lanT.setVisibility(View.GONE);
        }
        if(email.get(position).length()>0)
        {
            holder.emailT.setText(email.get(position));
        }
        else
        {
            holder.emailT.setVisibility(View.GONE);
        }

        holder.searchlinarlayoutholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phonebookFragment.nxtActivity(id.get(position));
            }
        });

        return view;
    }
}