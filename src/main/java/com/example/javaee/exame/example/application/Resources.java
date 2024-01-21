package com.example.javaee.exame.example.application;


import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.enterprise.inject.Produces;

@Dependent
public class Resources {

  @Produces
  @PersistenceContext(unitName = "javaee-example")
  private EntityManager entityManager;

  @Produces
  public Logger getLogger(InjectionPoint injectionPoint) {
    return Logger.getLogger(injectionPoint.getMember().getDeclaringClass());
  }
}
