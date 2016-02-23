package com.dash;

import com.dash.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashScreenActivity extends Activity {
    
	private Thread splashTread;
	protected int _splashTime = 2000; //2 SECONDS	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.splash);
                
		splashTread = new Thread()
		{
            @Override
            public void run()
            {            	
                try 
                {
                    synchronized (this)
                    {             
                    	wait(_splashTime);
                    }
            	} 
            	catch (InterruptedException e)
            	{}
            	finally
            	{       
            		//finish();
            		createActivity();
                }
                }
            };            
            splashTread.start();
		}
    
    private void createActivity()
    {    	
    	Intent deviceSearch = new Intent(this, BrowseActivity.class);
    	startActivity(deviceSearch);
    }
}

