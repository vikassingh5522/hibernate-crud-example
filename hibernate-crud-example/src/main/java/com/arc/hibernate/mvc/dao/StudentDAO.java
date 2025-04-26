package com.arc.hibernate.mvc.dao;

import com.arc.hibernate.mvc.model.Student;
import java.util.List;

public interface StudentDAO {
    void save(Student student);
    void update(Student student);
    void delete(int id);
    Student getById(int id);
    List<Student> getAll();
}
