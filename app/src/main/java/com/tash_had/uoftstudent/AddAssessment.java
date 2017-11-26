package com.tash_had.uoftstudent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

/*
TODO: LEARN https://developer.android.com/training/transitions/index.html !
 */
public class AddAssessment extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        android.support.v7.widget.Toolbar homeScreenToolBar = findViewById(R.id.form_toolbar);
        homeScreenToolBar.setTitle("");
        homeScreenToolBar.setTitleTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        homeScreenToolBar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.primary, null));

        setSupportActionBar(homeScreenToolBar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
}
