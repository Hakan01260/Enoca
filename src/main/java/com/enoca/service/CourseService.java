package com.enoca.service;

import com.enoca.model.api.request.AddStudentToCourseRequest;
import com.enoca.model.api.request.CourseCreateRequest;
import com.enoca.model.api.request.CourseUpdateRequest;
import com.enoca.model.api.response.CourseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<CourseResponse> findAll();
    CourseResponse findById(Long id);
    CourseResponse create(CourseCreateRequest request);
    CourseResponse deleteById(Long id);
    CourseResponse update(CourseUpdateRequest request);
    void addStudentToCourse(Long id, AddStudentToCourseRequest request);
    void deleteStudentInCourse(Long id, Long studentId);
    List<CourseResponse> findCoursesByOrderedCreditScore();

}
