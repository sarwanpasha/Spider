package com.example.blueberry.database;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


public class Emergency1 extends ActionBarActivity {
    Button btnCurrent;
    Button btnDefault;
    Button btnOther;
    Double lat;
    Double lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency1);
        final GPSTracker gpsTracker=new GPSTracker(this);
       final GPSAddress gpsAddress=new GPSAddress();
        btnCurrent = (Button) findViewById(R.id.btncurrent);
        btnDefault = (Button) findViewById(R.id.btndefault);
        btnOther = (Button) findViewById(R.id.btnother);
        final String[] address = new String[1];
        btnCurrent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
       lat= gpsTracker.getLatitude();
       lon= gpsTracker.getLongitude();
               // GPSAddress obj = new GPSAddress(obj.getCompleteAddressString(lat,lon));
                address[0] =  gpsAddress.getCompleteAddressString(lat,lon);
                String lonn = String.valueOf(lon);
                String latt = String.valueOf(lat);
                //Toast.makeText(getBaseContext(),address[0] , Toast.LENGTH_LONG).show();
               Toast.makeText(getBaseContext(), latt, Toast.LENGTH_LONG).show();
               Toast.makeText(getBaseContext(), lonn, Toast.LENGTH_LONG).show();

            }
        });

        btnDefault.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        btnOther.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),other_address.class);
                startActivity(i);
                finish();
            }
        });


        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_emergency1, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
