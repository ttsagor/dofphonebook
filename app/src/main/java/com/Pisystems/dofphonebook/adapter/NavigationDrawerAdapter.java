package com.Pisystems.dofphonebook.adapter;

/**
 * Created by Ravi on 29/07/15.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import com.Pisystems.dofphonebook.R;
import com.Pisystems.dofphonebook.model.NavDrawerItem;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
    Typeface tf;
    String fontPath = "fonts/SolaimanLipi.ttf";
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.title.setTypeface(tf);
        if(position==0)
        {
            holder.drawerimage.setImageDrawable(context.getResources().getDrawable(R.drawable.home_icon));
        }
        else if(position==1)
        {
            holder.drawerimage.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_icon));
        }
        else if(position==2)
        {
            holder.drawerimage.setImageDrawable(context.getResources().getDrawable(R.drawable.update_icon));
        }
        else if(position==3)
        {
            holder.drawerimage.setImageDrawable(context.getResources().getDrawable(R.drawable.feedback_icon));
        }
        else if(position==4)
        {
            holder.drawerimage.setImageDrawable(context.getResources().getDrawable(R.drawable.about_us_icon));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView drawerimage;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            drawerimage = (ImageView) itemView.findViewById(R.id.drawerimage);
        }
    }
}
