package com.revolutionuc.donut.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.revolutionuc.donut.Controller.LoadFeedData;
import com.revolutionuc.donut.Controller.LoadOfferData;
import com.revolutionuc.donut.Model.Offer;
import com.revolutionuc.donut.Model.Trend;
import com.revolutionuc.donut.R;
import com.revolutionuc.donut.Util.NetworkUtil;

import java.util.ArrayList;

/**
 * Created by samridhi on 17/04/16.
 */
public class OfferActivity extends Activity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
    Button menu;
    Button back;
    OfferListAdapter adapter;
    ListView listView;
    private ArrayList<Offer> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        menu = (Button)findViewById(R.id.menu);
        back = (Button)findViewById(R.id.back);
        offers = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listview_offers);
        adapter = new OfferListAdapter(this, offers);
        listView.setAdapter(adapter);

        updateList();
    }
    public void showMenu(View view) {

    }

    private void updateList() {
        LoadOfferData loadOfferData = new LoadOfferData(this,this);
        loadOfferData.execute();
    }

    public void back(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void setAdaptorWith(ArrayList<Offer> newoffers) {
        if(offers.size()!=0) offers.clear();
        offers.addAll(newoffers);
        adapter.notifyDataSetChanged();
        Log.d(LOG_TAG, "adaptor has list size" + adapter.getCount());
    }
}
