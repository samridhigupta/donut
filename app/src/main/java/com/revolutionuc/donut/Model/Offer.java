package com.revolutionuc.donut.Model;

/**
 * Created by samridhi on 17/04/16.
 */
public class Offer {
    private String title;
    private String body;

    public Offer(String title, String body){

        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
