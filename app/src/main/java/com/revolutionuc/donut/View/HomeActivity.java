package com.revolutionuc.donut.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
 * Created by samridhi on 16/04/16.
 */
public class HomeActivity extends Activity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
    Button menu;
    Button offers;
    TrendingListAdapter adapter;
    ListView listView;
    private ArrayList<Trend> trends;
    private NetworkUtil networkUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menu = (Button)findViewById(R.id.menu);
        offers = (Button)findViewById(R.id.offers);
        trends = new ArrayList<>();
        networkUtil = new NetworkUtil(this);
        if(networkUtil.hasActiveNetwork()){
            listView = (ListView)findViewById(R.id.listview_trends);
            adapter = new TrendingListAdapter(this, trends);
            listView.setAdapter(adapter);

            updateList();
//            final HomeActivity thisActivity = this;
//            final Context context = this;
//            OnTrendClickListener onIssueClickListener = new OnTrendClickListener(context,thisActivity);
//            listView.setOnItemClickListener(onIssueClickListener.getListener(getLayoutInflater()));

        }else{
            Toast.makeText(this, "check internet Connection", Toast.LENGTH_SHORT).show();
        }
    }



    public void showMenu(View view) {

    }

    private void updateList() {
        if(networkUtil.hasActiveNetwork()){
            LoadFeedData loadFeedData = new LoadFeedData(this,this);
            loadFeedData.execute();
        }else{
            Toast.makeText(this, "check internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void showOffers(View view) {
        Intent intent = new Intent(this, OfferActivity.class);
        startActivity(intent);
    }

    public void setAdaptorWith(ArrayList<Trend> newTrends) {
        if(trends.size()!=0) trends.clear();
        trends.addAll(newTrends);
        adapter.notifyDataSetChanged();
        Log.d(LOG_TAG, "adaptor has list size" + adapter.getCount());
    }
}