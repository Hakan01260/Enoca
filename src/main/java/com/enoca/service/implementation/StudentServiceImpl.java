package com.enoca.service.implementation;

import com.enoca.mapper.StudentResponseMapper;
import com.enoca.model.api.request.StudentCreateRequest;
import com.enoca.model.api.request.StudentUpdateRequest;
import com.enoca.model.api.response.CourseResponse;
import com.enoca.model.api.response.StudentCoursesResponse;
import com.enoca.model.api.response.StudentResponse;
import com.enoca.model.entity.Course;
import com.enoca.model.entity.Student;
import com.enoca.model.exception.NotFoundException;
import com.enoca.repository.CourseRepository;
import com.enoca.repository.StudentRepository;
import com.enoca.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentResponseMapper studentResponseMapper;

    @Transactional(readOnly = true)
    public List<StudentResponse> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentResponseMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentResponse findById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student not found by id :" + id);
        }
        return studentResponseMapper.map(optionalStudent.get());
    }

    @Transactional(readOnly = true)
    public StudentCoursesResponse findStudentCoursesById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student not found by id :" + id);
        }
        Student student = optionalStudent.get();
        List<CourseResponse> courseResponseList = student.getCourses()
                .stream()
                .map(course -> new CourseResponse(course.getId(), course.getName(), course.getCreditScore()))
                .collect(Collectors.toList());
        return new StudentCoursesResponse(student.getId(), student.getName(), student.getBirtOfDate(),
                student.getAddress(), student.getGender(), courseResponseList);
    }

    @Transactional
    public StudentResponse create(StudentCreateRequest request) {
        Student student = new Student();
        student.setAddress(request.getAddress());
        student.setName(request.getName());
        student.setGender(request.getGender());
        student.setBirtOfDate(request.getBirtOfDate());

        Student createdStudent = studentRepository.save(student);
        return studentResponseMapper.map(createdStudent);
    }

    @Transactional
    public StudentResponse update(StudentUpdateRequest request) {
        Optional<Student> optionalStudent = studentRepository.findById(request.getId());
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student not found by id: " + request.getId());
        }
        Student student = optionalStudent.get();
        student.setAddress(request.getAddress());
        student.setName(request.getName());
        student.setGender(request.getGender());
        student.setBirtOfDate(request.getBirtOfDate());

        Student updatedStudent = studentRepository.save(student);
        return studentResponseMapper.map(updatedStudent);
    }

    @Transactional
    public StudentResponse deleteById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student not found by id: " + id);
        }
        Student student = optionalStudent.get();
        List<Course> courses = student.getCourses();
        for (Course course : courses) {
            course.removeStudent(student);
            courseRepository.save(course);
        }
        studentRepository.deleteById(id);
        return studentResponseMapper.map(student);
    }
}
