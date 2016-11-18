package com.jackzhao.www.bottomactionbar.components;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.activities.Favorite;
import com.jackzhao.www.bottomactionbar.activities.History;
import com.jackzhao.www.bottomactionbar.activities.Home;
import com.jackzhao.www.bottomactionbar.activities.Search;
import com.jackzhao.www.bottomactionbar.activities.User;

import static com.jackzhao.www.bottomactionbar.R.layout.fragment_bottom_toolbar;

public class toolbar_bottom_fragment extends Fragment {

    private ImageView btn_search;
    private ImageView btn_history;
    private ImageView btn_home;
    private ImageView btn_favorite;
    private ImageView btn_user;

//    BottomToolbarButtonsListener activityCommander;
//
//    public interface BottomToolbarButtonsListener {
//        public void ButtomToolbarButtonsClicked();
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            activityCommander = (BottomToolbarButtonsListener) context;
//        } catch (ClassCastException ccex) {
//            throw new ClassCastException(context.toString());
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(fragment_bottom_toolbar, container, false);
        this.initToolbarButtons(view);
        return view;
    }

    private void initToolbarButtons(View view) {

        btn_search = (ImageView) view.findViewById(R.id.btn_search);
        btn_history = (ImageView) view.findViewById(R.id.btn_history);
        btn_home = (ImageView) view.findViewById(R.id.btn_home);
        btn_favorite = (ImageView) view.findViewById(R.id.btn_favorite);
        btn_user = (ImageView) view.findViewById(R.id.btn_user);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context.getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context.getApplicationContext(), Favorite.class);
                startActivity(intent);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context.getApplicationContext(), History.class);
                startActivity(intent);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context.getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context.getApplicationContext(), User.class);
                startActivity(intent);
            }
        });
    }

}
