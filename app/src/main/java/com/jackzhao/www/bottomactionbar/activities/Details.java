package com.jackzhao.www.bottomactionbar.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.models.Company;
import com.jackzhao.www.bottomactionbar.utils.AppSingleton;
import com.jackzhao.www.bottomactionbar.utils.Common;
import com.jackzhao.www.bottomactionbar.webservices.CompanyWebServices;

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
                //new AsyncCallCompanyDetails().execute(company_id);
                String WSDL_URL = "http://iccyp.com/GetFirstMenu.svc/json/detail/" + company_id;
                this.volleyJsonObjectRequest(WSDL_URL);
            }
        }
    }

    public class AsyncCallCompanyDetails extends AsyncTask<Integer, Void, Company> {

        private final ProgressDialog dialog = new ProgressDialog(Details.this);

        @Override
        protected Company doInBackground(Integer... integers) {
            CompanyWebServices services = new CompanyWebServices();
            Company company = services.GetCompanyDetails(integers[0]);
            return company;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("數據加載中......");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Company company) {
            super.onPostExecute(company);

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

            dialog.dismiss();
        }
    }

    public void volleyJsonObjectRequest(String url) {

        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        final String TAG="CCYP";

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

//                        LayoutInflater li = LayoutInflater.from(Details.this);
//                        //showDialogView = li.inflate(R.layout.show_dialog, null);
//                        outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
//                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Details.this);
//                        alertDialogBuilder.setView(showDialogView);
//                        alertDialogBuilder
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                    }
//                                })
//                                .setCancelable(false)
//                                .create();
//                        outputTextView.setText(response.toString());
//                        alertDialogBuilder.show();
//                        progressDialog.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
               // progressDialog.hide();
            }
        });

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq, REQUEST_TAG);
    }
}
