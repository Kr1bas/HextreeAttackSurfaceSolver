package com.sntrain.kribas.hextree1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Flag18Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        setResult(42,null,null);
        Log.v("Flag18Receiver","Flag18Receiver activated");
    }
}
