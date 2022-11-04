package com.enoca.mapper;

import com.enoca.model.api.response.StudentResponse;
import com.enoca.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentResponseMapper {

    public StudentResponse map(Student student){
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getBirtOfDate(),
                student.getAddress(),
                student.getGender());
    }
}
