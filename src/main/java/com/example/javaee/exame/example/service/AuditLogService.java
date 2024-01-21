package com.example.javaee.exame.example.service;

import com.example.javaee.exame.example.entity.AuditLog;
import com.example.javaee.exame.example.mapper.AuditLogMapper;
import com.example.javaee.exame.example.repository.AuditLogRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuditLogService {

  @Inject
  private Logger logger;

  @Inject
  private AuditLogMapper auditLogMapper;

  @Inject
  private AuditLogRepository auditLogRepository;

  public void saveAuditLog(String value) {
    logger.infof("Creazione auditLog con valore: [%s]", value);
    AuditLog auditLog = auditLogMapper.toEntity(value);
    auditLogRepository.createAuditLog(auditLog);
  }
}
