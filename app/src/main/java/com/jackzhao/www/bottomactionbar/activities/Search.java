package com.jackzhao.www.bottomactionbar.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.adapters.CompanyAdapter;
import com.jackzhao.www.bottomactionbar.utils.AppSingleton;
import com.jackzhao.www.bottomactionbar.utils.Common;
import com.jackzhao.www.bottomactionbar.utils.CommonVolley;
import com.jackzhao.www.bottomactionbar.webservices.CompanyWebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity {

    final String TAG = "CCYP";
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

        RequestQueue queue = CommonVolley.getRequestQueue(Search.this);
        String get_url = String.format(Common.WSDL_COMPANY_LIST, keys, location, longitude, latitude, count);

        Log.i(TAG, get_url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                get_url,
                null,
                createResponseSuccessListener(),
                createResponseErrorListener());

        //queue.add(request);

        request.setRetryPolicy(new DefaultRetryPolicy(
                Common.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        AppSingleton.getInstance(this).addToRequestQueue(request, "");

        //new AsyncCompanies().execute(keys, location, longitude, latitude, count);
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

    public class AsyncCompanies extends AsyncTask<String, Void, JSONArray> {

        private final ProgressDialog dialog = new ProgressDialog(Search.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("數據加載中......");
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray response) {
            super.onPostExecute(response);

            CompanyAdapter adapter = new CompanyAdapter(Search.this, response);
            adapter.notifyDataSetChanged();
            lv_company.setAdapter(adapter);

            dialog.dismiss();
        }

        @Override
        protected JSONArray doInBackground(String... strings) {

            String keys = strings[0];
            String location = strings[1];
            String longitude = strings[2];
            String latitude = strings[3];
            String count = strings[4];

            CompanyWebServices webServices = new CompanyWebServices();
            JSONArray companies = webServices.GetCompanies(keys, location, longitude, latitude, count);
            return companies;

        }
    }
}
