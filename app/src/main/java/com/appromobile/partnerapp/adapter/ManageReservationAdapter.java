package com.appromobile.partnerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.model.api.UserBookingForm;

import java.util.List;

/**
 * Created by Chau Huynh on 3/26/2017.
 */

public class ManageReservationAdapter extends BaseAdapter {
    private Context myContext;
    private List<UserBookingForm> list;

    public ManageReservationAdapter(Context myContext, List<UserBookingForm> list) {
        this.myContext = myContext;
        this.list = list;
    }

    private static int HOURS = 1;
    private static int OVERNIHT = 2;
    //private static int DAILY = 3;

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
        private TextView txtPaid, txtNumber, txtCode, txtInfo, txtCoupon, txtRoom, txtType, txtDate, txtTime;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = view;
        ViewHolder viewHolder = new ViewHolder();

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_mange_reservation, null);
            //Init
            viewHolder.txtPaid = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_paid);
            viewHolder.txtNumber = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_number);
            viewHolder.txtCode = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_code);
            viewHolder.txtInfo = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_info);
            viewHolder.txtCoupon = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_coupon);
            viewHolder.txtRoom = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_room);
            viewHolder.txtType = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_type);
            viewHolder.txtDate = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_date);
            viewHolder.txtTime = (TextView) rowView.findViewById(R.id.textView_item_manage_reservation_time);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        if (list != null) {
            UserBookingForm userBookingForm = list.get(i);

            //Set Paid/UnPaid
            if (userBookingForm.isPrepay()) {
                viewHolder.txtPaid.setBackgroundResource(R.drawable.rounded_org);
            } else {
                viewHolder.txtPaid.setBackgroundResource(R.drawable.rounded_gray);
            }

            //Set Number
            viewHolder.txtNumber.setText(i + 1 + "");

            //set Code
            viewHolder.txtCode.setText(userBookingForm.getSn() + "");

            //Set Information
            if (userBookingForm.getMobile() != null) {
                viewHolder.txtInfo.setText(userBookingForm.getMobile());
            }

            //Set Coupon
            viewHolder.txtCoupon.setText(null);

            //Set Room
            viewHolder.txtRoom.setText(null);

            //Set Type
            //Hours
            if (userBookingForm.getType() == HOURS) {
                viewHolder.txtType.setText(myContext.getString(R.string.txt_3_1_hours));
                //Overnight
            } else if (userBookingForm.getType() == OVERNIHT) {
                viewHolder.txtType.setText(myContext.getString(R.string.txt_3_1_overnight));
            } else {
                //Daily
                viewHolder.txtType.setText(myContext.getString(R.string.txt_3_1_daily));
            }

            //Set Date
            if (userBookingForm.getCheckInDatePlan() != null) {
                viewHolder.txtDate.setText(formatDate(userBookingForm.getCheckInDatePlan()));
            }

            //Set Time
            if (userBookingForm.getStartTime() != null && userBookingForm.getEndTime() != null) {
                viewHolder.txtTime.setText(formatTime(userBookingForm.getStartTime()) + " - " + formatTime(userBookingForm.getEndTime()));
            }
        }

        return rowView;
    }

    private String formatDate(String date) {
        String[] d = date.split("-");
        return d[2] + "/" + d[1];
    }

    private String formatTime(String time) {
        String[] h = time.split(":");
        return h[0] + myContext.getString(R.string.format_hour);
    }
}
