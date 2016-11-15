package com.jackzhao.www.bottomactionbar.components;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.activities.Favorite;
import com.jackzhao.www.bottomactionbar.activities.History;
import com.jackzhao.www.bottomactionbar.activities.Home;
import com.jackzhao.www.bottomactionbar.activities.Search;
import com.jackzhao.www.bottomactionbar.activities.User;

public class toolbar_bottom extends AppCompatActivity {

    private ImageView btn_search;
    private ImageView btn_history;
    private ImageView btn_home;
    private ImageView btn_favorite;
    private ImageView btn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_bottom);

        this.initButtonButtonsClick();

    }

    public void initButtonButtonsClick() {

        btn_search = (ImageView) findViewById(R.id.btn_search);
        btn_history = (ImageView) findViewById(R.id.btn_history);
        btn_home = (ImageView) findViewById(R.id.btn_home);
        btn_favorite = (ImageView) findViewById(R.id.btn_favorite);
        btn_user = (ImageView) findViewById(R.id.btn_user);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Favorite.class);
                startActivity(intent);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User.class);
                startActivity(intent);
            }
        });
    }
}
