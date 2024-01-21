package com.example.javaee.exame.example.mapper;

import com.example.javaee.exame.example.entity.AuditLog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuditLogMapperTest {

  private final AuditLogMapper mapper = new AuditLogMapper();

  @Test
  void toEntityTest() {
    AuditLog auditLogExpect = new AuditLog();
    auditLogExpect.setValue("test");

    AuditLog auditLogActual = mapper.toEntity("test");

    assertTrue(new ReflectionEquals(auditLogExpect).matches(auditLogActual));
  }
}