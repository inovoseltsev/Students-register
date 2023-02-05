package com.inovoseltsev.studentsregister.repository.impl;

import com.inovoseltsev.studentsregister.entity.Student;
import com.inovoseltsev.studentsregister.repository.PaginationRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PaginationRepositoryImpl implements PaginationRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Student> getObjectsPage(int limit, int offset) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from Student order by studentId");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getAllObjectsPage(int offset) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from Student order by studentId");
            query.setFirstResult(offset);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
