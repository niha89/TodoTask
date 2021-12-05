/*package com.api.todo.todotask.core.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import com.api.todo.todotask.controller.TodoController;
import com.api.todo.todotask.dao.TodosDAO;
import com.api.todo.todotask.representations.Todos;

*//**
 * Tests {@link io.dropwizard.testing.junit.ResourceTestRule}
 *//*

import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.ResourceTestRule;

*//**
 * Tests {@link io.dropwizard.testing.junit.ResourceTestRule}
 *//*
public class TodoControllerTest {

  private static final TodosDAO todosDAO = mock(TodosDAO.class);
  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder()
      .addResource(new TodoController(todosDAO, null))
      .build();

  @Test
  public void getAll() throws Exception {
    List<Todos> todos = new ArrayList<>();
    Todos obj = TodosTest.getTodos();
    todos.add(obj);
    when(todosDAO.getAll()).thenReturn(todos);

    List<Todos> result = resources.client().resource("/Todo").get(new GenericType<List<Todos>>() {});

    assertEquals(2, todos.size());
    assertEquals("person1", todos.get(0).getName());
  }

  @Test
  public void get() throws Exception {
    when(todosDAO.findById(1)).thenReturn(
        TodosTest.getTodos());
    assertEquals("person1", TodosTest.getTodos().getName());
  }

  @Test
  public void update() throws Exception {
    Todos person = TodosTest.getTodos();

    Todos updatedTodo = resources.client().resource("/Todo/10")
        .type(MediaType.APPLICATION_JSON)
        .put(Todos.class, person);

    assertEquals(person.getId(), updatedTodo.getId());
    assertEquals(person.getName(), updatedTodo.getName());
    verify(todosDAO, times(1)).update(person);
  }

  @Test(expected = ConstraintViolationException.class)
  public void update_invalid_todo() throws Exception {

    Todos updatedTodo = resources.client().resource("/Todo/10")
        .type(MediaType.APPLICATION_JSON)
        .put(Todos.class, TodosTest.getTodos().setName(null));
  }

  @Test()
  public void add() throws Exception {
    Todos newTodo = TodosTest.getTodos();
    when(todosDAO.insert(any(Todos.class))).thenReturn(newTodo);

    Todos person = resources.client().resource("/Todo")
        .type(MediaType.APPLICATION_JSON)
        .post(Todos.class, newTodo);

    assertEquals(newTodo.getName(), person.getName());
    verify(todosDAO, times(1)).insert(any(Todos.class));
  }

  @Test(expected = ConstraintViolationException.class)
  public void add_invalid_todo() throws Exception {
    Todos todo = resources.client().resource("/Todo")
        .type(MediaType.APPLICATION_JSON)
        .post(Todos.class, TodosTest.getTodos().setName(null));
  }

  @Test()
  public void delete() throws Exception {
    resources.client().resource("/Todo/1").delete();
    verify(todosDAO, times(1)).delete(any(Todos.class));
  }
}
*/