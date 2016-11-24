package com.jackzhao.www.bottomactionbar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.adapters.CompanyAdapter;
import com.jackzhao.www.bottomactionbar.utils.AppSingleton;
import com.jackzhao.www.bottomactionbar.utils.Common;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity {

    ListView lv_company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        lv_company = (ListView) findViewById(R.id.company_list);

        EditText txt_keys = (EditText) findViewById(R.id.txt_search);
        EditText txt_location = (EditText) findViewById(R.id.txt_location);

        String keys = "美食";
        String location = "pasadena";
        String longitude = "-118";
        String latitude = "34";
        String count = "0";

        String get_url = String.format(Common.WSDL_COMPANY_LIST, keys, location, longitude, latitude, count);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                get_url,
                null,
                createResponseSuccessListener(),
                createResponseErrorListener());

        request.setRetryPolicy(new DefaultRetryPolicy(
                Common.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        AppSingleton.getInstance(this).addToRequestQueue(request, "");

    }

    private Response.Listener<JSONObject> createResponseSuccessListener() {

        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                bindCompanyList(response);
            }
        };

    }

    private Response.ErrorListener createResponseErrorListener() {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

    }

    private void bindCompanyList(JSONObject response) {

        try {

            JSONArray companies = response.getJSONArray("GetSearchResultsResult");
            CompanyAdapter adapter = new CompanyAdapter(Search.this, companies);
            lv_company.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
