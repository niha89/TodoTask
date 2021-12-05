package com.api.todo.todotask.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
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
import com.api.todo.todotask.dao.TodosDAO;
import com.api.todo.todotask.representations.Todos;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/todos")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class TodoController {
  private final Validator validator;
  TodosDAO todoDAO;

  public TodoController(TodosDAO todosDAO, Validator validator) {
    this.todoDAO = todosDAO;
    this.validator = validator;
  }

  @RolesAllowed("USER")
  @GET
  @UnitOfWork
  @PermitAll
  public Response getAllTodoList(@Auth User user) {
    return Response.ok(todoDAO.getAll()).build();
  }

  @GET
  @Path("/{id}")
  @UnitOfWork
  @RolesAllowed("USER")
  public Response getTodoById(@PathParam("id") Integer id, @Auth User user) {
    Todos todos = todoDAO.findById(id);
    if (todos != null)
      return Response.ok(todos).build();
    else
      return Response.status(Status.NOT_FOUND).build();
  }

  @POST
  @UnitOfWork
  @RolesAllowed("ADMIN")
  public Response addTodoItem(@Valid Todos person, @Auth User user) throws URISyntaxException {
    // validation
    Set<ConstraintViolation<Todos>> violations = validator.validate(person);
    if (violations.size() > 0) {
      ArrayList<String> validationMessages = new ArrayList<String>();
      for (ConstraintViolation<Todos> violation : violations) {
        validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
      }
      return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
    }
    Todos newPerson = todoDAO.insert(person);
    return Response.ok(newPerson).build();
  }

  @PUT
  @Path("/{id}")
  @UnitOfWork
  public Response updateTodoById(@PathParam("id") Integer id, @Valid Todos todoDetails) {
    // validation
    Set<ConstraintViolation<Todos>> violations = validator.validate(todoDetails);
    Todos todos = todoDAO.findById(id);
    if (violations.size() > 0) {
      ArrayList<String> validationMessages = new ArrayList<String>();
      for (ConstraintViolation<Todos> violation : violations) {
        validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
      }
      return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
    }
    if (todos != null) {
      todos.setName(todoDetails.getName());
      todos.setDescription(todoDetails.getDescription());
      todos.setTasks(todoDetails.getTasks());
      todoDAO.update(todos);
      return Response.ok(todos).build();
    } else
      return Response.status(Status.NOT_FOUND).build();
  }

  @DELETE
  @Path("/{id}")
  @UnitOfWork
  public Response deleteTodoById(@PathParam("id") Integer id) {
    Todos todo = todoDAO.findById(id);
    if (todo != null) {
      todoDAO.delete(todo);
      return Response.ok().build();
    } else
      return Response.status(Status.NOT_FOUND).build();
  }
}
