package com.jackzhao.www.bottomactionbar.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.adapters.CompanyImageAdapter;

import static com.jackzhao.www.bottomactionbar.utils.Common.BOUNDLE_COMPANY_ID;

public class CompanyImageGallery extends AppCompatActivity {

    private ViewPager pager;
    private CompanyImageAdapter adapter;
    private ActionBar bar;
    private int company_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_company_image_gallery);

//        bar = getSupportActionBar();
//        bar.setDisplayShowHomeEnabled(true);
//        bar.setTitle(Html.fromHtml("我发布图片列表"));
//        bar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            if (data.containsKey(BOUNDLE_COMPANY_ID)) {
                company_id = data.getInt(BOUNDLE_COMPANY_ID);
                adapter = new CompanyImageAdapter(this, company_id);
                pager = (ViewPager) findViewById(R.id.view_pager);
                pager.setAdapter(adapter);
            }
        }

    }
}
