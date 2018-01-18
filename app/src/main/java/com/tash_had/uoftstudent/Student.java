package com.tash_had.uoftstudent;

import java.util.ArrayList;

/**
 * Created by tash-had on 2017-11-22.
 */

public class Student {
    private ArrayList<Course> courses;

    public Student(String name){
        courses = new ArrayList<>();
    }

    public Object[] getCourses() {
        return courses.toArray(new Course[courses.size()]);
    }

    public Course getCourse(String courseName) throws CourseNotFoundException {
        for (Course c : courses){
            if (c.getCourseName().equals(courseName)){
                return c;
            }
        }
        throw new CourseNotFoundException(courseName + " was not found in this students courses");
    }

    public void addNewCourse(Course course){
        courses.add(course);
    }
}
