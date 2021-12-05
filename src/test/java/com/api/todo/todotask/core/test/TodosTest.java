package com.api.todo.todotask.core.test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.api.todo.todotask.representations.Tasks;
import com.api.todo.todotask.representations.Todos;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class TodosTest {

  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  private static final String NULL_ERROR_MESSAGE = "may not be null";

  private static Validator validator;

  @BeforeClass
  public static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  //@Test
  public void serializesToJson() throws Exception {
    final Todos todoObj = getTodos();
    assertEquals(fixture("../resources/Todo.json"), MAPPER.writeValueAsString(todoObj));
  }

  //@Test
  public void deserializesFromJSON() throws Exception {
    final Todos todoObj = getTodos();
    assertEquals(todoObj, MAPPER.readValue(fixture("../resources/Todo.json"), Todos.class));
  }

  // Should be replaced with individual class field validator tests
  //@Test
  public void validate_not_null() throws Exception {
    final Todos todoObj = getTodos();

    Set<ConstraintViolation<Todos>> constraintViolations =
        validator.validate(todoObj);

    assertEquals(2, constraintViolations.size());
    assertEquals(NULL_ERROR_MESSAGE, constraintViolations.iterator().next().getMessage());
  }

  public static Todos getTodos() {
    Todos todo = new Todos();
    todo.setId(1);
    todo.setName("John");
    todo.setDescription("creating a list of activities to be done");
    List<Tasks> tasks = new ArrayList<>();
    Tasks taskObj = new Tasks();
    taskObj.setId(7);
    taskObj.setName("hiking");
    taskObj.setDescription("need to go for hiking to clear my brain");
    tasks.add(taskObj);
    todo.setTasks(tasks);
    return todo;
  }
}
