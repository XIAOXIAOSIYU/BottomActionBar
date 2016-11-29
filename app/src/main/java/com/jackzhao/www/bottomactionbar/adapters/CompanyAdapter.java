package com.jackzhao.www.bottomactionbar.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.activities.Details;
import com.jackzhao.www.bottomactionbar.utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompanyAdapter extends BaseAdapter {

    JSONArray jsonArray;
    Context context;

    public CompanyAdapter(Context _context, JSONArray _jsonArray) {
        super();
        this.jsonArray = _jsonArray;
        this.context = _context;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return jsonArray.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listview = inflater.inflate(R.layout.activity_company_listview_item, viewGroup, false);

        try {
            JSONObject company = (JSONObject) jsonArray.get(position);

            final ImageView company_image = (ImageView) listview.findViewById(R.id.img_company);
            TextView lb_company = (TextView) listview.findViewById(R.id.lb_company);
            TextView lb_company_ename = (TextView) listview.findViewById(R.id.lb_company_ename);
            TextView lb_company_distance = (TextView) listview.findViewById(R.id.lb_company_distance);
            ImageView img_details = (ImageView) listview.findViewById(R.id.img_company_rows_details);

            String company_image_url = String.format(Common.APP_BUSINESS_IMAGE_SERVER_URL, company.getString("ImageURL"));
            Common.ImageLoaderWithVolley(context, company_image, company_image_url);

            String company_name = company.getString("CName");
            String company_ename = company.getString("EName");

            if (company_name.equals("null") && !company_ename.isEmpty()) {
                company_name = company_ename;
            }

            lb_company.setText(company_name);
            lb_company_ename.setText(company_ename);

            //double distance = Double.parseDouble(company.getString("QueryDistance"));
            //DecimalFormat df = new DecimalFormat("0.00");
            //lb_company_distance.setText(df.format(distance) + "m");

            final int Company_Id = Integer.parseInt(company.getString("CID"));
            img_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Common.BOUNDLE_COMPANY_ID, Company_Id);
                    context.startActivity(intent);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listview;
    }
}
