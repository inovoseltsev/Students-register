package com.inovoseltsev.studentsregister.repository;

import com.inovoseltsev.studentsregister.entity.Student;

import java.util.List;

public interface PaginationRepository {
    List<Student> getObjectsPage(int limit, int offset);

    List<Student> getAllObjectsPage(int offset);

}
