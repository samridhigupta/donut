package com.revolutionuc.donut.Controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.revolutionuc.donut.Model.Response;
import com.revolutionuc.donut.Model.Trend;
import com.revolutionuc.donut.Service.HttpService;
import com.revolutionuc.donut.View.HomeActivity;

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
public class LoadFeedData extends AsyncTask<String,Void, Response> {

    private static final String LOG_TAG = LoadFeedData.class.getSimpleName();
    private Response response;
    private final HttpService httpService;
    private Context context;
    private HomeActivity activity;

    public LoadFeedData(Context context, HomeActivity mainActivity) {
        super();
        this.context = context;
        this.activity = mainActivity;
        this.response = new Response(new JSONArray(),
                HttpURLConnection.HTTP_NO_CONTENT);
        this.httpService = new HttpService();
    }


    @Override
    protected Response doInBackground(String... params) {

        String host = "https://api.globalgiving.org";
        String operation = "/api/public/projectservice/featured/projects?api_key=";
        String api_key = "0eb8e448-5bfa-4624-8466-a3597ca35c6f";
        String url = new StringBuilder().append(host).append(operation).append(api_key).toString();
        URL uri;
        try {
            uri = new URL(url);
            Log.d(LOG_TAG, "starting http request on url: " + url);
            response = httpService.getTrends(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }

    private void getDataIntoList(JSONArray jsonArrayResponse) {
        ArrayList<Trend> trends = new ArrayList<Trend>();
        for (int i = 0; i < jsonArrayResponse.length(); i++) {
            try {
                JSONObject jsonObject = jsonArrayResponse.getJSONObject(i);
                JSONObject orgJSoON = jsonObject.getJSONObject("organization");
                String title = orgJSoON.getString("name");
                String mission = orgJSoON.getString("mission");
                trends.add(new Trend(title, mission));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        activity.setAdaptorWith(trends);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response != null && response.getCode() == HttpURLConnection.HTTP_OK) {
            JSONArray jsonResponse = response.jsonResponse();
            getDataIntoList(jsonResponse);
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
