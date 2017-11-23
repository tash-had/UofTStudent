package com.tash_had.uoftstudent;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private Drawer homeScreenDrawer;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set test student
        SessionData.sessionStudent = CodeTest.testStudent();

        android.support.v7.widget.Toolbar homeScreenToolBar = findViewById(R.id.home_screen_toolbar);

        homeScreenDrawer = buildNavDrawer(homeScreenToolBar, buildAccountHeader(),
                buildDrawerItem("Home", MainActivity.class, true),
                buildDrawerItem("Courses", MainActivity.class, false),
                buildDrawerItem("Settings", MainActivity.class, false));

        mRecyclerView = findViewById(R.id.main_activity_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Use a layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyCoursesAdapter((Course[]) SessionData.getSessionStudent().getCourses());
        mRecyclerView.setAdapter(mAdapter);

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

    private IDrawerItem buildDrawerItem(String itemName, final Class classToLoad, boolean primaryItem){
        if (primaryItem){
            return new PrimaryDrawerItem().withName(itemName).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    startNewActivity(classToLoad);
                    homeScreenDrawer.closeDrawer();
                    return true;
                }
            });
        }
        return new SecondaryDrawerItem().withName(itemName)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        startNewActivity(classToLoad);
                        homeScreenDrawer.closeDrawer();
                        return true;
                    }
                });
    }

    private void startNewActivity(Class activityToLoad){
        Intent changeActivityIntent = new Intent(MainActivity.this, activityToLoad);
        startActivity(changeActivityIntent);
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
