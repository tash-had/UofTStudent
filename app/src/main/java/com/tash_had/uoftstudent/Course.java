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
        setCategoryToWeightMap(new HashMap<String, Double>());
    }

    public boolean addCategory(String categoryName, Double weight){
        ArrayList<Object[]> assesmentsArrayList = new ArrayList<>();
        if (!getCategoryToAssessmentMap().containsKey(categoryName)){
            getCategoryToAssessmentMap().put(categoryName, assesmentsArrayList);
            addWeight(categoryName, weight);
            return true;
        }
        return false;

    }

    public boolean editCategoryName(String oldName, String newName){
        if (getCategoryToAssessmentMap().containsKey(oldName)){
            ArrayList<Object[]> assessmentArrayList = getCategoryToAssessmentMap().get(oldName);
            getCategoryToAssessmentMap().remove(oldName);
            getCategoryToAssessmentMap().put(newName, assessmentArrayList);
            double weight = getWeight(oldName);
            deleteCategoryFromWeightMap(oldName);
            addWeight(newName, weight);
            return true;
        }
        return false;
    }

    public void editCategoryWeight(String categoryName, double newWeight){
        addWeight(categoryName, newWeight);
    }

    public boolean removeCategory(String categoryName){
        if (getCategoryToAssessmentMap().containsKey(categoryName)){
            getCategoryToAssessmentMap().remove(categoryName);
            deleteCategoryFromWeightMap(categoryName);
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
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeAssessment(String categoryName, String assessmentName, double assessmentGrade){
        if (getCategoryToAssessmentMap().containsKey(categoryName)){
            ArrayList<Object[]> assessmentList = getCategoryToAssessmentMap().get(categoryName);
            boolean removeIndex = false;
            int indexToRemove = 0;
            for (int i = 0; i < assessmentList.size(); i++){
                Object[] assessment = assessmentList.get(i);
                if ((assessment[0]).equals(assessmentName) && ((double) assessment[1] == assessmentGrade)){
                    indexToRemove = i;
                    removeIndex = true;
                }
            }
            if (removeIndex){
                assessmentList.remove(indexToRemove);
                return true;
            }
        }
        return false;
    }
    private HashMap<String, ArrayList<Object[]>> getCategoryToAssessmentMap() {
        return categoryToAssessmentMap;
    }

    private void setCategoryToAssessmentMap(HashMap<String, ArrayList<Object[]>> categoryToAssessmentMap) {
        this.categoryToAssessmentMap = categoryToAssessmentMap;
    }

    private HashMap<String, Double> getCategoryToWeightMap() {
        return categoryToWeightMap;
    }

    private void setCategoryToWeightMap(HashMap<String, Double> categoryToWeightMap) {
        this.categoryToWeightMap = categoryToWeightMap;
    }

    public void addWeight(String categoryName, Double weight){
        if (getCategoryToWeightMap().containsKey(categoryName)){
            getCategoryToWeightMap().put(categoryName, weight);
        }
    }

    public void deleteCategoryFromWeightMap(String categoryName){
        getCategoryToWeightMap().remove(categoryName);
    }

    public Double getWeight(String categoryName){
        return getCategoryToWeightMap().get(categoryName);
    }
}
