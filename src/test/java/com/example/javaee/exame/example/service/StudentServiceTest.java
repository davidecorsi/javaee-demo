package com.example.javaee.exame.example.service;

import com.example.javaee.exame.example.entity.Student;
import com.example.javaee.exame.example.repository.StudentRepository;
import com.example.javaee.exame.example.util.ActionEnum;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.enterprise.event.Event;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private Logger logger;

  @Mock
  private StudentRepository studentRepository;

  @Mock
  private Event<AbstractMap.SimpleEntry<ActionEnum, Object>> event;

  @InjectMocks
  private StudentService studentService;

  @Test
  void getAllStudentTest() {

    List<Student> studentList = Collections.singletonList(generateMockStudent());
    Mockito.when(studentRepository.getAllStudent()).thenReturn(studentList);

    List<Student> studentListReceived = studentService.getAllStudent();

    assertEquals(1L, studentListReceived.size());
  }

  @Test
  void getStudentTest() {
    Mockito.when(studentRepository.getStudent(1L)).thenReturn(generateMockStudent());

    Student studentReceived = studentService.getStudent(1L);

    assertEquals(1L, studentReceived.getId());
  }

  @Test
  void createStudentTest() {
    studentService.createStudent(generateMockStudent());

    Mockito.verify(studentRepository, Mockito.times(1)).createStudent(Mockito.any());
    Mockito.verify(event, Mockito.times(1)).fire(Mockito.any());
  }

  @Test
  void updateStudentTest() {
    studentService.updateStudent(generateMockStudent());

    Mockito.verify(studentRepository, Mockito.times(1)).updateStudent(Mockito.any());
    Mockito.verify(event, Mockito.times(1)).fire(Mockito.any());
  }

  @Test
  void removeStudentTest() {
    studentService.remove(1L);

    Mockito.verify(studentRepository, Mockito.times(1)).removeStudent(1L);
    Mockito.verify(event, Mockito.times(1)).fire(Mockito.any());
  }

  private Student generateMockStudent() {
    Student student = new Student();
    student.setId(1L);
    student.setName("Davide");
    student.setSurname("Corsi");
    return student;
  }
}