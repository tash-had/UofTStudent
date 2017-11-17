package com.tash_had.uoftstudent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by tash-had on 2017-11-07.
 */



public class Calculator {

    private static double undocumentedGPA;
    private static double undocumentedCredits;

    /*
         TODO: Calculate with Old grades
     */
    /**
     * Set the GPA of this student prior to using this app
     *
     * @param gpa the gpa
     */
    public static void setUndocumentedGPA(double gpa) {
        undocumentedGPA = gpa;
    }

    /**
     * Set the number of credits this student completed prior to using this app
     *
     * @param credits the number of credits
     */
    public static void setUndocumentedCredits(double credits) {
        undocumentedCredits = credits;
    }

    /**
     * Get the GPA the student had before using this app
     *
     * @return the gpa
     */
    private static double getUndocumentedGPA(){
        return undocumentedGPA;
    }

    /**
     * Get the # of credits the student had before using this app
     *
     * @return the number of credits
     */
    private static double getUndocumentedCredits(){
        return undocumentedCredits;
    }

    // For courses.
    // GradesInCategory -> Weight of Category  = gradesToWeightMap
    // weights must add up to 100
    public static double calculateWeightedAverage(HashMap<ArrayList<Double>, Double> gradesToWeightMap){
        ArrayList<Double> weightedCategAvg = new ArrayList<>();
        double validWeightSum = 0.0;
        for (ArrayList<Double> grades : gradesToWeightMap.keySet()){
            double weight = gradesToWeightMap.get(grades);
            if (!grades.isEmpty()){
                // calculate average of grades in this category
                double avg = (sumArrayList(grades))/(grades.size());
                // multiply category average by category weight and store value
                weightedCategAvg.add((percentAsDecimal(avg) *
                        percentAsDecimal(weight)));
                validWeightSum += percentAsDecimal(weight);

            }
        }
        double sumAverages = sumArrayList(weightedCategAvg);
        if (validWeightSum > 0.0){
            return (sumAverages/validWeightSum)*100.0;
        }
        // if validWeightSum = 0, then no grades have yet been entered, so return -1.
        return -1.0;
    }

    public static double percentAsDecimal(double percent){
        return (percent/100.0);
    }

    // Ask how to make this with <?> on bb
    private static double sumArrayList(ArrayList<Double> arrayList){
        Double sum = 0.0;
        for (Double d : arrayList) {
            sum += d;
        }
        return sum;
    }

    /**
     * Return grade information corresponding to the given percent
     *
     * @param percent a grade percentage
     * @return an array with the letter grade at index 0 and gpa at index 1
     */
    public static Object[] gradeWithPercent(double percent){
        percent = Math.round(percent);
        Object[][] gradeBoundsArr = {
                {90, 100, "A+", 4.0}, {85, 89, "A", 4.0}, {80, 84, "A-", 3.7},
                {77, 79, "B+", 3.3}, {73, 76, "B", 3.0}, {70, 72, "B-", 2.7},
                {67, 69, "C+", 2.3}, {63, 66, "C", 2.0}, {60, 62, "C-", 1.7},
                {57, 59, "D+", 1.3}, {53, 56, "D", 1.0}, {50, 52, "D-", 0.7},
                {0, 49, "F", 0.0}
        };

        for (Object[] gradeArr : gradeBoundsArr){
            int lowerBound = (int)gradeArr[0];
            int upperBound = (int)gradeArr[1];
            if (isBetweenIncl(percent, lowerBound, upperBound)){
                String letterGrade = (String)gradeArr[2];
                double gpv = (double)gradeArr[3]; // grade point value

                return new Object[]{letterGrade, gpv};
            }
        }
        return new Object[]{"N/A", 0};
    }

    /**
     * Check if a number is in within a range(inclusive)
     *
     * @param numToCheck the number to check
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     * @return is the number given within range
     * */
    private static boolean isBetweenIncl(double numToCheck, double lowerBound, double upperBound){
        return numToCheck >= lowerBound && numToCheck <= upperBound;
    }

    public static double calculateGPA(Course[] courses){
        ArrayList<Double> totals = new ArrayList<>();
        double creditSum = 0.0;
        double oldGPA = getUndocumentedGPA();
        double oldCredits = getUndocumentedCredits();

        for (Course course : courses){
            double courseAverage = course.getCourseAverage();
            Object[] gradeInfo = gradeWithPercent(courseAverage);

            if (!course.isCreditNoCredit() && ! gradeInfo[0].equals("N/A")){
                double gradePointValue = (double) gradeInfo[1];
                double courseCreditWeight = course.getCourseCreditWeight();
                creditSum += courseCreditWeight;
                totals.add(gradePointValue * courseCreditWeight);
            }
        }
        if (oldGPA > 0 && oldCredits > 0){
            creditSum += oldCredits;
            totals.add(oldGPA * oldCredits);
        }
        return sumArrayList(totals)/creditSum;
    }
}
