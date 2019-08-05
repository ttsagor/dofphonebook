package com.Pisystems.dofphonebook.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.Pisystems.dofphonebook.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sagor on 3/21/2016.
 */
public class MyCustomAdapterNoticeList extends BaseAdapter implements ListAdapter {

    private Context context;
    private ArrayList<Integer> id = new ArrayList<Integer>();
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> date = new ArrayList<String>();
    private ArrayList<String> ref_numb = new ArrayList<String>();
    ArrayList<Integer> seen =new ArrayList<>();
    String fontPath = "fonts/SolaimanLipi.ttf";
    Typeface tf;
    public MyCustomAdapterNoticeList(Context context,ArrayList<Integer> id, ArrayList<String> title,ArrayList<String> date,ArrayList<Integer> seen, ArrayList<String> ref_numb) {
        this.id = id;
        this.context = context;
        this.title = title;
        this.date=date;
        this.seen=seen;
        this.ref_numb=ref_numb;
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
    }

    private static class rowHolder{
        public TextView listnoticetitle;
        public LinearLayout relativeLayout;
        public LinearLayout noticelistlayoutholder;
        public TextView noticelistunread;
    }
    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int pos) {
        return id.get(pos);
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.all_notice, null);
        }

        //Handle TextView and display string from your list
        holder.listnoticetitle = (TextView)view.findViewById(R.id.listnoticetitle);
        holder.relativeLayout = (LinearLayout)view.findViewById(R.id.listnoticelayout);
        holder.noticelistlayoutholder = (LinearLayout)view.findViewById(R.id.noticelistlayoutholder);
        holder.noticelistunread = (TextView)view.findViewById(R.id.noticelistunread);
        //final TextView notice_ref_number = (TextView)view.findViewById(R.id.notice_ref_number);

        holder.listnoticetitle.setTypeface(tf);
       // listnoticedate.setTypeface(tf);
      //  notice_ref_number.setTypeface(tf);

        holder.listnoticetitle.setText(title.get(position));
       // listnoticedate.setText("তারিখঃ "+date.get(position));
       // notice_ref_number.setText("স্মারক নং: "+ref_numb.get(position));
        if(seen.get(position)==0)
        {
            holder.noticelistlayoutholder.setBackgroundResource(R.drawable.listbarnotice);
            holder.noticelistunread.setVisibility(View.VISIBLE);
            holder.listnoticetitle.setTextColor(Color.parseColor("#0000FF"));
        }
        else
        {
           holder.noticelistlayoutholder.setBackgroundResource(R.drawable.listbaruser);;
           holder.noticelistunread.setVisibility(View.INVISIBLE);
           holder.listnoticetitle.setTextColor(Color.WHITE);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNotice.nxtActivity(id.get(position));

               // System.out.println("Notice:" + id.get(position));
            }
        });


        return view;
    }
}