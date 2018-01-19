package com.tash_had.uoftstudent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by tash-had on 2017-11-27.
 */

class CourseCategoryAdapter extends RecyclerView.Adapter<CourseCategoryAdapter.ViewHolder> {
    private HashMap<String, Double> categoryToWeightMap;
    private String[] categoryNames;

    @Override
    public CourseCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_category_card,
                        parent, false);
                return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CourseCategoryAdapter.ViewHolder holder, int position) {
        if (position < categoryNames.length){
            holder.categoryNameEt.setText(categoryNames[position]);
            String category = categoryNames[position];
            Double weight = categoryToWeightMap.get(category);
            holder.categoryWeightEt.setText(String.valueOf(weight.toString()));
            holder.categoryNameEt.setEnabled(false);
            holder.categoryWeightEt.setEnabled(false);
        }else{
            holder.categoryNameEt.setText("");
            holder.categoryWeightEt.setText("");
            holder.categoryNameEt.setEnabled(true);
            holder.categoryWeightEt.setEnabled(true);
        }



    }

    @Override
    public int getItemCount() {
        // Return the count +1 because we want an extra empty row at the top for new entires
        return categoryToWeightMap.keySet().size() + 1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public MaterialEditText categoryNameEt;
        public MaterialEditText categoryWeightEt;
        public Button addCategoryDoneBtn;


        public ViewHolder(View itemView) {
            super(itemView);
                categoryNameEt = itemView.findViewById(R.id.categoryNameTv);
                categoryWeightEt = itemView.findViewById(R.id.categoryWeightEt);
//                addCategoryDoneBtn = itemView.findViewById(R.id.addCategoryDoneBtn);
            }
    }


    public CourseCategoryAdapter(LinkedHashMap<String, Double> categoryToWeightMap){
        categoryNames = categoryToWeightMap.keySet().toArray(
                new String[categoryToWeightMap.keySet().size()]
        );
        this.categoryToWeightMap = categoryToWeightMap;

    }

}
