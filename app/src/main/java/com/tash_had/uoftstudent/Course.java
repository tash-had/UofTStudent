package com.tash_had.uoftstudent;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tash-had on 2017-11-09.
 */

public class Course {

    /**
     * A HashMap to store grading categories with their corresponding assessments and grades
     *
     * Example:
     * Category = Labs
     * ArrayList of assessmentsAndGrades: ["lab1", 80], ["lab2", 93], ["lab3", 70]
     * */
    private HashMap<String, ArrayList<Object[]>> categoryToAssessmentMap;
    private HashMap<String, Double> categoryToWeightMap;

    public Course(){
        setCategoryToAssessmentMap(new HashMap<String, ArrayList<Object[]>>());
    }

    public boolean addCategory(String categoryName){
        ArrayList<Object[]> assesmentsArrayList = new ArrayList<>();
        if (!getCategoryToAssessmentMap().containsKey(categoryName)){
            getCategoryToAssessmentMap().put(categoryName, assesmentsArrayList);
            return true;
        }
        return false;

    }

    public boolean editCategoryName(String oldName, String newName){
        if (getCategoryToAssessmentMap().containsKey(oldName)){
            ArrayList<Object[]> assessmentArrayList = getCategoryToAssessmentMap().get(oldName);
            getCategoryToAssessmentMap().remove(oldName);
            getCategoryToAssessmentMap().put(newName, assessmentArrayList);
            return true;
        }
        return false;
    }

    public boolean removeCategory(String categoryName){
        if (getCategoryToAssessmentMap().containsKey(categoryName)){
            getCategoryToAssessmentMap().remove(categoryName);
            return true;
        }
        return false;
    }

    public boolean addAssessment(String category, String assessmentName, double grade){
        if (getCategoryToAssessmentMap().containsKey(category)){
            ArrayList<Object[]> assessmentsArrayList = getCategoryToAssessmentMap().get(category);
            Object[] assessmentAndGradArr = {assessmentName, grade};
            assessmentsArrayList.add(assessmentAndGradArr);
            return true;
        }
        return false;
    }

    public boolean editAssessment(String categoryName, String assessmentName,
                                  @Nullable String newAssessmentName, @Nullable Double newGrade){
        ArrayList<Object[]> assessmentList = getCategoryToAssessmentMap().get(categoryName);
        if (getCategoryToAssessmentMap().containsKey(categoryName)){
            for (int i = 0; i < assessmentList.size(); i++){
                Object[] assessment = assessmentList.get(i);
                String aName = (String) assessment[0];
                if (aName.equals(assessmentName)){
                    String name = newAssessmentName == null ? aName : newAssessmentName;
                    Double grade = newGrade == null ? (double) assessment[1] : newGrade;
                    addAssessment(categoryName, name, grade);
                    removeAssessment(categoryName, aName, (double) assessment[1]);
                }
            }
        }
    }
    public boolean removeAssessment(String categoryName, String assessmentName, double assessmentGrade){
        if (getCategoryToAssessmentMap().containsKey(categoryName)){
            ArrayList<Object[]> assessmentList = getCategoryToAssessmentMap().get(categoryName);
            boolean removeIndex = false;
            int indexToRemove = 0;
            for (int i = 0; i < assessmentList.size(); i++){
                Object[] assessment = assessmentList.get(i);
                if ((assessment[0]).equals(assessmentName) && (assessment[1] == assessmentGrade)){
                    indexToRemove = i;
                    removeIndex = true;
                }
            }
            if (removeIndex){
                assessmentList.remove(indexToRemove);
                return true;
            }
            return false;
        }

    }
    public HashMap<String, ArrayList<Object[]>> getCategoryToAssessmentMap() {
        return categoryToAssessmentMap;
    }

    public void setCategoryToAssessmentMap(HashMap<String, ArrayList<Object[]>> categoryToAssessmentMap) {
        this.categoryToAssessmentMap = categoryToAssessmentMap;
    }
}
