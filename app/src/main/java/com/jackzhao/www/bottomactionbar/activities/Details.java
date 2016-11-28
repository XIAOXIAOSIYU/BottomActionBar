package com.jackzhao.www.bottomactionbar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.models.Company;
import com.jackzhao.www.bottomactionbar.utils.AppSingleton;
import com.jackzhao.www.bottomactionbar.utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Details extends AppCompatActivity implements OnMapReadyCallback {

    private ImageView company_details_main_image;
    private TextView label_company_name, label_company_english_name, label_company_tags, label_company_address, label_company_openhour, label_company_phone;
    private double latitude = 0, longitude = 0;
    private GoogleMap mMap;

    private ImageButton btn_company_details_more_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_company_details);
        setSupportActionBar(toolbar);

        company_details_main_image = (ImageView) findViewById(R.id.img_company_details_main_image);
        company_details_main_image.setScaleType(ImageView.ScaleType.FIT_XY);

        label_company_name = (TextView) findViewById(R.id.lb_company_name);
        label_company_english_name = (TextView) findViewById(R.id.lb_company_english_name);
        label_company_tags = (TextView) findViewById(R.id.lb_company_tags);
        label_company_address = (TextView) findViewById(R.id.lb_company_details_address);
        label_company_openhour = (TextView) findViewById(R.id.lb_company_details_openhour);
        label_company_phone = (TextView) findViewById(R.id.lb_company_details_phone);

        btn_company_details_more_control = (ImageButton) findViewById(R.id.btn_company_details_more_control);
        btn_company_details_more_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu menu = new PopupMenu(Details.this, view);
                menu.getMenuInflater().inflate(R.menu.company_details_more_controller, menu.getMenu());
                menu.show();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.btn_company_details_popup_favorite: {
                                Toast.makeText(getApplicationContext(), "Favorite", Toast.LENGTH_LONG).show();
                                break;
                            }
                            case R.id.btn_company_details_popup_wrong: {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                                break;
                            }
                            default: {
                                break;
                            }
                        }

                        return true;
                    }
                });

            }
        });


        Bundle data = getIntent().getExtras();
        if (data != null) {
            if (data.containsKey(Common.BOUNDLE_COMPANY_ID)) {
                int company_id = data.getInt(Common.BOUNDLE_COMPANY_ID);
                String company_images_url = String.format(Common.WSDL_COMPANY_IMAGE_LIST, company_id);
                String company_details_url = String.format(Common.WSDL_COMPANY_DETAILS, company_id);

                JsonObjectRequest details_request = new JsonObjectRequest(Request.Method.GET,
                        company_details_url,
                        null,
                        createDetailsResponseSuccessListener(),
                        createResponseErrorListener());

                JsonObjectRequest images_request = new JsonObjectRequest(Request.Method.GET,
                        company_images_url,
                        null,
                        createImagesResponseSuccessListener(),
                        createResponseErrorListener());

                AppSingleton.getInstance(this).addToRequestQueue(details_request, "");
                AppSingleton.getInstance(this).addToRequestQueue(images_request, "");

            }
        }

    }

    private Response.Listener<JSONObject> createDetailsResponseSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                bindCompanyDetails(response);

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(Details.this);
            }
        };
    }

    private Response.Listener<JSONObject> createImagesResponseSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray image_list = new JSONArray();
                try {
                    image_list = response.getJSONArray("GetImagesResult");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (image_list.length() > 0) {
                    try {
                        JSONObject image = (JSONObject) image_list.get(0);
                        String image_url = String.format(Common.APP_IMAGE_SERVER_URL, image.getString("ImageName"));
                        Common.ImageLoaderWithVolley(Details.this, company_details_main_image, image_url);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    initCompanyImageGallerySource(image_list);
                }
            }
        };
    }

    private Response.ErrorListener createResponseErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
    }

    private void bindCompanyDetails(JSONObject _response) {

        try {

            JSONArray company_response = _response.getJSONArray("GetDetailListResult");
            JSONObject response = company_response.getJSONObject(0);

            Company company = new Company(response);

            String cname = company.getChineseName();
            String ename = company.getEnglishName();
            String tags = company.getTags();

            if (ename == "null" || ename.length() == 0) {
                ename = "--";
            }

            if (cname == "null" || cname.length() == 0) {
                cname = ename;
            }

            if (tags == "null" || tags.length() == 0) {
                tags = "--";
            }

            label_company_name.setText(cname);
            label_company_english_name.setText(ename);
            label_company_tags.setText(tags);

            label_company_address.setText(company.getStreet() + " " + company.getCity() + " " + company.getState() + ", " + company.getZipCode());
            label_company_openhour.setText(company.getOpenHour());
            label_company_phone.setText(company.getPhone());

            latitude = company.getLatitude();
            longitude = company.getLongitude();

            if (company.getOpenHour().equals("")) {
                LinearLayout layout_company_info_openhour = (LinearLayout) findViewById(R.id.layout_company_info_openhour);
                layout_company_info_openhour.setVisibility(View.GONE);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng sydney = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_36dp))
                .alpha(0.7f));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));
    }

    private void initCompanyImageGallerySource(JSONArray image_list) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.company_details_image_gallery);

        for (int i = 0; i < image_list.length(); i++) {

            JSONObject image = null;
            try {

                image = (JSONObject) image_list.get(0);
                String image_url = String.format(Common.APP_IMAGE_SERVER_URL, image.getString("ImageName"));

                ImageView _image = new ImageView(this);
                _image.setId(i);
                _image.setPadding(2, 2, 2, 2);
                //_image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_search_black_24dp));

                _image.setMaxWidth(90);
                Common.ImageLoaderWithVolley(Details.this, _image, image_url);
                layout.addView(_image);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
