package com.revolutionuc.donut.Service;

import android.content.Context;

import com.revolutionuc.donut.Model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samridhi on 17/04/16.
 */
public class HttpService {
    public HttpService() {

    }

    public Response getTrends(URL url) throws IOException {
        InputStream inputStream = null;
        int responseCode = -1;
        JSONArray jsonResponse = null;

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            responseCode = conn.getResponseCode();
            inputStream = conn.getInputStream();


            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();


            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            JSONObject json = jsonObject.getJSONObject("projects");
            jsonResponse = json.getJSONArray("project");
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            return new Response(jsonResponse, responseCode);
        }
    }

    public Response getOffers(URL uri) {

        String str = "{\"offers\":{\n" +
                "        \"id\": 01,\n" +
                "        \"title\": \"Kroger\",\n" +
                "        \"description\":\"Get 10% off when you donate to Ecuador Earthquake Relief Fund\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 02,\n" +
                "        \"title\": \"Udf\",\n" +
                "        \"description\":\"Get a free 12oz coffee when you donate to Creating Hope International (CHI) \"\n" +
                "   \n" +
                "        },\n" +
                "{\n" +
                "        \"id\": 03,\n" +
                "        \"title\": \"Walmart\",\n" +
                "        \"description\":\"Get 5% on your next bill off when you donate for Ebola Orphans through Develop Africa, Inc. \"\n" +
                "   \n" +
                "        }\n" +
                "    }}";
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        try {
            jsonObject = new JSONObject(str);
            jsonArray = jsonObject.getJSONArray("offers");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Response(jsonArray, 200);
    }
}

