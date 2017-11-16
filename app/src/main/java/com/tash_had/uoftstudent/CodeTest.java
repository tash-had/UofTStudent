package com.tash_had.uoftstudent;

import org.junit.Test;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tash-had on 2017-11-07.
 */

@org.junit.runner.RunWith(JUnit4.class)
public class CodeTest{

    @Test
    public void testGPACalc(){
//        Calculator g = new Calculator();
////        System.out.println(g.gradeWithPercent(77, 0.5)[0] + " " +
////                g.gradeWithPercent(77, 0.5)[1]);
//
//        HashMap<ArrayList<Double>, Double> hm = new HashMap<>();
//        ArrayList<Double> a = new ArrayList<>();
//        a.add(89.0);
//
//        ArrayList<Double> b = new ArrayList<>();
//        b.add(90.0);
//        ArrayList<Double> c = new ArrayList<>();
//        c.add(45.0);
//        ArrayList<Double> d = new ArrayList<>();
//        d.add(100.0);
//        ArrayList<Double> e = new ArrayList<>();
//        e.add(70.0);
//
//
//        hm.put(a, 15.0);
//        hm.put(b, 1.0);
//        hm.put(c, 1.0);
//        hm.put(d, 1.0);
//        hm.put(e, 10.0);
//
//        System.out.println(Calculator.calculateWeightedAverage(hm));
//        Object[] o = Calculator.gradeWithPercent(Calculator.calculateWeightedAverage(hm));
//        System.out.println(o[0] +" " + o[1]);
//

        // Add Random Entries
        Course course1 = new Course("course1", 0.5);
        course1.addCategory("Midterm", 10.0);
        course1.addAssessment("Midterm", "Midterm", 96.00);

        course1.addCategory("Final", 40.0);

        course1.addCategory("A1", 5.0);
        course1.addAssessment("A1", "a1", 100.0);

        course1.addCategory("A2", 10.0);

        course1.addCategory("X", 8.0);
        course1.addAssessment("X", "X1", 100.0);
        course1.addAssessment("X", "X2", 100.0);
        course1.addAssessment("X", "X3", 100.0);
        course1.addAssessment("X", "X4", 100.0);

        course1.addCategory("randAssignment1", 10.0);
        course1.addCategory("randAssignment2", 17.0);

        Course course2 = new Course("course2", 0.5);
        course2.addCategory("a1", 10.0);
        course2.addAssessment("a1", "a1", 91.89);
        course2.addCategory("a2", 10.0);
        course2.addAssessment("a2","a2", 40.0);
        course2.addCategory("a3", 10.0);
        course2.addCategory("midterm", 30.0);
        course2.addAssessment("midterm", "m1", 59.0);
        course2.addCategory("final", 40.0);


        Course course3 = new Course("course3", 0.5);
        course3.addCategory("test1", 20.0);
        course3.addAssessment("test1", "t1", 51.28);

        course3.addCategory("test2", 20.0);

        course3.addCategory("X1", 10.0);
        course3.addAssessment("X1", "X1", 85.0);

        course3.addCategory("X2", 10.0);

        course3.addCategory("final", 35.0);

        course3.addCategory("assignment", 4.0);
        course3.addAssessment("assignment", "assignment", 100.0);

        course3.addCategory("other", 1.0);
        course3.addAssessment("other", "other", 100.0);

        Course course4 = new Course("course4", 0.5);
        course4.addCategory("PS1", 15.0);
        course4.addAssessment("PS1", "ps1", 89.0);

        course4.addCategory("PS2", 15.0);
        course4.addCategory("PS3", 15.0);

        course4.addCategory("Final", 40.0);

        course4.addCategory("onepercentassignment1", 1.0);
        course4.addAssessment("onepercentassignment1", "onepercentassignment1", 90.0);

        course4.addCategory("onepercentassignment2", 1.0);
        course4.addAssessment("onepercentassignment2", "onepercentassignment2", 45.0);

        course4.addCategory("onepercentassignment3", 1.0);
        course4.addAssessment("onepercentassignment3", "onepercentassignment3", 100.0);

        course4.addCategory("onepercentassignment4", 1.0);
        course4.addCategory("onepercentassignment5", 1.0);

        course4.addCategory("Midterm", 10.0);
        course4.addAssessment("Midterm", "midterm", 70.0);


        Course[] courses ={course1, course2, course3, course4};
        System.out.println(Calculator.calculateGPA(courses));



    }
}
