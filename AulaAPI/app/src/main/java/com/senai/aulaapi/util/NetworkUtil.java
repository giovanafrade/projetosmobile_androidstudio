package com.senai.aulaapi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;

public class NetworkUtil {

    public static boolean temInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null){
            Network network = cm.getActiveNetwork();
            return network != null;
        }

        return false;
    }
}
