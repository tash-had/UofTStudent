package com.tash_had.uoftstudent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tash-had on 2017-11-22.
 */

class MyCoursesAdapter extends RecyclerView.Adapter<MyCoursesAdapter.ViewHolder> {
    private Course[] courses;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card_view,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.courseTitle.setText(courses[position].getCourseName());
        holder.courseDetail.setText("Next class goes here?");

        double courseAvg = courses[position].getCourseAverage();
        String grade = (String) Calculator.gradeWithPercent(courseAvg)[0];
        switch (grade.substring(0, 1)){
            case "A":
                holder.courseIcon.setImageResource(R.drawable.a_letter_icon);
                break;
            case "B":
                holder.courseIcon.setImageResource(R.drawable.b_letter_icon);
                break;
            case "C":
                holder.courseIcon.setImageResource(R.drawable.c_letter_icon);
                break;
            case "D":
                holder.courseIcon.setImageResource(R.drawable.d_letter_icon);
                break;
            case "F":
                holder.courseIcon.setImageResource(R.drawable.f_letter_icon);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return courses.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView courseTitle;
        public TextView courseDetail;
        public ImageView courseIcon;
        public Button courseBtn1;
        public Button courseBtn2;


        public ViewHolder(View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.course_card_title);
            courseDetail = itemView.findViewById(R.id.course_card_detail);
            courseIcon = itemView.findViewById(R.id.course_card_icon);
            courseBtn1 = itemView.findViewById(R.id.course_card_btn_1);
            courseBtn2 = itemView.findViewById(R.id.course_card_btn_2);
        }
    }

    public MyCoursesAdapter(Course[] courses) {
        this.courses = courses;
    }

}
