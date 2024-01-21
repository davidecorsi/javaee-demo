package com.example.javaee.exame.example.mapper;

import com.example.javaee.exame.example.entity.AuditLog;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditLogMapper {

  public AuditLog toEntity(String value) {
    AuditLog auditLog = new AuditLog();
    auditLog.setValue(value);
    return auditLog;
  }
}
