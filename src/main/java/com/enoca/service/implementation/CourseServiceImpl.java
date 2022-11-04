package com.enoca.service.implementation;

import com.enoca.mapper.CourseResponseMapper;
import com.enoca.model.api.request.AddStudentToCourseRequest;
import com.enoca.model.api.request.CourseCreateRequest;
import com.enoca.model.api.request.CourseUpdateRequest;
import com.enoca.model.api.response.CourseResponse;
import com.enoca.model.entity.Course;
import com.enoca.model.entity.Student;
import com.enoca.model.exception.NotFoundException;
import com.enoca.repository.CourseRepository;
import com.enoca.repository.StudentRepository;
import com.enoca.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final CourseResponseMapper courseResponseMapper;

    @Transactional(readOnly = true)
    public List<CourseResponse> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(courseResponseMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseResponse findById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new NotFoundException("Course not found by id :" + id);
        }
        return courseResponseMapper.map(optionalCourse.get());
    }

    @Transactional
    public CourseResponse create(CourseCreateRequest request) {
        Course course = new Course();
        course.setName(request.getCourseName());
        course.setCreditScore(request.getCreditScore());

        Course createdCourse = courseRepository.save(course);
        return courseResponseMapper.map(createdCourse);
    }

    @Transactional
    public CourseResponse deleteById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new NotFoundException("Course not found by id :" + id);
        }
        Course course = optionalCourse.get();
        List<Student> students = course.getStudents();
        for (Student student : students) {
            student.remove(course);
            studentRepository.save(student);
        }
        courseRepository.delete(course);
        return courseResponseMapper.map(course);
    }

    @Transactional
    public CourseResponse update(CourseUpdateRequest request) {
        Optional<Course> optionalCourse = courseRepository.findById(request.getId());
        if (optionalCourse.isEmpty()) {
            throw new NotFoundException("Course not found by id :" + request.getId());
        }
        Course course = optionalCourse.get();
        course.setName(request.getName());
        course.setCreditScore(request.getCreditScore());
        Course updatedCourse = courseRepository.save(course);
        return courseResponseMapper.map(updatedCourse);
    }

    @Transactional
    public void addStudentToCourse(Long id, AddStudentToCourseRequest request) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new NotFoundException("Course not found by id :" + id);
        }
        Course course = optionalCourse.get();

        Optional<Student> optionalStudent = studentRepository.findById(request.getStudentId());
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student not found by id: " + request.getStudentId());
        }
        Student student = optionalStudent.get();
        course.addStudent(student);
        courseRepository.save(course);
    }

    @Transactional
    public void deleteStudentInCourse(Long id, Long studentId) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new NotFoundException("Course not found by id :" + id);
        }
        Course course = optionalCourse.get();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student not found by id: " + studentId);
        }
        course.removeStudent(optionalStudent.get());
        courseRepository.save(course);
    }

    @Transactional
    public List<CourseResponse> findCoursesByOrderedCreditScore() {
        return courseRepository.findCoursesByOrderedCreditScore()
                .stream()
                .map(courseResponseMapper::map)
                .collect(Collectors.toList());
    }
}
