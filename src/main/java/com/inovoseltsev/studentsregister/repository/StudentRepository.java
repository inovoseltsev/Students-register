package com.inovoseltsev.studentsregister.repository;

import com.inovoseltsev.studentsregister.entity.Student;

import java.util.List;

public interface StudentRepository {
    void create(Student student);

    void update(Student student);

    void remove(Student student);

    Student findById(Long id);

    List<Student> findAll();
}
