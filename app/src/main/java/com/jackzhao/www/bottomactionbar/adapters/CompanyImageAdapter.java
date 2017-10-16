package com.jackzhao.www.bottomactionbar.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.utils.AppSingleton;
import com.jackzhao.www.bottomactionbar.utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompanyImageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private JSONArray image_resource;

    public CompanyImageAdapter(Context context, int company_id) {
        this.context = context;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        String company_images_url = String.format(Common.WSDL_COMPANY_IMAGE_LIST, company_id);
        JsonObjectRequest images_request = new JsonObjectRequest(Request.Method.GET,
                company_images_url,
                null,
                createImagesResponseSuccessListener(),
                createResponseErrorListener());
        AppSingleton.getInstance(context).addToRequestQueue(images_request, "");

    }

    private Response.Listener<JSONObject> createImagesResponseSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    image_resource = response.getJSONArray("GetImagesResult");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createResponseErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Common.CommonToast(context, error.getMessage());
            }
        };
    }

    @Override
    public int getCount() {
        return image_resource.length();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (FrameLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.content_company_images_item,container,false);
        ImageView image = (ImageView) view.findViewById(R.id.image_view);
        TextView text = (TextView) view.findViewById(R.id.image_count);

        try {
            image.setImageResource(image_resource.getInt(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        text.setText("Image : " + position + " / "+ image_resource.length());

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
