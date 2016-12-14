package com.jackzhao.www.bottomactionbar.adapters;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class ReviewAdapter {

    JSONArray jsonArray;
    Context context;

    public ReviewAdapter(Context _context, JSONArray _jsonArray) {
        super();

        this.jsonArray = _jsonArray;
        this.context = _context;
    }

    public void generateReviewView(LinearLayout lv_company_review){

        int count = this.jsonArray.length();

        for (int i = 0; i < count; i++) {
            LayoutInflater inflater = null;
            inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listview = inflater.inflate(R.layout.activity_company_details_reviews_item, null);

            ImageView user_photo = (ImageView) listview.findViewById(R.id.company_review_user_photo);
            TextView user_nickname = (TextView) listview.findViewById(R.id.company_review_user_nickname);
            TextView review_post_date = (TextView) listview.findViewById(R.id.company_review_post_date);
            TextView company_review_score_taste_text = (TextView) listview.findViewById(R.id.company_review_score_taste_text);
            TextView company_review_score_taste_value = (TextView) listview.findViewById(R.id.company_review_score_taste_value);
            TextView company_review_score_service_text = (TextView) listview.findViewById(R.id.company_review_score_service_text);
            TextView company_review_score_sevice_value = (TextView) listview.findViewById(R.id.company_review_score_sevice_value);
            TextView company_review_score_amb_text = (TextView) listview.findViewById(R.id.company_review_score_amb_text);
            TextView company_review_score_amb_value = (TextView) listview.findViewById(R.id.company_review_score_amb_value);
            TextView company_review_content = (TextView) listview.findViewById(R.id.company_review_content);

            JSONObject review = null;
            try {
                review = (JSONObject) this.jsonArray.get(i);
                String image_url = String.format(Common.APP_USER_IMAGE_SERVER_URL, review.getString("UserID"));
                Common.ImageLoaderWithVolley(context, user_photo, image_url,false);

                String post_date = review.getString("PublishTime");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    try {
                        review_post_date.setText(format.parse(post_date).toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                String user_name = review.getString("Nickname");
                user_nickname.setText(user_name.substring(0, 1).toUpperCase() + user_name.substring(1));
                company_review_score_taste_value.setText(review.getString("Taste"));
                company_review_score_sevice_value.setText(review.getString("Service"));
                company_review_score_amb_value.setText(review.getString("Amb"));
                company_review_content.setText(review.getString("ReviewContent"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            lv_company_review.addView(listview);

        }
    }

}
