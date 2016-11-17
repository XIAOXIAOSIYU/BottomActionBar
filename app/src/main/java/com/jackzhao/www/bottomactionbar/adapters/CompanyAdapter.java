package com.jackzhao.www.bottomactionbar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackzhao.www.bottomactionbar.R;

import org.json.JSONArray;
import org.json.JSONException;

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
            JSONArray company = (JSONArray) jsonArray.get(position);

            ImageView image = (ImageView) listview.findViewById(R.id.img_person);
            TextView lb_person_name = (TextView) listview.findViewById(R.id.lb_person_name);
            TextView lb_person_title = (TextView) listview.findViewById(R.id.lb_person_title);
            Button button = (Button) listview.findViewById(R.id.btn_details);

            String company_name = company.getString(1);
            lb_person_name.setText(company_name);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listview;
    }
}
