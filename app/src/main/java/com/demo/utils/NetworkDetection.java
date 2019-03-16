package com.demo.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.PermissionChecker;
import android.util.Log;



public class NetworkDetection {

    private static final String TAG = NetworkDetection.class.getSimpleName();
    private static Location location;

    public boolean isMobileNetworkAvailable(Context ctx) {
        ConnectivityManager connectionManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myNetworkInfo = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        try {
            if (myNetworkInfo == null) {
                return false;
            } else {
                return myNetworkInfo.isConnected() ? true : false;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return false;
    }

    public boolean isWifiAvailable(Context ctx) {
        ConnectivityManager myConnManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return myNetworkInfo.isConnected() ? true : false;
    }


}
