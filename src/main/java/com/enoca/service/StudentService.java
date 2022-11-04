package com.enoca.service;

import com.enoca.model.api.request.StudentCreateRequest;
import com.enoca.model.api.request.StudentUpdateRequest;
import com.enoca.model.api.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<StudentResponse> findAll();
    StudentResponse findById(Long id);
    StudentResponse create(StudentCreateRequest request);
    StudentResponse update(StudentUpdateRequest request);
    StudentResponse deleteById(Long id);
}
