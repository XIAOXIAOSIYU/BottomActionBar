package com.jackzhao.www.bottomactionbar.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.adapters.CompanyAdapter;
import com.jackzhao.www.bottomactionbar.webservices.CompanyWebServices;

import org.json.JSONArray;
import org.json.JSONException;

public class Search extends AppCompatActivity {

    ListView lv_company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        lv_company = (ListView) findViewById(R.id.company_list);

        String keys = "food";
        String location = "ros";
        String latitude = "118";
        String longitude = "-34";
        String count = "1";

        new AsyncCompanies().execute(keys, location, latitude, longitude, count);
    }

    public class AsyncCompanies extends AsyncTask<String, Void, String> {

        private final ProgressDialog dialog = new ProgressDialog(Search.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog.setMessage("數據加載中......");
            //dialog.show();
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            JSONArray array = null;
            try {
                array = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CompanyAdapter adapter = new CompanyAdapter(Search.this, array);
            lv_company.setAdapter(adapter);
        }

        @Override
        protected String doInBackground(String... strings) {

            String keys = strings[0];
            String location = strings[1];
            String latitude = strings[2];
            String longitude = strings[3];
            String count = strings[4];

            CompanyWebServices webServices = new CompanyWebServices();
            String companies = webServices.GetCompanies(keys, location, latitude, longitude, count);
            return companies;

        }
    }
}
