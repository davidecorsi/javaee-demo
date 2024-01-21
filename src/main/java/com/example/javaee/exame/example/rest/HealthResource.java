package com.example.javaee.exame.example.rest;

import com.example.javaee.exame.example.application.interceptor.Log;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("health")
@RequestScoped
@Log
public class HealthResource {

  @GET
  @PermitAll
  public Response health() {
    return Response.ok().build();
  }
}
