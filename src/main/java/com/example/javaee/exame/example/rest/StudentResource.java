package com.example.javaee.exame.example.rest;

import com.example.javaee.exame.example.application.interceptor.Log;
import com.example.javaee.exame.example.entity.Student;
import com.example.javaee.exame.example.service.StudentService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Log
public class StudentResource {

    @Inject
    private StudentService studentService;

    @GET
    @RolesAllowed({"user", "admin"})
    public List<Student> getAll() {
        return studentService.getAllStudent();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({"user", "admin"})
    public Student getStudent(@PathParam("id") Long id) {
        return studentService.getStudent(id);
    }

    @POST
    @RolesAllowed({"admin"})
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.ok().build();
    }

    @PUT
    @RolesAllowed({"admin"})
    public Response updateStudent(Student student) {
        studentService.updateStudent(student);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed({"admin"})
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.remove(id);
        return Response.ok().build();
    }
}
