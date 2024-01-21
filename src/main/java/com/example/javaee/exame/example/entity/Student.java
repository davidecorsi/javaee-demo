package com.example.javaee.exame.example.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "STUDENT")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotBlank(message = "Il campo name non può essere vuoto")
  private String name;

  @Column(name = "surname")
  @NotBlank(message = "Il campo surname non può essere vuoto")
  private String surname;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Student)) return false;
    Student student = (Student) o;
    return Objects.equals(getId(), student.getId()) && Objects.equals(getName(), student.getName()) && Objects.equals(getSurname(), student.getSurname());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getSurname());
  }
}
