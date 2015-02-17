package com.example.blueberry.database;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class other_address extends ActionBarActivity {
    Button send;
    EditText city;
    EditText area;
    EditText street_number;
    EditText house_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_address);

        send = (Button) findViewById(R.id.btnSend);
        city = (EditText) findViewById(R.id.addressCity);
        area = (EditText) findViewById(R.id.addressArea);
        street_number = (EditText) findViewById(R.id.addressStreet);
        house_number = (EditText) findViewById(R.id.addressHouse);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String sendd = send.getText().toString();
                String areaa = area.getText().toString();
                String street_numberr = street_number.getText().toString();
                String house_numberr = house_number.getText().toString();

            }
        });

    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_other_address, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
