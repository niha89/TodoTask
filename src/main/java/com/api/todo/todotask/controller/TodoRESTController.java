package com.api.todo.todotask.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.api.todo.todotask.basicauth.User;
import com.api.todo.todotask.dao.TestDB;
import com.api.todo.todotask.representations.Todos;

import io.dropwizard.auth.Auth;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoRESTController {

  private final Validator validator;

  public TodoRESTController(Validator validator) {
    this.validator = validator;
  }

  @PermitAll
  @GET
  public Response getAllTodoItems(@Auth User user) {
    return Response.ok(TestDB.getAllItems()).build();
  }

  @RolesAllowed("USER")
  @GET
  @Path("/{id}")
  public Response getTodoItemById(@PathParam("id") int id, @Auth User user) {
    // You can validate here if user is watching his record
    /*
     * if(id != user.getId()){
     * //Not allowed
     * }
     */
    Todos employee = TestDB.getTodoItem(id);
    if (employee != null)
      return Response.ok(employee).build();
    else
      return Response.status(Status.NOT_FOUND).build();
  }

  @RolesAllowed("ADMIN")
  @POST
  public Response createTodoItem(Todos employee, @Auth User user) throws URISyntaxException {
    // validation
    Set<ConstraintViolation<Todos>> violations = validator.validate(employee);
    Todos e = TestDB.getTodoItem(employee.getId());
    if (violations.size() > 0) {
      ArrayList<String> validationMessages = new ArrayList<String>();
      for (ConstraintViolation<Todos> violation : violations) {
        validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
      }
      return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
    }
    if (e != null) {
      TestDB.updateTodoItem(employee.getId(), employee);
      return Response.created(new URI("/todos/" + employee.getId()))
          .build();
    } else
      return Response.status(Status.NOT_FOUND).build();
  }

  @PUT
  @Path("/{id}")
  public Response updateTodoItemById(@PathParam("id") int id, Todos todos) {
    // validation
    Set<ConstraintViolation<Todos>> violations = validator.validate(todos);
    Todos e = TestDB.getTodoItem(id);
    if (violations.size() > 0) {
      ArrayList<String> validationMessages = new ArrayList<String>();
      for (ConstraintViolation<Todos> violation : violations) {
        validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
      }
      return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
    }
    if (e != null) {
      todos.setId(id);
      TestDB.updateTodoItem(id, todos);
      return Response.ok(todos).build();
    } else
      return Response.status(Status.NOT_FOUND).build();
  }

  @DELETE
  @Path("/{id}")
  public Response removeTodoItemById(@PathParam("id") int id) {
    Todos employee = TestDB.getTodoItem(id);
    if (employee != null) {
      TestDB.removeTodoItem(id);
      return Response.ok().build();
    } else
      return Response.status(Status.NOT_FOUND).build();
  }
}