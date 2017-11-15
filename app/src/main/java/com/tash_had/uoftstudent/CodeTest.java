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
        Course csc207 = new Course();
        csc207.addCategory("Midterm", 10.0);
        csc207.addAssessment("Midterm", "Midterm", 96.00);

        csc207.addCategory("Final", 40.0);

        csc207.addCategory("A1", 5.0);
        csc207.addAssessment("A1", "a1", 100.0);

        csc207.addCategory("A2", 10.0);

        csc207.addCategory("Lab", 8.0);
        csc207.addAssessment("Lab", "lab1", 100.0);
        csc207.addAssessment("Lab", "lab2", 100.0);
        csc207.addAssessment("Lab", "lab3", 100.0);
        csc207.addAssessment("Lab", "lab4", 100.0);

        csc207.addCategory("PP1", 10.0);
        csc207.addCategory("PP2", 17.0);

        Course csc236 = new Course();
        csc236.addCategory("PS1", 15.0);
        csc236.addAssessment("PS1", "ps1", 89.0);

        csc236.addCategory("PS2", 15.0);
        csc236.addCategory("PS3", 15.0);

        csc236.addCategory("Final", 40.0);

        csc236.addCategory("EX1", 1.0);
        csc236.addAssessment("EX1", "ex1", 90.0);

        csc236.addCategory("EX2", 1.0);
        csc236.addAssessment("EX2", "ex2", 45.0);

        csc236.addCategory("EX3", 1.0);
        csc236.addAssessment("EX3", "ex3", 100.0);

        csc236.addCategory("EX4", 1.0);
        csc236.addCategory("EX5", 1.0);

        csc236.addCategory("Midterm", 10.0);
        csc236.addAssessment("Midterm", "midterm", 70.0);

        Course psy270 = new Course();
        psy270.addCategory("test1", 20.0);
        psy270.addAssessment("test1", "t1", 51.28);

        psy270.addCategory("test2", 20.0);
        psy270.addCategory("lab1", 10.0);
        psy270.addAssessment("lab1", "lab1", 85.0);
        psy270.addCategory("lab2", 10.0);
        psy270.addCategory("final", 35.0);
        psy270.addCategory("tophat", 4.0);
        psy270.addAssessment("tophat", "tophat", 100.0);
        psy270.addCategory("other", 1.0);
        psy270.addAssessment("other", "other", 100.0);

        Course sta247 = new Course();
        sta247.addCategory("a1", 10.0);
        sta247.addAssessment("a1", "a1", 91.89);
        sta247.addCategory("a2", 10.0);
        sta247.addAssessment("a2","a2", 45.0);
        sta247.addCategory("a3", 10.0);
        sta247.addCategory("midterm", 20.0);
        sta247.addAssessment("midterm", "m1", 59.0);
        sta247.addCategory("final", 40.0);

        Course[] courses ={csc236};
        System.out.println(Calculator.calculateGPA(courses));



    }
}
