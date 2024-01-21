package com.example.javaee.exame.example.service;

import com.example.javaee.exame.example.entity.Student;
import com.example.javaee.exame.example.repository.StudentRepository;
import com.example.javaee.exame.example.util.ActionEnum;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.*;

@ApplicationScoped
public class StudentService {

  @Inject
  private Logger logger;

  @Inject
  private StudentRepository studentRepository;

  @Inject
  private Event<AbstractMap.SimpleEntry<ActionEnum, String>> event;

  public List<Student> getAllStudent() {
    logger.info("Ricerca di tutti gli studenti");
    return studentRepository.getAllStudent();
  }

  public Student getStudent(Long id) {
    logger.infof("Ricerca dello studente con id: [%d]", id);
    return studentRepository.getStudent(id);
  }

  public void createStudent(Student student) {
    logger.infof("Creazione studente con nome: [%s] e cognome: [%s]", student.getName(),
        student.getSurname());
    studentRepository.createStudent(student);
    event.fire(new AbstractMap.SimpleEntry<>(ActionEnum.POST, student.toString()));
  }

  public void updateStudent(Student student) {
    logger.infof("Aggiornamento studente con id: [%d] nome: [%s] e cognome: [%s]", student.getId(),
        student.getName(), student.getSurname());
    studentRepository.updateStudent(student);
    event.fire(new AbstractMap.SimpleEntry<>(ActionEnum.PUT, student.toString()));
  }

  public void remove(Long id) {
    logger.infof("Cancellazione studente con id: [%d]", id);
    studentRepository.removeStudent(id);
    event.fire(new AbstractMap.SimpleEntry<>(ActionEnum.DELETE, String.valueOf(id)));
  }
}
