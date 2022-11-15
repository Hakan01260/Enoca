package com.enoca.controller;

import com.enoca.model.api.request.StudentCreateRequest;
import com.enoca.model.api.request.StudentUpdateRequest;
import com.enoca.model.api.response.StudentCoursesResponse;
import com.enoca.model.api.response.StudentResponse;
import com.enoca.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponse> getStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping("/{id}/courses")
    public StudentCoursesResponse getStudentCoursesById(@PathVariable Long id) {
        return studentService.findStudentCoursesById(id);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody StudentCreateRequest request) {
        return new ResponseEntity<>(studentService.create(request), HttpStatus.CREATED);
    }

    @PutMapping
    public StudentResponse update(@RequestBody StudentUpdateRequest request) {
        return studentService.update(request);
    }

    @DeleteMapping("/{id}")
    public StudentResponse delete(@PathVariable Long id) {
        return studentService.deleteById(id);
    }
}
