package com.enoca.mapper;

import com.enoca.model.api.response.CourseResponse;
import com.enoca.model.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseResponseMapper {

    public CourseResponse map(Course course){
        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getCreditScore());
    }
}
