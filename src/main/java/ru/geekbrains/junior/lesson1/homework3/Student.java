package ru.geekbrains.junior.lesson1.homework3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements Serializable {
    private String name;
    private int age;
    @JsonProperty("gpa")
    transient double GPA;

    public Student(){

    }
    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return GPA;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }
}
