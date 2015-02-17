package com.example.blueberry.database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class register extends Activity implements OnClickListener{
    Button btnRegister;
    Button btnLinkToLogin;
    EditText inputFullName;
    EditText inputEmail;
    EditText inputPassword;
    TextView registerErrorMsg;

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
        setContentView(R.layout.activity_register);
        //SQLiteDatabase db = new DatabaseHandlerClass(this);
       // db.onCreate(null);
        // Importing all assets like buttons, text fields
        inputFullName = (EditText) findViewById(R.id.registerName);
        inputEmail = (EditText) findViewById(R.id.registerEmail);
        inputPassword = (EditText) findViewById(R.id.registerPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        registerErrorMsg = (TextView) findViewById(R.id.register_error);
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR,email VARCHAR,password VARCHAR);");

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = inputFullName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if(inputFullName.getText().toString().trim().length()==0||
                        inputEmail.getText().toString().trim().length()==0||
                        inputPassword.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('"+inputFullName.getText()+"','"+inputEmail.getText()+
                        "','"+inputPassword.getText()+"');");
                showMessage("Success", "Record added");
                clearText();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                // Close Registration View
                finish();
            }
        });

    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        inputFullName.setText("");
        inputEmail.setText("");
        inputPassword.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    @Override
    public void onClick(View v) {
//        String name = inputFullName.getText().toString();
//        String email = inputEmail.getText().toString();
//        String password = inputPassword.getText().toString();
//        if(inputFullName.getText().toString().trim().length()==0||
//                inputEmail.getText().toString().trim().length()==0||
//                inputPassword.getText().toString().trim().length()==0)
//        {
//           // showMessage("Error", "Please enter all values");
//            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
//            return;
//        }
//        db.execSQL("INSERT INTO student VALUES('"+inputFullName.getText()+"','"+inputEmail.getText()+
//                "','"+inputPassword.getText()+"');");
//      //  showMessage("Success", "Record added");
//        Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_LONG).show();
        //clearText();
    }
}
