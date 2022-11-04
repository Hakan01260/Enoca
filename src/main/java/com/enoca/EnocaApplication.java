package com.enoca;

import com.enoca.model.entity.Course;
import com.enoca.model.entity.Student;
import com.enoca.repository.CourseRepository;
import com.enoca.repository.StudentRepository;
import com.enoca.service.CourseService;
import com.enoca.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class EnocaApplication implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(EnocaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Course course1 = new Course("Mathematics",80);
        Course course2 = new Course("English",50);

        Student student1 = new Student("Hakan", LocalDate.of(1999, Month.APRIL, 12), "Adana", "male");
        Student student2 = new Student("Oğuzhan", LocalDate.of(2002, Month.MARCH, 05), "Mersin", "male");

        course1.setStudents(Arrays.asList(student1,student2));
        course2.setStudents(List.of(student2));

        student1.setCourses(List.of(course1));
        student2.setCourses(Arrays.asList(course1,course2));

        courseRepository.save(course1);
        courseRepository.save(course2);

        studentRepository.save(student1);
        studentRepository.save(student2);
    }
}
