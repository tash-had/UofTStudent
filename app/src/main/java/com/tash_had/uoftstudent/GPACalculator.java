package com.tash_had.uoftstudent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tash-had on 2017-11-07.
 */



public class GPACalculator {
    private double oldGpa;


    private void calculateWeightedAverage(HashMap<Double, ArrayList<Double>> weightToGradesHashMap){

        for (double weight : weightToGradesHashMap.keySet()){
            for (double grade : weightToGradesHashMap.get(weight))
        }
    }

    /**
     * Return an object array with a letter grade at index 0 and a GPA at index 1, both
     * corresponding to the given percent and credit weight
     *
     * @param percent a grade percentage
     * @param creditWeight the weight of the course in which the percent was earened
     */
    private Object[] letterGradeAndGPAWithPercentAndCredits(double percent, double creditWeight){
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
                double gpv = (double)gradeArr[3];
                double gpa = gpv*creditWeight;

                return new Object[]{letterGrade, gpa};
            }
        }
        return null;
    }
    /**
     * Check if a number is in within a range(inclusive)
     *
     * @param numToCheck the number to check
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     * @return is the number given within range
     * */
    private boolean isBetweenIncl(double numToCheck, double lowerBound, double upperBound){
        return numToCheck >= lowerBound && numToCheck <= upperBound;
    }
}
