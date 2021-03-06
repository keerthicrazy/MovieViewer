package com.contus.keerthi.movieviewer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 8/2/17.
 */

public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

    Context context;

    public CustomListViewAdapter(Context context, int resourceId, List<RowItem> items)
    {
        super(context,resourceId,items);
        this.context =context;
    }

    private class ViewHolder {

        TextView txtTitle,txtDesc;
    }

    public View getView(int position,View convertView,ViewGroup parent)
    {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflator = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){

            convertView = mInflator.inflate(R.layout.list_items,null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView)convertView.findViewById(R.id.number);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.name);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.txtDesc.setText(rowItem.getDesc());
        holder.txtTitle.setText(rowItem.getTitle());


        return convertView;
    }
}
