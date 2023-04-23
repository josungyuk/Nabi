package com.seoul.ddroad.diary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seoul.ddroad.R;

import java.util.ArrayList;

public class SpinnerAdapter  extends ArrayAdapter<String> {
    Context mcontext; int mresId;  ArrayList<String>
    mlist;
    public SpinnerAdapter(Context context, int resId, ArrayList<String>
            list){
        super(context,resId,list);
        this.mcontext=context;
        this.mresId = resId;
        this.mlist=list;
    }

    public View getView(int position, View convertView, ViewGroup parent ){
        return initRow(position, convertView, parent);

    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }

    public View initRow (int position, View convertView, ViewGroup
            parent){
        ViewHolder holder ;

        LayoutInflater inflater = (LayoutInflater) mcontext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = convertView;
        // For reuse
        if (convertView == null) {
            row = inflater.inflate(mresId, null);
            holder = new ViewHolder();
            holder.textView1 = (TextView) row.findViewById(R.id.weathertext);
            holder.imageView1 = (ImageView)row.findViewById(R.id.weatherimageView);
            holder.item_holder = row.findViewById(R.id.item_holder);
            row.setTag(holder);
        }else{
            holder = (ViewHolder)row.getTag();
        }

        //xml weather_item_array 랑 갯수 맞춰야합니다
        for (int i=0; i < 6 ; i++){
            if(position == 1 ){
                holder.imageView1.setImageResource(R.drawable.circle1);
            }else if(position == 2){
                holder.imageView1.setImageResource(R.drawable.circle2);
            }else if(position == 3){
                holder.imageView1.setImageResource(R.drawable.circle3);
            }else if(position == 4){
                holder.imageView1.setImageResource(R.drawable.circle4);
            }else if(position == 5){
                holder.imageView1.setImageResource(R.drawable.circle5);
            }else if(position == 6){
                holder.imageView1.setImageResource(R.drawable.circle6);
            }else{
                holder.imageView1.setImageResource(0);
            }
        }

        holder.textView1.setText(mlist.get(position));


            if (position == 0) {
                holder.imageView1.setVisibility(View.GONE);
            } else {
                holder.imageView1.setVisibility(View.VISIBLE);
            }

        return row;
    }

    private class ViewHolder {
        TextView textView1 = null;
        ImageView imageView1 = null;
        LinearLayout item_holder = null;
    }
}
