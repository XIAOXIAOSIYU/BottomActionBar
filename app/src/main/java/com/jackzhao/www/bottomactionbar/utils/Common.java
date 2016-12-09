package com.jackzhao.www.bottomactionbar.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.jackzhao.www.bottomactionbar.R;

public class Common {

    public static final String TAG = "JACK_TAG";
    public static final String BOUNDLE_COMPANY_ID = "Company_Id";
    public static final String WSDL_COMPANY_LIST = "http://209.79.127.90/GetFirstMenu.svc/json/searchresults/%1$s,%2$s,%3$s,%4$s,%5$s";
    public static final String WSDL_COMPANY_DETAILS = "http://iccyp.com/GetFirstMenu.svc/json/detail/%1$s";
    public static final String WSDL_COMPANY_IMAGE_LIST = "http://iccyp.com/GetFirstMenu.svc/json/GetImages/%1$s";
    public static final String WSDL_COMPANY_REVIEW_LIST = "http://iccyp.com/GetFirstMenu.svc/json/GetReview/%1$s";
    public static final String WSDL_USER_LOGIN = "http://iccyp.com/GetFirstMenu.svc/json/login/%1$s/%2$s";
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
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.imageholder);
                image_view.setImageBitmap(bitmap);
            }
        });
    }

    public static void CommonToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void CommonStartActivity(Context context, Class<?> activity) {

        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public static ShapeDrawable ImageCircled(int radius) {

        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.setIntrinsicHeight(radius);
        drawable.setIntrinsicWidth(radius);
        drawable.getPaint().setColor(Color.parseColor("#abcd123"));
        return drawable;
    }
}
