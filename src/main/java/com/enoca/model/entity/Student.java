package com.enoca.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "birt_of_date")
    private LocalDate birtOfDate;

    private String address;

    private String gender;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    public Student(String name, LocalDate birtOfDate, String address, String gender) {
        this.name = name;
        this.birtOfDate = birtOfDate;
        this.address = address;
        this.gender = gender;
    }

    public void remove(Course course) {
        this.courses.remove(course);
    }
}
