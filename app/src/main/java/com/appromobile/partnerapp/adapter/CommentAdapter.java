package com.appromobile.partnerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.model.api.UserReviewForm;
import com.appromobile.partnerapp.utils.HelperRating;

import java.util.List;

/**
 * Created by Chau Huynh on 4/4/2017.
 */

public class CommentAdapter extends BaseAdapter {
    private Context myContext;
    private List<UserReviewForm> list;

    public CommentAdapter(Context myContext, List<UserReviewForm> list) {
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
        private TextView txtUsername, txtType, txtDate, txtContent;
        private ImageView star1, star2, star3, star4, star5;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = view;
        ViewHolder viewHolder = new ViewHolder();

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_comment, null);
            //Init
            viewHolder.txtUsername = (TextView) rowView.findViewById(R.id.textView_item_comment_username);
            viewHolder.txtType = (TextView) rowView.findViewById(R.id.textView_item_comment_type);
            viewHolder.txtDate = (TextView) rowView.findViewById(R.id.textView_item_comment_time);
            viewHolder.txtContent = (TextView) rowView.findViewById(R.id.textView_item_comment_content);
            viewHolder.star1 = (ImageView) rowView.findViewById(R.id.imageView_item_comment_star_1);
            viewHolder.star2 = (ImageView) rowView.findViewById(R.id.imageView_item_comment_star_2);
            viewHolder.star3 = (ImageView) rowView.findViewById(R.id.imageView_item_comment_star_3);
            viewHolder.star4 = (ImageView) rowView.findViewById(R.id.imageView_item_comment_star_4);
            viewHolder.star5 = (ImageView) rowView.findViewById(R.id.imageView_item_comment_star_5);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.txtUsername.setText(list.get(i).getUserNickName());
        viewHolder.txtDate.setText(list.get(i).getCreateTime());
        viewHolder.txtType.setText(list.get(i).getRoomName());
        viewHolder.txtContent.setText(list.get(i).getComment());

        HelperRating.setRating(list.get(i).getMark(),
                viewHolder.star1,
                viewHolder.star2,
                viewHolder.star3,
                viewHolder.star4,
                viewHolder.star5);


        return rowView;
    }
}
