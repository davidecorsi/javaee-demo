package com.example.javaee.exame.example.application.filter;

import org.jboss.logging.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Provider
public class MDCFilter implements ContainerRequestFilter, ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    String user = Optional.ofNullable(containerRequestContext.getSecurityContext())
        .map(SecurityContext::getUserPrincipal)
        .map(Principal::getName)
        .orElse("");
    MDC.put("user", user);
    if(MDC.get("transactionId") == null) {
      MDC.put("transactionId", UUID.randomUUID());
    }
  }

  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
    MDC.clear();
  }
}
