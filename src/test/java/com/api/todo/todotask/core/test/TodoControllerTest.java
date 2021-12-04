/*package com.api.todo.todotask.core.test;

import com.api.todo.todotask.controller.TodoController;
import com.api.todo.todotask.dao.TodosDAO;
import com.api.todo.todotask.representations.Todos;
import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

*//**
 * Tests {@link io.dropwizard.testing.junit.ResourceTestRule}
 *//*
public class TodoControllerTest {

    private static final TodosDAO todoDAO = mock(TodosDAO.class);
    private static final Todos todo = mock(Todos.class);


    static {
        Logger.getLogger("com.sun.jersey").setLevel(Level.OFF);
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TodoController(todoDAO, null))
            .build();

    @Test
    public void getAll() throws Exception {
        List<Todos> todos = new ArrayList<>();
        todos.add(todo.setId(1).setName("person1"));
        todos.add(new Todos().setId(2).setName("person2"));
        when(todoDAO.getAll()).thenReturn(persons);

        List<Todos> result = resources.client().resource("/Todo").get(new GenericType<List<Todos>>() {});

        assertEquals(2, result.size());
        assertEquals("person1", result.get(0).getName());
    }

    @Test
    public void get() throws Exception {
        when(todoDAO.findById(1)).thenReturn(
                new Person()
                        .setId(1)
                        .setName("person1")
        );

        Todos todos = resources.client().resource("/person/1").get(Todos.class);

        assertEquals("person1",todos.getName());
    }

    @Test
    public void update() throws Exception {
        Person person = PersonTests.getPerson();

        Person updatedPerson = resources.client().resource("/person/10")
                .type(MediaType.APPLICATION_JSON)
                .put(Person.class, person);

        assertEquals(person.getId(), updatedPerson.getId());
        assertEquals(person.getName(), updatedPerson.getName());
        verify(todoDAO, times(1)).update(person);
    }

    @Test(expected = ConstraintViolationException.class)
    public void update_invalid_person() throws Exception {
        Person person = PersonTests.getPerson().setName(null);

        Person updatedPerson = resources.client().resource("/person/10")
                .type(MediaType.APPLICATION_JSON)
                .put(Person.class, person);
    }

    @Test()
    public void add() throws Exception {
        Person newPerson = PersonTests.getPerson();
        when(todoDAO.insert(any(Todos.class))).thenReturn(newPerson);

        Person person = resources.client().resource("/person")
                .type(MediaType.APPLICATION_JSON)
                .post(Person.class, newPerson);

        assertEquals(newPerson.getName(), person.getName());
        verify(todoDAO, times(1)).insert(any(Todos.class));
    }

    @Test(expected = ConstraintViolationException.class)
    public void add_invalid_person() throws Exception {
      Todos newPerson = PersonTests.getPerson().setName(null);

      Todos person = resources.client().resource("/person")
                .type(MediaType.APPLICATION_JSON)
                .post(Todos.class, newPerson);
    }

    @Test()
    public void delete() throws Exception {
        resources.client().resource("/person/1").delete();
        verify(todoDAO, times(1)).delete(any(Todos.class));
    }
}*/