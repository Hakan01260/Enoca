package com.enoca.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "credit_score")
    private int creditScore;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();

    public Course(String courseName, int creditScore) {
        this.name = courseName;
        this.creditScore = creditScore;
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

}
