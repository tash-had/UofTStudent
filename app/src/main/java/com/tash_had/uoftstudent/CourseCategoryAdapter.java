package com.tash_had.uoftstudent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

/**
 * Created by tash-had on 2017-11-27.
 */

class CourseCategoryAdapter extends RecyclerView.Adapter<CourseCategoryAdapter.ViewHolder> {
    private HashMap<String, Double> categoryToWeightMap;
    private String[] categoryNames;

    @Override
    public CourseCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_view_label_card,
                        parent, false);
                return new ViewHolder(view, viewType);
            case 1:
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_category_card,
                        parent, false);
                return new ViewHolder(view, viewType);
        }

    }

    @Override
    public void onBindViewHolder(CourseCategoryAdapter.ViewHolder holder, int position) {
        if (position < categoryNames.length && position != 0){
            holder.categoryNameEt.setText(categoryNames[position]);
            holder.categoryNameEt.setEnabled(false);
            String category = categoryNames[position];
            Double weight = categoryToWeightMap.get(category);
            holder.categoryWeightEt.setText(String.valueOf(weight.toString()));
            holder.categoryWeightEt.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        // Return the count +1 because we want an extra empty row at the top for new entires
        return categoryToWeightMap.keySet().size() + 1;
    }

    @Override
    public int getItemViewType(int position){
        if (position == 0){
            return 0;
        }else{
            return 1;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public MaterialEditText categoryNameEt;
        public MaterialEditText categoryWeightEt;
        public Button addCategoryDoneBtn;


        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 1){
                categoryNameEt = itemView.findViewById(R.id.categoryNameTv);
                categoryWeightEt = itemView.findViewById(R.id.categoryWeightEt);
                addCategoryDoneBtn = itemView.findViewById(R.id.addCategoryDoneBtn);
            }
        }
    }


    public CourseCategoryAdapter(HashMap<String, Double> categoryToWeightMap){
        categoryNames = categoryToWeightMap.keySet().toArray(
                new String[categoryToWeightMap.keySet().size()]
        );
        this.categoryToWeightMap = categoryToWeightMap;

    }

}
