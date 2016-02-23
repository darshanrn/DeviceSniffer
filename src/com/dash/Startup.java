package com.dash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Startup extends BroadcastReceiver{

	public Startup() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    	Thread discoveryThread = new Thread(DiscoveryThread.getInstance());
	    discoveryThread.start();	
    	
    }

    
}