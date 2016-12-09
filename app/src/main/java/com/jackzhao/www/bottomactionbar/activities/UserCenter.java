package com.jackzhao.www.bottomactionbar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jackzhao.www.bottomactionbar.models.User;
import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.utils.Common;
import com.jackzhao.www.bottomactionbar.utils.UserManage;

public class UserCenter extends AppCompatActivity {

    private UserManage userManage;
    private User user;

    private ImageView user_photo;
    private ImageView user_photo_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        userManage = new UserManage(this);
        user = userManage.GetUserDetails();

        user_photo_bg = (ImageView) findViewById(R.id.image_user_center_photo_background);
        user_photo_bg.setScaleType(ImageView.ScaleType.FIT_XY);
        String user_photo_path = String.format(Common.APP_USER_IMAGE_SERVER_URL,user.getUserId());
        Common.ImageLoaderWithVolley(this, user_photo_bg, user_photo_path);
    }

    public void userLogout(View view) {
        userManage.SetUserLogOut();
        Common.CommonStartActivity(this, com.jackzhao.www.bottomactionbar.activities.User.class);
    }
}
