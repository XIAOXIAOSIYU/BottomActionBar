package com.jackzhao.www.bottomactionbar.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

public class Common {

    public static final String TAG = "JACK_TAG";
    public static final String BOUNDLE_COMPANY_ID = "Company_Id";
    public static final String WSDL_COMPANY_LIST = "http://209.79.127.90/GetFirstMenu.svc/json/searchresults/%1$s,%2$s,%3$s,%4$s,%5$s";
    public static final String WSDL_COMPANY_DETAILS = "http://iccyp.com/GetFirstMenu.svc/json/detail/%1$s";
    public static final String WSDL_COMPANY_IMAGE_LIST = "http://iccyp.com/GetFirstMenu.svc/json/GetImages/%1$s";
    public static final String WSDL_COMPANY_REVIEW_LIST = "http://iccyp.com/GetFirstMenu.svc/json/GetReview/%1$s";
    public static final String APP_BUSINESS_IMAGE_SERVER_URL = "http://img.iccyp.com/hizo/businesses/%1$s.jpg";
    public static final String APP_USER_IMAGE_SERVER_URL = "http://img.iccyp.com/hizo/users/%1$s.jpg";

    public static final int MY_SOCKET_TIMEOUT_MS = 5000;


    public static void ImageLoaderWithVolley(final Context context, final ImageView image_view, String image_url) {

        ImageLoader imageLoader = AppSingleton.getInstance(context).getImageLoader();
        imageLoader.get(image_url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                image_view.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

    public static ShapeDrawable ImageCircled(int radius){

        ShapeDrawable drawable =new ShapeDrawable(new OvalShape());
        drawable.setIntrinsicHeight(radius);
        drawable.setIntrinsicWidth(radius);
        drawable.getPaint().setColor(Color.parseColor("#abcd123"));

        return drawable;
    }
}
