package com.jackzhao.www.bottomactionbar.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jackzhao.www.bottomactionbar.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserManage {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    // Shared preference mode
    final int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "USER_LOGIN";
    public static final String PREF_USER_IS_LOGIN = "USER_LOGGED";
    public static final String PREF_USER_ID = "USER_ID";
    public static final String PREF_USER_NAME = "USER_NAME";
    public static final String PREF_USER_EMAIL = "USER_EMAIL";
    public static final String PREF_USER_PHOTO = "USER_PHOTO";

    private ProgressDialog dialog;

    public UserManage(Context _context) {
        this.context = _context;
        preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
        dialog = new ProgressDialog(context);

    }

    public void generateLoginSession(String email, String password) {

        dialog.setTitle("User Login");
        dialog.setMessage("We are logging you in......");
        dialog.show();

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
                dialog.dismiss();
                Common.CommonToast(context, error.getMessage());
            }
        };
    }

    private Response.Listener<JSONObject> createResponseSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BindUserDetails(response);
                dialog.dismiss();
            }
        };
    }

    private void BindUserDetails(JSONObject response) {

        try {

            String login_result = response.getString("LoginResult");

            if (login_result.equals("null")) {
                Common.CommonToast(context, "Login failed");
            } else {
                JSONArray response_array = response.getJSONArray("LoginResult");
                if (response_array.length() == 1) {

                    JSONObject login_details = (JSONObject) response_array.get(0);
                    String user_id = login_details.getString("userID");
                    editor.putBoolean(PREF_USER_IS_LOGIN, true);
                    editor.putString(PREF_USER_NAME, login_details.getString("nickname"));
                    editor.putString(PREF_USER_EMAIL, login_details.getString("email"));
                    editor.putInt(PREF_USER_ID, Integer.parseInt(user_id));
                    editor.putString(PREF_USER_PHOTO, user_id + ".jpg");
                    editor.commit();

                    Common.CommonToast(context, "Login succeed");
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean CheckUserLogged() {
        return preferences.getBoolean(PREF_USER_IS_LOGIN, false);
    }

    public User GetUserDetails() {
        User user = new User();
        user.setNickName(preferences.getString(PREF_USER_NAME, null));
        user.setUserEmail(preferences.getString(PREF_USER_EMAIL, null));
        user.setUserId(preferences.getInt(PREF_USER_ID, 0));
        user.setUserPhoto(preferences.getString(PREF_USER_PHOTO, null));
        return user;
    }

    public void SetUserLogOut() {
        editor.remove(PREF_USER_IS_LOGIN);
        editor.remove(PREF_USER_NAME);
        editor.remove(PREF_USER_EMAIL);
        editor.remove(PREF_USER_ID);
        editor.remove(PREF_USER_PHOTO);
        editor.commit();
    }

}
