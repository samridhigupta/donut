package com.revolutionuc.donut.Controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.revolutionuc.donut.Model.Response;
import com.revolutionuc.donut.Model.Offer;
import com.revolutionuc.donut.Service.HttpService;
import com.revolutionuc.donut.View.HomeActivity;
import com.revolutionuc.donut.View.OfferActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by samridhi on 17/04/16.
 */
public class LoadOfferData extends AsyncTask<String,Void, Response> {

    private static final String LOG_TAG = LoadFeedData.class.getSimpleName();
    private Response response;
    private final HttpService httpService;
    private Context context;
    private OfferActivity activity;

    public LoadOfferData(Context context, OfferActivity mainActivity) {
        super();
        this.context = context;
        this.activity = mainActivity;
        this.response = new Response(new JSONArray(),
                HttpURLConnection.HTTP_NO_CONTENT);
        this.httpService = new HttpService();
    }


    @Override
    protected Response doInBackground(String... params) {
        return new Response(new JSONArray(),200);

    }

    private void getDataIntoList() {
        ArrayList<Offer> offers = new ArrayList<Offer>();
        offers.add(new Offer("Kroger", "Get 10% off when you donate to Ecuador Earthquake Relief Fund"));
        offers.add(new Offer("UDF","Get a free 12oz coffee when you donate to Creating Hope International (CHI)"));
        offers.add(new Offer("Walmart","Get 5% on your next bill off when you donate for Ebola Orphans through Develop Africa, Inc."));
        offers.add(new Offer("Target","Get 10% off when you donate to Japan Earthquake Relief Fund"));
        offers.add(new Offer("JC Penny","Get 10% off when you donate to Chennai Flood Relief Fund"));
        activity.setAdaptorWith(offers);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response.getCode() == HttpURLConnection.HTTP_OK) {
            getDataIntoList();
        } else {
            if(response == null) {
                Log.d(LOG_TAG, "Response is null, check internet Connection");
                Toast.makeText(context, "Response received from api is null, check internet Connection", Toast.LENGTH_SHORT).show();
            }else{
                Log.d(LOG_TAG, "Response code "+ response.getCode());
                Toast.makeText(context, "Error in loading data Response code "+ response.getCode(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
