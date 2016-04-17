package com.revolutionuc.donut.View;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.revolutionuc.donut.Model.Trend;
import com.revolutionuc.donut.R;

import java.util.ArrayList;

/**
 * Created by samridhi on 17/04/16.
 */
public class TrendingListAdapter extends BaseAdapter {

    private static final int noOfChars = 140;
    private ArrayList<Trend> data;
    private static LayoutInflater inflater=null;

    public TrendingListAdapter(Activity activity, ArrayList<Trend> data) {
        this.data = data;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return (null != data ? data.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.body = (TextView) convertView.findViewById(R.id.body);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String title = data.get(position).getTitle();
        viewHolder.title.setText(title);

        String body = data.get(position).getBody();
        if(null!=body && body.length()>noOfChars)
            body =  body.substring(0, noOfChars);

        viewHolder.body.setText(body);
        return convertView;

    }

    private class ViewHolder {
        TextView title;
        TextView body;
    }
}