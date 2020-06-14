package com.inovoseltsev.studentsregister.service;

import com.inovoseltsev.studentsregister.entity.Student;

import java.util.List;

public interface StudentService {
    void create(Student student);

    void update(Student student);

    void remove(Student student);

    List<Student> findAll();
}
