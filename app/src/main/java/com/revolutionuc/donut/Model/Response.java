package com.revolutionuc.donut.Model;

import org.json.JSONArray;

/**
 * Created by samridhi on 17/04/16.
 */
public class Response {
    private JSONArray jsonResponse;
    private int code;

    public Response(JSONArray jsonResponse, int code) {

        this.jsonResponse = jsonResponse;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public JSONArray jsonResponse() {
        return jsonResponse;
    }
}