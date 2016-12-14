package com.jackzhao.www.bottomactionbar.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.models.User;
import com.jackzhao.www.bottomactionbar.utils.Common;
import com.jackzhao.www.bottomactionbar.utils.UserManage;

public class UserCenter extends AppCompatActivity {

    private UserManage userManage;
    private User user;

    //private FrameLayout layout_user_center_photo_zone;

    private ImageView user_photo;
    private ImageView user_photo_bg;
    private TextView txt_user_nickname;
    private TextView txt_user_email;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        userManage = new UserManage(this);
        user = userManage.GetUserDetails();
        String user_photo_path = String.format(Common.APP_USER_IMAGE_SERVER_URL, user.getUserId());

        user_photo_bg = (ImageView) findViewById(R.id.image_user_center_photo_background);
        user_photo = (ImageView) findViewById(R.id.image_user_center_photo);
        Common.ImageLoaderWithVolley(this, user_photo, user_photo_path, false);
        Common.ImageLoaderWithVolley(this, user_photo_bg, user_photo_path, true);

        txt_user_nickname = (TextView) findViewById(R.id.txt_user_nickname);
        txt_user_email = (TextView) findViewById(R.id.txt_user_email);

        txt_user_nickname.setText(user.getNickName());
        txt_user_email.setText(user.getUserEmail());
    }

    public void userLogout(View view) {
        userManage.SetUserLogOut();
        Common.CommonStartActivity(this, com.jackzhao.www.bottomactionbar.activities.User.class);
    }
}
