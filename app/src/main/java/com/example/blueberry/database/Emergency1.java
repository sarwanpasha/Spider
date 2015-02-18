package com.example.blueberry.database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Emergency1 extends ActionBarActivity {
    Button btnCurrent;
    Button btnDefault;
    Button btnOther;
    Button back;
    Double lat;
    Double lon;
    SQLiteDatabase db;
    String username;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency1);
        db=openOrCreateDatabase("Spider", Context.MODE_PRIVATE, null);
        //final GPSTracker gpsTracker=new GPSTracker(this);
       final GPSAddress gpsAddress=new GPSAddress();
        btnCurrent = (Button) findViewById(R.id.btncurrent);
        btnDefault = (Button) findViewById(R.id.btndefault);
        btnOther = (Button) findViewById(R.id.btnother);
        back = (Button) findViewById(R.id.btnback1);
        final String[] address = new String[1];
        btnCurrent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                notification();
             //Location loc = gpsTracker.getLocation();
             //  lon = loc.getLongitude();
              // lat = loc.getLatitude();
      // lat= gpsTracker.getLatitude();
//  lon= gpsTracker.getLongitude();
               // GPSAddress obj = new GPSAddress(obj.getCompleteAddressString(lat,lon));
               // address[0] =  gpsAddress.getCompleteAddressString(lat,lon);
              //  String lonn = String.valueOf(lon);
                //String latt = String.valueOf(lat);
                //Toast.makeText(getBaseContext(),address[0] , Toast.LENGTH_LONG).show();
              // Toast.makeText(getBaseContext(), latt, Toast.LENGTH_LONG).show();
               //Toast.makeText(getBaseContext(), lonn, Toast.LENGTH_LONG).show();

            }
        });

        btnDefault.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Cursor c=db.rawQuery("SELECT * FROM student WHERE email='"+username+"'", null);
               // MainActivity abc=new MainActivity();
                //username=abc.setemail();
                username="aaaa";
                Cursor defaultname=db.rawQuery("SELECT name FROM student WHERE email='" + username + "'", null);
                Cursor defaultemail=db.rawQuery("SELECT email FROM student WHERE email='" + username + "'", null);
                Cursor defaultpassword=db.rawQuery("SELECT password FROM student WHERE email='" + username + "'", null);
                Cursor defaultcity=db.rawQuery("SELECT city FROM student WHERE email='" + username + "'", null);
                Cursor defaultarea=db.rawQuery("SELECT area FROM student WHERE email='" + username + "'", null);
                Cursor defaultstreet=db.rawQuery("SELECT street FROM student WHERE email='" + username + "'", null);
                Cursor defaulthouse=db.rawQuery("SELECT house FROM student WHERE email='" + username + "'", null);
                Cursor defaultcnic=db.rawQuery("SELECT cnic FROM student WHERE email='" + username + "'", null);

                sendDefaulMessage(defaultname,defaultemail,defaultpassword,defaultcity,defaultarea,defaultstreet,defaulthouse,defaultcnic);

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
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(i);
                finish();

            }
        });


        }
    private void notification() {
      //  mp.setLooping(true);
        //mp.start();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Emergeny Message"); // Set Alert dialog title
        // here
        alert.setMessage("Is everything OK?");

        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
               // mp.stop();
                //editor.putBoolean("Accident", true).commit();
                //accident_warning.this.finish();
            }
            // End of onClick(DialogInterface dialog, int
            // whichButton)
        }); // End of alert.setPositiveButton
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                SendSMS();
               // mp.stop();
                //editor.putBoolean("Accident", true).commit();
              //  Emergency1.this.finish();
                Toast.makeText(getBaseContext(), "Your current location has been send", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
    public void sendDefaulMessage(Cursor name,Cursor email,Cursor password,Cursor city,Cursor area,Cursor street,Cursor house,Cursor cnic)
    {


            String pinpoint = "http://www.maps.google.com/maps?q=" + lat + ","
                    + lon;
            String phonenumber="03335847323";
            String messages="I love you";
        try {
            SmsManager.getDefault().sendTextMessage(String.valueOf(phonenumber),
                    null, "City = "+name + "AREA =  "+area + "Steet Number = "+street+"House Number = "+house
                            + " ", null, null);
        } catch (Exception ex) {
            Log.d("SMS Error: ", ex.getMessage().toString());
        }
        Cursor c=db.rawQuery("SELECT * FROM student WHERE email='" + username + "'", null);
        if(c.getCount()==0){
            Toast.makeText(getBaseContext(), "Username = "+ username, Toast.LENGTH_SHORT).show();
            Toast.makeText(getBaseContext(), "No record found", Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("name: " + c.getString(0) + "\n");
                buffer.append("email: " + c.getString(1) + "\n");
                buffer.append("password: " + c.getString(2) + "\n\n");
            }
            showMessage("Student Details", buffer.toString());
            Toast.makeText(getBaseContext(), "Your Detail has been sended", Toast.LENGTH_SHORT).show();
        }
    }
    public void SendSMS() {

        try {
            GPSTracker gps = new GPSTracker(Emergency1.this);
            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
               // String lonn = String.valueOf(lon);
               // String latt = String.valueOf(lat);
                String   lat = String.valueOf(latitude);
                String lon = String.valueOf(longitude);

            }
            String pinpoint = "http://www.maps.google.com/maps?q=" + lat + ","
                    + lon;
            // String address = GetAddress(lat, lon);
            String phonenumber="03335847323";
            String messages="I love you";
          //  DataInsertion obj = new DataInsertion();
          //  String[] phonenumber = obj.getphonenumbers(getApplicationContext());
           // String[] messages = obj.getmessages(getApplicationContext());
           // for (int i = 0; i <= phonenumber.length; i++)
  SmsManager.getDefault().sendTextMessage(String.valueOf(phonenumber),
          null, messages + "Im at: " + " " + pinpoint, null, null);
        } catch (Exception ex) {
            Log.d("SMS Error: ", ex.getMessage().toString());
        }
        Toast.makeText(getBaseContext(), "Lattitude = "+lat, Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(), "Lognitude = "+lon, Toast.LENGTH_SHORT).show();

    }
    public String GetAddress(String lat, String lon) {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        String ret = "";
        try {
            List<Address> addresses = geocoder.getFromLocation(
                    Double.parseDouble(lat), Double.parseDouble(lon), 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder(
                        "Address:\n");
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress
                            .append(returnedAddress.getAddressLine(i)).append(
                            "\n");
                }
                ret = strReturnedAddress.toString();
            } else {
                ret = null;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ret = null;
        }
        return ret;
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
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
