package com.example.javaee.exame.example.repository;

import com.example.javaee.exame.example.entity.AuditLog;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class AuditLogRepository {

  @Inject
  private EntityManager entityManager;

  public void createAuditLog(AuditLog auditLog) {
    entityManager.persist(auditLog);
  }
}
