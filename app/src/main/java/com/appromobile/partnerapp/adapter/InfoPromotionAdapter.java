package com.appromobile.partnerapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.model.other.ChildInfoPromotion;
import com.appromobile.partnerapp.model.other.GroupInfoPromotion;

import java.util.List;

/**
 * Created by Chau Huynh on 3/26/2017.
 */

public class InfoPromotionAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<GroupInfoPromotion> group;

    public InfoPromotionAdapter(Context context, List<GroupInfoPromotion> group) {
        this.context = context;
        this.group = group;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return group.get(groupPosition).childs.size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return group.get(groupPosition).childs.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_info_promotion, null);
        }

        ChildInfoPromotion childInfoPromotion = (ChildInfoPromotion) getChild(groupPosition, childPosition);

        TextView txtBill = (TextView) convertView.findViewById(R.id.textView_child_bill);
        txtBill.setText(childInfoPromotion.getDiscount() + " " + context.getString(R.string.currency));

        TextView txtTimeEvent = (TextView) convertView.findViewById(R.id.textView_child_time_event);
        txtTimeEvent.setText(formatDate(childInfoPromotion.getApplyStart()) + " - " + formatDate(childInfoPromotion.getApplyEnd()));

        TextView txtTimeUse = (TextView) convertView.findViewById(R.id.textView_child_time_use);
        txtTimeUse.setText(formatDate(childInfoPromotion.getCouponStart()) + " - " + formatDate(childInfoPromotion.getCouponEnd()));

        return convertView;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_info_promotion, null);
        }

        ExpandableListView expandableListView = (ExpandableListView) parent;
        GroupInfoPromotion groupInfoPromotion = (GroupInfoPromotion) getGroup(groupPosition);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.textView_group_title);
        if (groupInfoPromotion.getTitle().length() < 50) {
            txtTitle.setText(groupInfoPromotion.getTitle());
        } else {
            txtTitle.setText(groupInfoPromotion.getTitle().substring(0, 50));
        }
        TextView txtContent = (TextView) convertView.findViewById(R.id.textView_group_content);
        if (groupInfoPromotion.getContent().length() < 250) {
            txtContent.setText(groupInfoPromotion.getContent());
        } else {
            txtContent.setText(groupInfoPromotion.getContent().substring(0, 250));
        }

        ImageView img1 = (ImageView) convertView.findViewById(R.id.imageView_image_group);
        ImageView img2 = (ImageView) convertView.findViewById(R.id.imageView_none_group);

        if (groupInfoPromotion.getStatus() == 2) {   //expired
            if (groupInfoPromotion.isApply()) {      //hotel accepted
                img1.setImageResource(R.drawable.promotion_gray_image);
                img2.setImageResource(R.drawable.promotion_gray);
                txtTitle.setTextColor(Color.rgb(170, 170, 170));
                txtContent.setTextColor(Color.rgb(170, 170, 170));
                expandableListView.collapseGroup(groupPosition);
            } else {                                  //hotel not accepted
                img1.setImageResource(0);
                img2.setImageResource(0);
                txtTitle.setTextColor(Color.rgb(170, 170, 170));
                txtContent.setTextColor(Color.rgb(170, 170, 170));
                expandableListView.collapseGroup(groupPosition);
            }
        } else {                                     //valid
            if (groupInfoPromotion.isApply()) {      //hotel accepted
                img1.setImageResource(R.drawable.promotion_org_image);
                img2.setImageResource(R.drawable.promotion_org);
                txtTitle.setTextColor(Color.rgb(97, 97, 97));
                txtContent.setTextColor(Color.rgb(97, 97, 97));
                //expandableListView.collapseGroup(groupPosition);
            } else {                                  //hotel not accepted
                img1.setImageResource(0);
                img2.setImageResource(0);
                txtTitle.setTextColor(Color.rgb(97, 97, 97));
                txtContent.setTextColor(Color.rgb(97, 97, 97));
                //expandableListView.collapseGroup(groupPosition);
            }
        }

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private String formatDate(String date) {
        String[] d = date.split("-");
        return d[2] + "/" + d[1];
    }
}
