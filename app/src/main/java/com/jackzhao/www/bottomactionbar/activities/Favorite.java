package com.jackzhao.www.bottomactionbar.activities;

import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.models.*;
import com.jackzhao.www.bottomactionbar.models.User;
import com.jackzhao.www.bottomactionbar.utils.UserManage;

public class Favorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        UserManage userManage =new UserManage(this);
        TextView label =(TextView)findViewById(R.id.textView3);
        User user = userManage.GetUserDetails();

        label.setText(user.getNickName());
    }
}
