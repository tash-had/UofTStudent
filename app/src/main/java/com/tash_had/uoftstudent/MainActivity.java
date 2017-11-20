package com.tash_had.uoftstudent;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar homeScreenToolBar = findViewById(R.id.home_screen_toolbar);

        homeScreenToolBar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(homeScreenToolBar);
        ActionBar actionBar = getSupportActionBar();

        Drawer homeScreenDrawer = buildNavDrawer(homeScreenToolBar, buildAccountHeader(), buildDrawerItems());
    }

    private Drawer buildNavDrawer(Toolbar toolbar, AccountHeader accountHeader, IDrawerItem ... drawerItems){
        return new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(drawerItems)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .withAccountHeader(buildAccountHeader())
                .build();
    }

    private IDrawerItem[] buildDrawerItems(){
        IDrawerItem home = new PrimaryDrawerItem().withName("Home")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                return false;
            }
        });
        IDrawerItem courses = new SecondaryDrawerItem().withName("Courses")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                });
        IDrawerItem settings = new SecondaryDrawerItem().withName("Settings")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                });
        return new IDrawerItem[]{home, courses, settings};

    }

    private AccountHeader buildAccountHeader(){
        return new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(new ProfileDrawerItem().withName("Guest").withEmail("guest@utoronto.ca").withIcon(R.drawable.g_letter_icon))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();
    }
}
