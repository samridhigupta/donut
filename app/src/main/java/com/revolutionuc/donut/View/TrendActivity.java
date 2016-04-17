package com.revolutionuc.donut.View;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.revolutionuc.donut.Controller.LoadFeedData;
import com.revolutionuc.donut.Model.Trend;
import com.revolutionuc.donut.R;
import com.revolutionuc.donut.Util.NetworkUtil;

import java.util.ArrayList;

/**
 * Created by samridhi on 17/04/16.
 */
public class TrendActivity extends Activity {
    Button menu;
    Button offers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
        menu = (Button)findViewById(R.id.menu);
        offers = (Button)findViewById(R.id.offers);
    }
}