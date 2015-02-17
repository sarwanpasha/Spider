package com.example.blueberry.database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;


public class MainActivity extends ActionBarActivity {
    Button btnLogin;
    Button btnLinkToRegister;
    EditText inputEmail;
    EditText inputPassword;
    TextView loginErrorMsg;

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Importing all assets like buttons, text fields
        inputEmail = (EditText) findViewById(R.id.loginEmail);
        inputPassword = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        loginErrorMsg = (TextView) findViewById(R.id.login_error);
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR,email VARCHAR,password VARCHAR);");
        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
               // Toast.makeText(getBaseContext(), "Pasha", Toast.LENGTH_LONG).show();
                String Spidername="spider";
                String Spiderpassword="spider";
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if(email.equals("spider")  && password.equals("spider")){
                    Toast.makeText(getBaseContext(), "Server", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),server.class);
                    startActivity(i);
                    finish();
                }
                else if(email.equals("sarwan")&& password.equals("sarwan")){
                   Toast.makeText(getBaseContext(), "User", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(i);
                    finish();
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE email='"+inputEmail.getText()+"'", null);
                Cursor d=db.rawQuery("SELECT * FROM student WHERE password='"+inputPassword.getText()+"'", null);
                if(c.getCount()==0&& d.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Email: "+c.getString(0)+"\n");
                   buffer.append("Password: "+c.getString(1)+"\n");
                }
               // showMessage("Pasha Details", buffer.toString());
                Toast.makeText(getBaseContext(), "Sign in Successfull", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(i);
                finish();
           }
        });


        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),register.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        inputEmail.setText("");
        inputPassword.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
