package com.example.blueberry.database;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/**
 * Created by Blue Berry on 2/16/2015.
 */
public class UserFunctionsClass {
    private JSONParserClass jsonParser;

    // Testing in localhost using wamp or xampp
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    private static String loginURL = "http://10.0.2.2/ah_login_api/";
    private static String registerURL = "http://10.0.2.2/ah_login_api/";

    private static String login_tag = "login";
    private static String register_tag = "register";

    // constructor
    public UserFunctionsClass()
    {
        jsonParser = new JSONParserClass();
    }

    /**
     * function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }

    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String name, String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        // return json
        return json;
    }

    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        DatabaseHandlerClass db = new DatabaseHandlerClass(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }

    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandlerClass db = new DatabaseHandlerClass(context);
        db.resetTables();
        return true;
    }
}
