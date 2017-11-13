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
        Calculator g = new Calculator();
//        System.out.println(g.gradeWithPercent(77, 0.5)[0] + " " +
//                g.gradeWithPercent(77, 0.5)[1]);

        HashMap<ArrayList<Double>, Double> hm = new HashMap<>();
        ArrayList<Double> a = new ArrayList<>();
        a.add(89.0);

        ArrayList<Double> b = new ArrayList<>();
        b.add(90.0);
        ArrayList<Double> c = new ArrayList<>();
        c.add(45.0);
        ArrayList<Double> d = new ArrayList<>();
        d.add(100.0);
        ArrayList<Double> e = new ArrayList<>();
        e.add(70.0);


        hm.put(a, 15.0);
        hm.put(b, 1.0);
        hm.put(c, 1.0);
        hm.put(d, 1.0);
        hm.put(e, 10.0);

        System.out.println(Calculator.calculateWeightedAverage(hm));
        Object[] o = Calculator.gradeWithPercent(Calculator.calculateWeightedAverage(hm));
        System.out.println(o[0] +" " + o[1]);

    }
}
