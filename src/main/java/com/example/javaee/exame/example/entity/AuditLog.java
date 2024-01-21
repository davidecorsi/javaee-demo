package com.example.javaee.exame.example.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "AUDIT_LOG")
public class AuditLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "value")
  private String value;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AuditLog)) return false;
    AuditLog auditLog = (AuditLog) o;
    return Objects.equals(getId(), auditLog.getId()) && Objects.equals(getValue(), auditLog.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getValue());
  }

  @Override
  public String toString() {
    return "AuditLog{" +
        "id=" + id +
        ", value='" + value + '\'' +
        '}';
  }
}
