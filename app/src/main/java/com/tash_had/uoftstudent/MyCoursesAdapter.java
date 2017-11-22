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
    private String[] mDataset;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card_view,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.courseTitle.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
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

    public MyCoursesAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

}
