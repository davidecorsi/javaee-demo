package com.example.javaee.exame.example.amq;

import com.example.javaee.exame.example.util.ActionEnum;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.util.AbstractMap;

@ApplicationScoped
public class Producer {

  @Inject
  private Logger logger;

  @Inject
  private JMSContext jmsContext;

  @Resource(mappedName = "java:/jms/queue/LogQueue")
  private Queue queue;

  public void generateMessage(@Observes AbstractMap.SimpleEntry<ActionEnum, String> entry) {
    logger.debugf("Generazione messaggio action: [%s], value: [%s]", entry.getKey(), entry.getValue());
    JMSProducer jmsProducer = jmsContext.createProducer();
    TextMessage textMessage = jmsContext.createTextMessage(String.format(entry.getKey().getDescription(), entry.getValue()));
    jmsProducer.send(queue, textMessage);
  }
}
