package com.tash_had.uoftstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/*
TODO: LEARN https://developer.android.com/training/transitions/index.html !
 */
public class EditCourseController extends AppCompatActivity{
    private RecyclerView courseCategoriesRecyclerView;
    private RecyclerView.LayoutManager courseCategoriesLayoutManager;
    private RecyclerView.Adapter courseCategoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_view);


        android.support.v7.widget.Toolbar homeScreenToolBar = findViewById(R.id.form_toolbar);
        homeScreenToolBar.setTitle("");
        homeScreenToolBar.setTitleTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        homeScreenToolBar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.primary, null));

        setSupportActionBar(homeScreenToolBar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intentRecieved = getIntent();
        if (intentRecieved.getExtras() != null && intentRecieved.getExtras().containsKey("purpose")){
            String purpose = (String) intentRecieved.getExtras().get("purpose");

            if (purpose != null && purpose.equals("edit")){
                if
            }

        }
        courseCategoriesRecyclerView = findViewById(R.id.courseCategoriesRecyclerView);

        courseCategoriesLayoutManager = new LinearLayoutManager(this);
        courseCategoriesRecyclerView.setLayoutManager(courseCategoriesLayoutManager);

        courseCategoriesAdapter = new CourseCategoryAdapter(((Course[]) SessionData.getSessionStudent().getCourses())[0].getCategoryToWeightMap());
        courseCategoriesRecyclerView.setAdapter(courseCategoriesAdapter);
    }


//    private void initValidationEt() {
//        final MaterialEditText validationEt = (MaterialEditText) findViewById(R.id.validationEt);
//        validationEt.addValidator(new RegexpValidator("Only Integer Valid!", "\\d+"));
//        final Button validateBt = (Button) findViewById(R.id.validateBt);
//        validateBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // validate
//                validationEt.validate();
//            }
//        });
//    }
    public enum CourseViewPurpose{
        ADD_COURSE, EDIT_COURSE
    }
}
