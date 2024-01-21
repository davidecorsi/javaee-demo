package com.example.javaee.exame.example.amq;

import com.example.javaee.exame.example.service.AuditLogService;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConsumerTest {

  @Mock
  private Logger logger;

  @Mock
  private AuditLogService auditLogService;

  @InjectMocks
  private Consumer consumer;

  @Test
  void onMessageTest() throws JMSException {
    TextMessage message = Mockito.mock(TextMessage.class);
    Mockito.when(message.getText())
        .thenReturn("test");

    consumer.onMessage(message);

    Mockito.verify(auditLogService).saveAuditLog("test");
  }

  @Test
  void onMessageExceptionTest() throws JMSException {
    TextMessage message = Mockito.mock(TextMessage.class);
    Mockito.when(message.getText())
        .thenThrow(new RuntimeException());

    assertThrowsExactly(RuntimeException.class, () -> consumer.onMessage(message));
  }

  @Test
  void onMessageNoSaveTest() {
    Message message = Mockito.mock(Message.class);

    consumer.onMessage(message);

    Mockito.verify(auditLogService, Mockito.never()).saveAuditLog(Mockito.any(String.class));
  }
}