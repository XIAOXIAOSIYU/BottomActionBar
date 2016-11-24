package com.jackzhao.www.bottomactionbar.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.adapters.CompanyAdapter;
import com.jackzhao.www.bottomactionbar.models.Company;
import com.jackzhao.www.bottomactionbar.utils.AppSingleton;
import com.jackzhao.www.bottomactionbar.utils.Common;
import com.jackzhao.www.bottomactionbar.webservices.CompanyWebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Details extends AppCompatActivity {

    ImageView image;
    TextView label_company_name;
    TextView label_company_english_name;
    TextView label_company_tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_company_details);
        setSupportActionBar(toolbar);

        image = (ImageView) findViewById(R.id.img_company_details_main_image);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        label_company_name = (TextView) findViewById(R.id.lb_company_name);
        label_company_english_name = (TextView) findViewById(R.id.lb_company_english_name);
        label_company_tags = (TextView) findViewById(R.id.lb_company_tags);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            if (data.containsKey(Common.BOUNDLE_COMPANY_ID)) {
                int company_id = data.getInt(Common.BOUNDLE_COMPANY_ID);
                String get_url = String.format(Common.WSDL_COMPANY_DETAILS, company_id);

                Log.i("WSDL_URL", get_url);

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
        }

    }

    private Response.ErrorListener createResponseErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
    }

    private Response.Listener<JSONObject> createResponseSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                bindCompanyDetails(response);
            }
        };
    }

    private void bindCompanyDetails(JSONObject _response) {

        try {

            JSONArray company_response = _response.getJSONArray("GetDetailListResult");
            JSONObject response = company_response.getJSONObject(0);

            Company company = new Company(response);

            String cname = company.getChineseName();
            String ename = company.getEnglishName();
            String tags = company.getTags();

            if (ename == "null" || ename.length() == 0) {
                ename = "--";
            }

            if (cname == "null" || cname.length() == 0) {
                cname = ename;
            }

            if (tags == "null" || tags.length() == 0) {
                tags = "--";
            }

            label_company_name.setText(cname);
            label_company_english_name.setText(ename);
            label_company_tags.setText(tags);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
