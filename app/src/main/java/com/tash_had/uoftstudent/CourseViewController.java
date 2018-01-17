package com.tash_had.uoftstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;


/*
TODO: LEARN https://developer.android.com/training/transitions/index.html !
 */

/**
 * A controller for the individual course-view screen
 */
public class CourseViewController extends AppCompatActivity{
    private RecyclerView courseCategoriesRecyclerView;
    private RecyclerView.LayoutManager courseCategoriesLayoutManager;
    private RecyclerView.Adapter courseCategoriesAdapter;
    private String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_view);

        Intent intentRecieved = getIntent();
        if (intentRecieved.getExtras() != null && intentRecieved.getExtras().containsKey("course")){
            course = (String) intentRecieved.getExtras().get("course");
        }

        android.support.v7.widget.Toolbar homeScreenToolBar = findViewById(R.id.form_toolbar);
        homeScreenToolBar.setTitle("");
        homeScreenToolBar.setTitleTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        homeScreenToolBar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.primary, null));

        setSupportActionBar(homeScreenToolBar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        

        courseCategoriesRecyclerView = findViewById(R.id.courseCategoriesRecyclerView);

        courseCategoriesLayoutManager = new LinearLayoutManager(this);
        courseCategoriesRecyclerView.setLayoutManager(courseCategoriesLayoutManager);

        courseCategoriesAdapter = new CourseCategoryAdapter(((Course[]) SessionData.getSessionStudent().getCourses())[0].getCategoryToWeightMap());
        courseCategoriesRecyclerView.setAdapter(courseCategoriesAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_view_toolbar_actions, menu);
        return true;
    }

}
