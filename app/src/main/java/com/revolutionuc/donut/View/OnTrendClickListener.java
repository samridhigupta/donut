package com.revolutionuc.donut.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.revolutionuc.donut.Model.Trend;
import com.revolutionuc.donut.R;
import com.revolutionuc.donut.Util.NetworkUtil;

/**
 * Created by samridhi on 17/04/16.
 */
public class OnTrendClickListener{
    private ListView lv;
    private Context context;
    private HomeActivity activity;

    public OnTrendClickListener(final Context context, final HomeActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    public AdapterView.OnItemClickListener getListener(final LayoutInflater layoutInflater) {
        final OnTrendClickListener thisObject = this;
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trend issue = (Trend) parent.getAdapter().getItem(position);


            }
        };
    }
}