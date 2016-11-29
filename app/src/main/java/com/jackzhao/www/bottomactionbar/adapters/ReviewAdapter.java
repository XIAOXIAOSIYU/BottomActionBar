package com.jackzhao.www.bottomactionbar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReviewAdapter extends BaseAdapter {

    JSONArray jsonArray;
    Context context;

    public ReviewAdapter(Context _context, JSONArray _jsonArray) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listview = inflater.inflate(R.layout.activity_company_details_reviews_item, viewGroup, false);

        ImageView user_photo = (ImageView) listview.findViewById(R.id.company_review_user_photo);
        user_photo.setBackground(Common.ImageCircled(90));

        TextView user_nickname = (TextView) listview.findViewById(R.id.company_review_user_nickname);
        TextView review_post_date = (TextView) listview.findViewById(R.id.company_review_post_date);
        TextView company_review_score_taste_text = (TextView) listview.findViewById(R.id.company_review_score_taste_text);
        TextView company_review_score_taste_value = (TextView) listview.findViewById(R.id.company_review_score_taste_value);
        TextView company_review_score_service_text = (TextView) listview.findViewById(R.id.company_review_score_service_text);
        TextView company_review_score_sevice_value = (TextView) listview.findViewById(R.id.company_review_score_sevice_value);
        TextView company_review_score_amb_text = (TextView) listview.findViewById(R.id.company_review_score_amb_text);
        TextView company_review_score_amb_value = (TextView) listview.findViewById(R.id.company_review_score_amb_value);

        try {
            JSONObject review = (JSONObject) jsonArray.get(i);

            String image_url = String.format(Common.APP_USER_IMAGE_SERVER_URL, review.getString("UserID"));
            Common.ImageLoaderWithVolley(this.context, user_photo, image_url);

            user_nickname.setText(review.getString("Nickname"));
            review_post_date.setText(review.getString("PublishTime"));
            company_review_score_taste_value.setText(review.getString("Taste"));
            company_review_score_sevice_value.setText(review.getString("Service"));
            company_review_score_amb_value.setText(review.getString("Amb"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listview;
    }


}
