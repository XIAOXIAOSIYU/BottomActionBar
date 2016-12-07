package com.jackzhao.www.bottomactionbar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

public class UserManage {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    // Shared preference mode
    final int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "USER_LOGIN";
    public static final String PREF_USER_IS_LOGIN = "USER_LOGGED";
    public static final String PREF_USER_ID ="USER_ID";
    public static final String PREF_USER_NAME = "USER_NAME";
    public static final String PREF_USER_EMAIL = "USER_EMAIL";

    public UserManage(Context _context){
        this.context = _context;
        preferences = this.context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void generateLoginSession(String email,String password){

        String get_url = String.format(Common.WSDL_USER_LOGIN, email, password);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                get_url,
                null,
                createResponseSuccessListener(),
                createResponseErrorListener());

        VolleyLog.DEBUG = true;
        AppSingleton.getInstance(context).addToRequestQueue(request, "");

    }

    private Response.ErrorListener createResponseErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Common.CommonToast(context,error.getMessage());
            }
        };
    }

    private Response.Listener<JSONObject> createResponseSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BindUserDetails(response);
            }
        };
    }

    private void BindUserDetails(JSONObject response){
        //UserManage user = new UserManage();
        //return user;

        editor.putBoolean(PREF_USER_IS_LOGIN,true);
        editor.putString(PREF_USER_NAME,"");
        editor.putString(PREF_USER_EMAIL,"");
        editor.commit();
    }

}
