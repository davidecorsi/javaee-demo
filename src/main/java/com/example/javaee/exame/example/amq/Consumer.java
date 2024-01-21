package com.example.javaee.exame.example.amq;

import com.example.javaee.exame.example.service.AuditLogService;
import org.jboss.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "ConsumerLog", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/LogQueue"),
})
public class Consumer implements MessageListener {

  @Inject
  private Logger logger;

  @Inject
  private AuditLogService auditLogService;

  @Override
  public void onMessage(Message message) {
    if(message instanceof TextMessage) {
      TextMessage rcv = (TextMessage) message;
      try {
        String messageValue = rcv.getText();
        logger.infof("Message received: %s", messageValue);
        auditLogService.saveAuditLog(messageValue);
      } catch (JMSException e) {
        logger.error("Error message received", e);
      }
    } else {
      logger.warnf("Invalid message received: %s", message.getClass().getName());
    }
  }
}
