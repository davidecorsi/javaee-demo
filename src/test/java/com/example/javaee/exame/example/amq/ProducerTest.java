package com.example.javaee.exame.example.amq;

import com.example.javaee.exame.example.util.ActionEnum;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProducerTest {

  @Mock
  private Logger logger;

  @Mock
  private JMSContext jmsContext;

  @Mock
  private Queue queue;

  @InjectMocks
  private Producer producer;

  @Test
  void generateMessageTest() {
    JMSProducer jmsProducer = Mockito.mock(JMSProducer.class);
    Mockito.when(jmsContext.createProducer())
        .thenReturn(jmsProducer);
    Mockito.when(jmsContext.createTextMessage(Mockito.any(String.class)))
        .thenReturn(Mockito.mock(TextMessage.class));

    producer.generateMessage(new AbstractMap.SimpleEntry<>(ActionEnum.POST, ""));

    Mockito.verify(jmsContext).createProducer();
    Mockito.verify(jmsContext).createTextMessage(Mockito.any(String.class));
    Mockito.verify(jmsProducer).send(Mockito.eq(queue), Mockito.any(TextMessage.class));
  }
}