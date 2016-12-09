package com.jackzhao.www.bottomactionbar.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.utils.Common;
import com.jackzhao.www.bottomactionbar.utils.UserManage;

public class User extends AppCompatActivity {

    private UserManage user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user = new UserManage(this);
        if (user.CheckUserLogged()) {
            Common.CommonStartActivity(User.this, UserCenter.class);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        toolbar.setTitle("登录");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back_white_24dp, null));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.CommonToast(User.this, "User");
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void userLogin(View view) {

        EditText txt_email = (EditText) findViewById(R.id.txt_user_email);
        EditText txt_password = (EditText) findViewById(R.id.txt_user_password);

        //UserManage user = new UserManage(this);
        user.generateLoginSession(txt_email.getText().toString(), txt_password.getText().toString());
    }
}
