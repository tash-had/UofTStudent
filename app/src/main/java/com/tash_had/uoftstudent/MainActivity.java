package com.tash_had.uoftstudent;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    /*
    TODO: Use Acorn API to ask user for course reviews for previous courses taken + import grades
    TODO: Add pictures to homescreen recyclerview to make it less plain
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar
        android.support.v7.widget.Toolbar homeScreenToolBar = findViewById(R.id.home_screen_toolbar);
        homeScreenToolBar.setTitle("Home");
        homeScreenToolBar.setTitleTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        setSupportActionBar(homeScreenToolBar);
//        homeScreenToolBar.inflateMenu(R.menu.courses_toolbar_actions);

        // Set test student
        SessionData.sessionStudent = CodeTest.testStudent();

        homeScreenDrawer = buildNavDrawer(homeScreenToolBar, buildAccountHeader(),
                buildDrawerItem("Home", MainActivity.class, true),
                buildDrawerItem("Courses", MainActivity.class, false),
                buildDrawerItem("Settings", MainActivity.class, false));

        prepRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_course:
                addCourseClick();
                break;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.courses_toolbar_actions, menu);
        return true;
    }

    private void prepRecyclerView(){
        // Setup recyclerview
        mRecyclerView = findViewById(R.id.main_activity_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Use a layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyCoursesAdapter((Course[]) SessionData.getSessionStudent().getCourses(),
                this);
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

    public void addCourseClick(){
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
    }
}
