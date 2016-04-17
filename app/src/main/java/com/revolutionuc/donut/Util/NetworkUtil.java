package com.revolutionuc.donut.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.revolutionuc.donut.View.HomeActivity;

/**
 * Created by samridhi on 17/04/16.
 */
public class NetworkUtil {

    private Context context;

    public NetworkUtil(Context context){
        this.context = context;
    }
    public boolean hasActiveNetwork() {
        ConnectivityManager connManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        return (activeNetwork != null) && activeNetwork.isConnected();
    }
}