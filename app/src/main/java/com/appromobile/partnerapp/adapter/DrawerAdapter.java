package com.appromobile.partnerapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.model.other.DrawerObject;

import java.util.ArrayList;

/**
 * Created by Chau Huynh on 15/03/02017.
 */

public class DrawerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DrawerObject> drawerObjects;

    public DrawerAdapter(Context context, ArrayList<DrawerObject> drawerObjects){
        this.context = context;
        this.drawerObjects = drawerObjects;
    }

    @Override
    public int getCount() {
        return drawerObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_slide_menu, null);
        }

        //ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        //imgIcon.setImageResource(drawerObjects.get(position).getIcon());
        txtTitle.setText(drawerObjects.get(position).getTitle());

        return convertView;
    }
}
