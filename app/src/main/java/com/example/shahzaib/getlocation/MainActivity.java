package com.example.shahzaib.getlocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    LocationManager lm;
    LocationListener ll;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // now check user granted permission or not
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            // user granted the permission
            Log.i("info","User granted the permission");

            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                Log.i("info","We not have permission :( ....");
                // if permission is not granted

            }
            else
            {
                // if we have permission
                Log.i("info","We have permission :) ....");
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,ll);
            }


        }
        else
        {
            Log.i("info", "User Denied the permission");
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("info", "Location Changed.......");
                Log.i("info", location.toString());
                Log.i("info",""+location.getLatitude());
                Log.i("info",""+location.getLongitude());

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {
            Log.i("info", "Your Device is less then Marshmellow.....");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) { // if permission already granted
                Log.i("info", "Permission Already Granted");
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
            } else { // if permission is not granted
                Log.i("info", "Permission is not Granted");
            }

        }
        else // if android version is Marshmellow or greater (23 or above)
        {
            Log.i("info", "Your Device is  Marshmellow or above.....");

            // ab yahan pr check krna hy k permission already granted hy k nahi
            //      - agr granted hy to simply system sy location ki request krni hy
            //      - agr nahi granted to permission grant krny k liye msg krna hy

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i("info", "If permission is not already sgranted.....");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                // jump to the @Overriden method onRequestPermissionResult(...)

            }
            else
            {

                Log.i("info", "If permission already sgranted.....");
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
            }


        }

    }// end of OnCreate



}
