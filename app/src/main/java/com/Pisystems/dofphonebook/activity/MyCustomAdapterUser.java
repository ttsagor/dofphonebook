package com.Pisystems.dofphonebook.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.AbsListView.LayoutParams;
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
public class MyCustomAdapterUser extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private ArrayList<String> desgination = new ArrayList<String>();
    private ArrayList<Integer> id = new ArrayList<Integer>();
    private ArrayList<String> phone = new ArrayList<String>();
    String fontPath = "fonts/SolaimanLipi.ttf";
    Typeface tf;

    public MyCustomAdapterUser(Context context,ArrayList<String> list, ArrayList<String> desgination,ArrayList<Integer> id,ArrayList<String> phone) {
        this.list = list;
        this.context = context;
        this.desgination = desgination;
        this.id=id;
        this.phone = phone;
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
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
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.user_row, null);
        }

        //Handle TextView and display string from your list
        final TextView user_name = (TextView)view.findViewById(R.id.multiUserName);
        final TextView user_deg = (TextView)view.findViewById(R.id.multiUserDeg);
        ImageView user_detail =(ImageView)view.findViewById(R.id.multiUserdetail);
        ImageView multiuserphone =(ImageView)view.findViewById(R.id.multiuserphone);
        LinearLayout user_row_layout =(LinearLayout)view.findViewById(R.id.user_row_layout);
        TextView user_row_layout_text = (TextView)view.findViewById(R.id.user_row_layout_text);

        user_name.setTypeface(tf);
        user_deg.setTypeface(tf);
        user_row_layout_text.setTypeface(tf);
        if(phone.get(position).contains("01914811118"))
        {
            user_name.setText(list.get(position));
            user_deg.setText(desgination.get(position));
            user_row_layout.setVisibility(View.VISIBLE);
            user_row_layout_text.setText("বি.এম.টি.এফ প্রতিনিধি");
            //LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)user_row_layout.getLayoutParams();
           // params.setMargins(0, 50, 0, 0);
            //user_row_layout.setLayoutParams(params);
        }
        else {
            user_name.setText(list.get(position));
            user_deg.setText(desgination.get(position));
            user_row_layout.setVisibility(View.GONE);
        }

        multiuserphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phone.get(position) != "")
                {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phone.get(position)));
                    try {
                        context.startActivity(intent);
                    } catch (Exception e) {
                    }
                }
                else {
                    Toast.makeText(context, "নাম্বার উপস্থিত নাই", Toast.LENGTH_LONG).show();
                }

            }
        });

        user_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FragmentUserList.nxtActivity(id.get(position));
                }
                catch (Exception e)
                {}

            }
        });


        return view;
    }
}