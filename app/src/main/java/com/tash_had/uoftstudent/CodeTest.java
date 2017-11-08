package com.tash_had.uoftstudent;

import org.junit.Test;
import org.junit.runners.JUnit4;

/**
 * Created by tash-had on 2017-11-07.
 */

@org.junit.runner.RunWith(JUnit4.class)
public class CodeTest{

    @Test
    public void testGPACalc(){
        GPACalculator g = new GPACalculator();
        System.out.println(g.letterGradeAndGPAWithPercentAndCredits(77, 0.5)[0] + " " +
                g.letterGradeAndGPAWithPercentAndCredits(77, 0.5)[1]);
    }
}
