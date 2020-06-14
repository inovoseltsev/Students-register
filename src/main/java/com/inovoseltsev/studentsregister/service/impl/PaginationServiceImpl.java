package com.inovoseltsev.studentsregister.service.impl;

import com.inovoseltsev.studentsregister.entity.Student;
import com.inovoseltsev.studentsregister.repository.PaginationRepository;
import com.inovoseltsev.studentsregister.repository.StudentRepository;
import com.inovoseltsev.studentsregister.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaginationServiceImpl implements PaginationService {

    private int limit = 5;
    private int numberOfTablePages;

    @Autowired
    private PaginationRepository paginationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Student> getObjectsPage(int pageNumber) {
        if (pageNumber == 1) {
            return paginationRepository.getObjectsPage(limit, 0);
        }
        int offset = (pageNumber - 1) * limit;

        if (numberOfTablePages == pageNumber) {
            return paginationRepository.getAllObjectsPage(offset);
        }
        return paginationRepository.getObjectsPage(limit, offset);
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public void setLimit(int limit) {
        if (limit == 0) {
            throw new ArithmeticException("Limit was set to 0");
        }
        this.limit = limit;
    }

    @Override
    public int getNumberOfTablePages() {
        return numberOfTablePages;
    }

    @Override
    public void setNumberOfTablePages(int number) {
        this.numberOfTablePages = number;
    }

    @Transactional(readOnly = true)
    public void countTablePages() {
        int numberOfAllElements = studentRepository.findAll().size();
        if (numberOfAllElements % limit != 0) {
            this.numberOfTablePages = (numberOfAllElements / limit) + 1;
        } else {
            this.numberOfTablePages = numberOfAllElements / limit;
        }
    }
}
