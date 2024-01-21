package com.example.javaee.exame.example.service;

import com.example.javaee.exame.example.entity.AuditLog;
import com.example.javaee.exame.example.mapper.AuditLogMapper;
import com.example.javaee.exame.example.repository.AuditLogRepository;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceTest {
  @Mock
  private Logger logger;

  @Mock
  private AuditLogMapper auditLogMapper;

  @Mock
  private AuditLogRepository auditLogRepository;

  @InjectMocks
  private AuditLogService auditLogService;

  @Test
  void saveAuditLogTest() {
    Mockito.when(auditLogMapper.toEntity("test"))
        .thenReturn(new AuditLog());

    auditLogService.saveAuditLog("test");

    Mockito.verify(auditLogRepository).createAuditLog(Mockito.any(AuditLog.class));
  }
}