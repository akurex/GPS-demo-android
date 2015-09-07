package com.example.gps_demo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    float lat;
    float lng;	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /* Use the LocationManager class to obtain GPS locations */

        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        LocationListener mlocListener = new MyLocationListener();


        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        
        final Handler handler = new Handler();
        
        
        new Thread(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(10000);
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                    		    String Text = "My current location is: " +    "Latitud = " + String.valueOf(lat) +    "Longitud = " + String.valueOf(lng);
                    			
                    			Toast.makeText( getApplicationContext(), Text,Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

    }

    
    public class MyLocationListener implements LocationListener

    {

    @Override

    public void onLocationChanged(Location loc)

    {

    lat = (float) loc.getLatitude();
    lng = (float) loc.getLongitude();

    loc.getLongitude();

    String Text = "ON CHANGE My current location is: " +

    "Latitud = " + loc.getLatitude() +

    "Longitud = " + loc.getLongitude();


    Toast.makeText( getApplicationContext(),

    Text,

    Toast.LENGTH_SHORT).show();

    }


    @Override

    public void onProviderDisabled(String provider)

    {

    Toast.makeText( getApplicationContext(),

    "Gps Disabled",

    Toast.LENGTH_SHORT ).show();

    }


    @Override

    public void onProviderEnabled(String provider)

    {

    Toast.makeText( getApplicationContext(),

    "Gps Enabled",

    Toast.LENGTH_SHORT).show();

    }


    @Override

    public void onStatusChanged(String provider, int status, Bundle extras)

    {


    }

    }/* End of Class MyLocationListener */
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
