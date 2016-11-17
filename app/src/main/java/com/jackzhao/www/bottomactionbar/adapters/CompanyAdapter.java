package com.jackzhao.www.bottomactionbar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.utils.GetImageAsyncTaskHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompanyAdapter extends BaseAdapter {

    JSONArray jsonArray;
    Context context;

    public CompanyAdapter(Context _context, JSONArray _jsonArray) {
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
        View listview = inflater.inflate(R.layout.activity_listview_item_company, viewGroup, false);

        try {
            JSONObject company = (JSONObject) jsonArray.get(position);

            ImageView image = (ImageView) listview.findViewById(R.id.img_company);
            TextView lb_company = (TextView) listview.findViewById(R.id.lb_company);
            TextView lb_company_ename = (TextView) listview.findViewById(R.id.lb_company_ename);

            String company_image = company.getString("ImageURL");
            new GetImageAsyncTaskHelper(image).execute(company_image);

            String company_name = company.getString("CName");
            lb_company.setText(company_name);

            String company_ename = company.getString("EName");
            lb_company_ename.setText(company_ename);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listview;
    }
}
