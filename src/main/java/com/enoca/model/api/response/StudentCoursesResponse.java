package com.enoca.model.api.response;

import com.enoca.model.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCoursesResponse {

    private Long id;

    private String name;

    private LocalDate birtOfDate;

    private String address;

    private String gender;

    private List<CourseResponse> courses = new ArrayList<>();

    public StudentCoursesResponse(Long id, String name, LocalDate birtOfDate, String address, String gender, List<CourseResponse> courses) {
        this.id = id;
        this.name = name;
        this.birtOfDate = birtOfDate;
        this.address = address;
        this.gender = gender;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirtOfDate() {
        return birtOfDate;
    }

    public void setBirtOfDate(LocalDate birtOfDate) {
        this.birtOfDate = birtOfDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<CourseResponse> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseResponse> courses) {
        this.courses = courses;
    }
}
