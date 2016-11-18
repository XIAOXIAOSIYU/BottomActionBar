package com.jackzhao.www.bottomactionbar.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.models.Company;
import com.jackzhao.www.bottomactionbar.webservices.CompanyWebServices;

public class Details extends AppCompatActivity {

    TextView label_company_name;
    ImageView image;
    TextView label_company_english_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        image = (ImageView) findViewById(R.id.img_company_details_main_image);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        label_company_name = (TextView) findViewById(R.id.label_company_name);
        label_company_english_name = (TextView) findViewById(R.id.label_company_english_name);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            if (data.containsKey("CompanyID")) {
                int company_id = Integer.parseInt(data.getString("CompanyID"));
                new AsyncCallCompanyDetails().execute(company_id);
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

            label_company_name.setText(company.getChineseName());
            label_company_english_name.setText(company.getEnglishName());

            dialog.dismiss();
        }
    }

}
