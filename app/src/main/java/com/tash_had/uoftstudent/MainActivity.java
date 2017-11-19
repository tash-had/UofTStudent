package com.tash_had.uoftstudent;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar homeScreenToolBar = findViewById(R.id.home_screen_toolbar);
        homeScreenToolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(homeScreenToolBar);

        Drawer homeScreenDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(homeScreenToolBar)
                .addDrawerItems(new PrimaryDrawerItem().withName("Courses"))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .build();
    }
}
