package com.appromobile.partnerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.model.api.FqaInformationForm;

import java.util.List;

/**
 * Created by Chau Huynh on 4/4/2017.
 */

public class FaqAdapter extends BaseAdapter {
    private Context myContext;
    private List<FqaInformationForm> list;

    public FaqAdapter(Context myContext, List<FqaInformationForm> list) {
        this.myContext = myContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        private TextView txtUsername, txtDate, txtQuestion, txtAnswer;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = view;
        ViewHolder viewHolder = new ViewHolder();

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_question_answer, null);
            //Init
            viewHolder.txtUsername = (TextView) rowView.findViewById(R.id.textView_item_faq_username);
            viewHolder.txtDate = (TextView) rowView.findViewById(R.id.textView_item_faq_time);
            viewHolder.txtQuestion = (TextView) rowView.findViewById(R.id.textView_item_faq_question);
            viewHolder.txtAnswer = (TextView) rowView.findViewById(R.id.textView_item_faq_answer);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.txtUsername.setText(list.get(i).getCreateStaffName());
        viewHolder.txtDate.setText(list.get(i).getCreateTime());
        viewHolder.txtQuestion.setText(myContext.getString(R.string.txt_3_1_question)+" "+list.get(i).getTitle());
        viewHolder.txtAnswer.setText(myContext.getString(R.string.txt_3_1_awnser)+" "+list.get(i).getContent());

        return rowView;
    }
}
