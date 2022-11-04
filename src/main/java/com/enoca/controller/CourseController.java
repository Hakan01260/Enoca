package com.enoca.controller;

import com.enoca.model.api.request.AddStudentToCourseRequest;
import com.enoca.model.api.request.CourseCreateRequest;
import com.enoca.model.api.request.CourseUpdateRequest;
import com.enoca.model.api.response.CourseResponse;
import com.enoca.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponse> getAllCourses(@RequestParam(required = false) boolean isOrdered) {
        if (isOrdered) {
            return courseService.findCoursesByOrderedCreditScore();
        }
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "New Course adding method")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseCreateRequest request) {
        return new ResponseEntity<>(courseService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public CourseResponse deleteCourseById(@PathVariable Long id) {
        return courseService.deleteById(id);
    }

    @PutMapping
    public CourseResponse updateCourse(@RequestBody CourseUpdateRequest request) {
        return courseService.update(request);
    }

    @PostMapping("/{id}/addStudent")
    public void addStudentToCourse(@PathVariable Long id, @RequestBody AddStudentToCourseRequest request) {
        courseService.addStudentToCourse(id, request);
    }

    @DeleteMapping("/{id}/deleteStudent/{studentId}")
    public void deleteStudentInCourse(@PathVariable Long id, @PathVariable Long studentId) {
        courseService.deleteStudentInCourse(id, studentId);
    }
}
