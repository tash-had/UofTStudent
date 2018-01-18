package com.tash_had.uoftstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.WindowManager;

import com.rengwuxian.materialedittext.MaterialEditText;


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
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_view);

        Intent intentRecieved = getIntent();
        if (intentRecieved.getExtras() != null && intentRecieved.getExtras().containsKey("course")){
            String courseName = (String) intentRecieved.getExtras().get("course");
            try{
                course = SessionData.getSessionStudent().getCourse(courseName);
            }catch (CourseNotFoundException e){
                e.printStackTrace();
            }
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

        courseCategoriesAdapter = new CourseCategoryAdapter(course.getCategoryToWeightMap());
        courseCategoriesRecyclerView.setAdapter(courseCategoriesAdapter);

        setAndDisableFields();
    }

    /**
     * Disable the Course Name and Credit Weight edittext fields
     */
    private void setAndDisableFields(){
        MaterialEditText courseNameEt = findViewById(R.id.course_view_name_et);
        MaterialEditText courseCreditEt = findViewById(R.id.course_view_credit_et);

        courseNameEt.setText(course.getCourseName());
        courseCreditEt.setText(String.valueOf(course.getCourseCreditWeight()));
        courseNameEt.setEnabled(false);
        courseCreditEt.setEnabled(false);
        courseNameEt.setFloatingLabel(1);

        // Make sure keyboard doesn't show up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_view_toolbar_actions, menu);
        return true;
    }

}
