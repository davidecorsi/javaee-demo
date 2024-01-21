package com.example.javaee.exame.example.repository;

import com.example.javaee.exame.example.entity.Student;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class StudentRepository {

  @Inject
  private EntityManager entityManager;

  public List<Student> getAllStudent() {
    return entityManager.createQuery("select s from Student s", Student.class).getResultList();
  }

  public Student getStudent(Long id) {
    TypedQuery<Student> query = entityManager.createQuery("select s from Student s where id = :id", Student.class);
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  public void createStudent(Student student) {
    entityManager.persist(student);
  }

  public void updateStudent(Student student) {
    entityManager.merge(student);
  }

  public void removeStudent(Long id) {
    Student student = getStudent(id);
    entityManager.remove(student);
  }
}
