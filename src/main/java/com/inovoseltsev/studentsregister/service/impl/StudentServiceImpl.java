package com.inovoseltsev.studentsregister.service.impl;

import com.inovoseltsev.studentsregister.entity.Student;
import com.inovoseltsev.studentsregister.repository.StudentRepository;
import com.inovoseltsev.studentsregister.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Period;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void create(Student student) {
        if (isValid(student)) {
            student.setAge(countStudentAge(student.getBirthday()));
            studentRepository.create(student);
        } else {
            throw new IllegalArgumentException("Wrong student data!");
        }
    }

    @Override
    @Transactional
    public void update(Student student) {
        if (isValid(student)) {
            student.setAge(countStudentAge(student.getBirthday()));
            studentRepository.update(student);
        } else {
            throw new IllegalArgumentException("Wrong student data!");
        }
    }

    @Override
    @Transactional
    public void remove(Student student) {
        if (student != null) {
            studentRepository.remove(student);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    private boolean isValid(Student student) {
        return student != null
                && student.getFirstName() != null
                && student.getLastName() != null
                && student.getBirthday() != null
                && student.getFaculty() != null;
    }

    private int countStudentAge(Date birthday) {
        Date currentDate = new Date(new java.util.Date().getTime());
        Period period = Period.between(birthday.toLocalDate(), currentDate.toLocalDate());
        return period.getYears();
    }
}
