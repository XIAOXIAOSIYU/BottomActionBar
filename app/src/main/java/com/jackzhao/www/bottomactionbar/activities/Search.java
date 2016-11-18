package com.jackzhao.www.bottomactionbar.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.adapters.CompanyAdapter;
import com.jackzhao.www.bottomactionbar.webservices.CompanyWebServices;

import org.json.JSONArray;

public class Search extends AppCompatActivity {

    ListView lv_company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        lv_company = (ListView) findViewById(R.id.company_list);

        EditText txt_keys =(EditText)findViewById(R.id.txt_search);
        EditText txt_location =(EditText)findViewById(R.id.txt_location);

        String keys = "美食";
        String location = "pasadena";
        String longitude = "-118";
        String latitude = "34";
        String count = "0";

        new AsyncCompanies().execute(keys, location, longitude, latitude, count);
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
