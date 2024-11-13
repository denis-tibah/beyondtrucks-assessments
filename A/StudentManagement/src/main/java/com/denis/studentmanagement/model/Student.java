package com.denis.studentmanagement.model;

import jakarta.persistence.*;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String name;
    private Integer age;
    private String sexuality;

    public Student() {}

    public Student(Long studentId, String name, Integer age, String sexuality) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.sexuality = sexuality;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSexuality() {
        return sexuality;
    }

    public void setSexuality(String sexuality) {
        this.sexuality = sexuality;
    }
}
