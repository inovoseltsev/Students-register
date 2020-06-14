package com.inovoseltsev.studentsregister.service;

import com.inovoseltsev.studentsregister.entity.Student;

import java.util.List;

public interface PaginationService {
    List<Student> getObjectsPage(int pageNumber);

    int getLimit();

    void setLimit(int limit);

    int getNumberOfTablePages();

    void setNumberOfTablePages(int number);

    void countTablePages();
}
