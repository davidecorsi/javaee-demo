package com.example.javaee.exame.example.application.provider;

import javax.ejb.EJBException;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<EJBException> {

  @Override
  public Response toResponse(EJBException e) {
    if(e.getCause() instanceof ConstraintViolationException) {
      ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
      JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
      for(ConstraintViolation<?> cv: ex.getConstraintViolations()) {
        jsonObjectBuilder.add("Error", cv.getMessage());
      }
      return Response.status(400).entity(jsonObjectBuilder.build()).build();
    }
    throw e;
  }
}
