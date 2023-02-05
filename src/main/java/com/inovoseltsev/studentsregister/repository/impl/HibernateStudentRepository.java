package com.inovoseltsev.studentsregister.repository.impl;

import com.inovoseltsev.studentsregister.entity.Student;
import com.inovoseltsev.studentsregister.repository.StudentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HibernateStudentRepository implements StudentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Student student) {
        try {
            sessionFactory.getCurrentSession().save(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {
        try {
            sessionFactory.getCurrentSession().update(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Student student) {
        try {
            sessionFactory.getCurrentSession().remove(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(Long id) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from Student where studentId =: id");
            query.setParameter("id", id);
            return (Student) query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        try {
            List<Student> students = sessionFactory.getCurrentSession().createQuery("from Student order by studentId").list();
            return students;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
