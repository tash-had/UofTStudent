package com.tash_had.uoftstudent;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
    private LinkedHashMap<String, ArrayList<Object[]>> categoryToAssessmentMap;
    private LinkedHashMap<String, Double> categoryToWeightMap;

    private String courseName;
    private double courseCreditWeight;

    private boolean creditNoCredit;

    private Student student;
    /*
     * TODO:
     * Fix lint errors
     * Write unit tests
     */

    public Course(String courseName, double courseCreditWeight, Student student){
        setCategoryToAssessmentMap(new LinkedHashMap<String, ArrayList<Object[]>>());
        setCategoryToWeightMap(new LinkedHashMap<String, Double>());
        setCourseName(courseName);
        setCourseCreditWeight(courseCreditWeight);
        this.student = student;
        this.student.addNewCourse(this);
    }

    /**
     * Get the name of this course.
     *
     * @return this courses name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set the name of this course
     *
     * @param courseName the name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Get the credit weught of this course
     *
     * @return the credit weight
     */
    public double getCourseCreditWeight() {
        if (isCreditNoCredit()){
            return 0;
        }else {
            return this.courseCreditWeight;
        }
    }

    /**
     * Set the credit weight of this course
     *
     * @param courseCreditWeight the weight to set
     */
    public void setCourseCreditWeight(double courseCreditWeight) {
        this.courseCreditWeight = courseCreditWeight;
    }

    /**
     * Get the average of this course
     *
     * @return the average
     */
    public double getCourseAverage(){
        // Create gradesToWeightMap
        return Calculator.calculateWeightedAverage(getGradesToWeightMap());
    }

    /**
     * Get a HashMap mapping a list of grades from each category, to the weight of that category
     *
     * @return the grades to weight map
     */
    private HashMap<ArrayList<Double>, Double> getGradesToWeightMap() {
        HashMap<ArrayList<Double>, Double> gradesToWeightMap = new HashMap<>();
        for (String category : getCategoryToAssessmentMap().keySet()) {
            ArrayList<Double> grades = new ArrayList<>();
            ArrayList<Object[]> assessments = getCategoryToAssessmentMap().get(category);
            if (assessments != null) {
                for (Object[] assessment : assessments) {
                    grades.add(getGradeWithAssessmentArray(assessment));
                }
                gradesToWeightMap.put(grades, getCategoryToWeightMap().get(category));
            }
        }
        return gradesToWeightMap;
    }

    /**
     * Add a grading category to this course
     *
     * @param categoryName the name of the grading category
     * @param weight how much weight this category holds (percent)
     * @return true if the category was added, false if it already exists
     */
    public boolean addCategory(String categoryName, Double weight){
        if (!getCategoryToAssessmentMap().containsKey(categoryName)){
            getCategoryToAssessmentMap().put(categoryName, new ArrayList<Object[]>());
            addCategoryWithWeight(categoryName, weight);
            return true;
        }
        return false;

    }

    /**
     * Edit the name of a grade category in this course
     *
     * @param oldName the name of the category to edit
     * @param newName the new name to give the category
     * @return true if the rename was done successfully, false if the category doesn't exist
     */
    public boolean editCategoryName(String oldName, String newName){
        if (getCategoryToAssessmentMap().containsKey(oldName)){
            ArrayList<Object[]> assessmentArrayList = getCategoryToAssessmentMap().get(oldName);
            getCategoryToAssessmentMap().remove(oldName);
            getCategoryToAssessmentMap().put(newName, assessmentArrayList);
            double weight = getCategoryWeight(oldName);
            deleteCategoryFromWeightMap(oldName);
            addCategoryWithWeight(newName, weight);
            return true;
        }
        return false;
    }

    /**
     * Remove a grading category from this course
     *
     * @param categoryName the category to remove
     * @return true if the rename was done successfully, false if the category doesn't exist.
     */
    public boolean removeCategory(String categoryName){
        if (getCategoryToAssessmentMap().containsKey(categoryName)){
            getCategoryToAssessmentMap().remove(categoryName);
            deleteCategoryFromWeightMap(categoryName);
            return true;
        }
        return false;
    }

    /**
     * Add an assessment to a category in this course
     *
     * @param category the category this asessment falls under
     * @param assessmentName the name of the asessment
     * @param grade the grade earned
     * @return true if the assessment was added successfully, false if the category doesn't exist
     */
    public boolean addAssessment(String category, String assessmentName, double grade){
        if (getCategoryToAssessmentMap().containsKey(category)){
            ArrayList<Object[]> assessmentsArrayList = getCategoryToAssessmentMap().get(category);
            Object[] assessmentAndGradArr = {assessmentName, grade};
            assessmentsArrayList.add(assessmentAndGradArr);
            return true;
        }
        return false;
    }

    /**
     * Edit an asessment in this course
     *
     * @param categoryName the category this assessment falls under
     * @param assessmentName the name of this assessment
     * @param newAssessmentName the new assessment name if there is one, null otherwise
     * @param newGrade the new grade if there is one, null otherwise
     * @return true if the assessment is edited successfully, false if at least one doesn't exist
     */
    public boolean editAssessment(String categoryName, String assessmentName,
                                  @Nullable String newAssessmentName, @Nullable Double newGrade){
        ArrayList<Object[]> assessmentList = getCategoryToAssessmentMap().get(categoryName);
        if (getCategoryToAssessmentMap().containsKey(categoryName)){
            for (int i = 0; i < assessmentList.size(); i++){
                Object[] assessment = assessmentList.get(i);
                String aName = getAssessmentNameWithAssessmentArray(assessment);
                if (aName.equals(assessmentName)){
                    String name = newAssessmentName == null ? aName : newAssessmentName;
                    Double grade = newGrade == null ? getGradeWithAssessmentArray(assessment) :
                            newGrade;
                    addAssessment(categoryName, name, grade);
                    removeAssessment(categoryName, aName, getGradeWithAssessmentArray(assessment));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove an assessment from this course
     *
     * @param categoryName the category which the assessment falls under
     * @param assessmentName the name of the assessment
     * @param assessmentGrade the grade recieved in the assessment
     * @return true if the assessment was removed, false if the assessment or category doesn't exist
     */
    public boolean removeAssessment(String categoryName, String assessmentName, double assessmentGrade){
        if (getCategoryToAssessmentMap().containsKey(categoryName)){
            ArrayList<Object[]> assessmentList = getCategoryToAssessmentMap().get(categoryName);
            boolean removeIndex = false;
            int indexToRemove = 0;
            for (int i = 0; i < assessmentList.size(); i++){
                Object[] assessment = assessmentList.get(i);
                if (getAssessmentNameWithAssessmentArray(assessment).equals(assessmentName) &&
                        (getGradeWithAssessmentArray(assessment) == assessmentGrade)){
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


    /**
     * Get the categoryToAssessmentMap.
     *
     * @return a hashmap mapping each category name, to an arraylist of assessments
     */
    private LinkedHashMap<String, ArrayList<Object[]>> getCategoryToAssessmentMap() {
        return categoryToAssessmentMap;
    }

    /**
     * Set the category to assessment map
     *
     * @param categoryToAssessmentMap the map to set to
     */
    private void setCategoryToAssessmentMap(LinkedHashMap<String, ArrayList<Object[]>>
                                                    categoryToAssessmentMap) {
        this.categoryToAssessmentMap = categoryToAssessmentMap;
    }

    /**
     * Get the categoryToWeightMap
     *
     * @return a hashmap mapping category names in this course to their corresponding weights
     */
    public LinkedHashMap<String, Double> getCategoryToWeightMap() {
        return categoryToWeightMap;
    }

    /**
     * Set the categoryToWeightMap
     *
     * @param categoryToWeightMap he hasmap to initialize categoryToWeightMap to
     */
    private void setCategoryToWeightMap(LinkedHashMap<String, Double> categoryToWeightMap) {
        this.categoryToWeightMap = categoryToWeightMap;
    }

    /**
     * Add a category with its corresponding weight in the categoryToWeight map
     *
     * @param categoryName the name of the category
     * @param weight the weight of the category
     */
    private void addCategoryWithWeight(String categoryName, Double weight){
        getCategoryToWeightMap().put(categoryName, weight);
    }

    /**
     * Edit the weight of a grading category in this course
     *
     * @param categoryName the name of the category to edit
     * @param newWeight the new weight of the category
     */
    public void editCategoryWeight(String categoryName, double newWeight){
        addCategoryWithWeight(categoryName, newWeight);
    }

    /**
     * Delete a category from the map of categories to weights
     *
     * @param categoryName the name of the category to remove.
     */
    public void deleteCategoryFromWeightMap(String categoryName){
        getCategoryToWeightMap().remove(categoryName);
    }

    /**
     * Get the weight of a grading category
     *
     * @param categoryName the category name
     * @return the weight of the given category
     */
    public Double getCategoryWeight(String categoryName){
        return getCategoryToWeightMap().get(categoryName);
    }

    /**
     * Given an array representing an assessment, return the name from that assessment
     *
     * @param assessmentArr the assessment
     * @return the asessment name
     */
    public String getAssessmentNameWithAssessmentArray(Object[] assessmentArr){
        return (String) assessmentArr[0];
    }

    /**
     * Given an array representing an assessment, return the grade from that assessment
     *
     * @param assessmentArr the assessment
     * @return the grade
     */
    public double getGradeWithAssessmentArray(Object[] assessmentArr){
        return (double) assessmentArr[1];
    }

    /**
     * Get whether this course is credit/no-credit (marks won't be included in calculations)
     * @return
     */
    public boolean isCreditNoCredit() {
        return creditNoCredit;
    }

    public void setCreditNoCredit(boolean creditNoCredit) {
        this.creditNoCredit = creditNoCredit;
    }

    public ArrayList<Object[]> getCategoryAssessments(String category){
        return getCategoryToAssessmentMap().get(category);
    }

    public double getCategoryAverage(String category){
        double sum = 0.0;
        ArrayList<Object[]> categoryAssessments = getCategoryAssessments(category);
        for (Object[] assessment : categoryAssessments){
            sum += (double) assessment[1];
        }
        return sum / categoryAssessments.size();
    }
}
