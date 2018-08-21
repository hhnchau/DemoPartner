package com.appromobile.partnerapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.utils.HelperSpinner;

import java.util.List;

/**
 * Created by Chau Huynh on 3/29/2017.
 */

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater layoutInflater;

    public SpinnerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_spinner, null);
        }
        TextView txt = (TextView) view.findViewById(R.id.item_spinner);

        txt.setText(list.get(position).toString());

        //Set Color
        HelperSpinner.setColor(txt, position);

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_spinner_list, null);
        }
        TextView txt = (TextView) view.findViewById(R.id.item_spinner_list);
        txt.setText(list.get(position).toString());

        return view;

    }
}
