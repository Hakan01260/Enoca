package com.enoca.repository;

import com.enoca.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c order by c.creditScore asc")
    List<Course> findCoursesByOrderedCreditScore();


}