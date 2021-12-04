package com.api.todo.todotask.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.api.todo.todotask.representations.Tasks;
import com.api.todo.todotask.representations.Todos;

public class InMemoryDB {

  public static HashMap<Integer, Todos> todoList = new HashMap<>();
  /*static {
    List<Tasks> task1 = new ArrayList<>();
    Tasks t1 = new Tasks(1234L, "reading","have to read to crack interview");
    task1.add(t1);
    todoList.put(1, new Todos(1, "Lokesh", "Gupta", task1));
    todoList.put(2, new Todos(2, "Niha", "Bhagavatula", task1));
  }*/

  public static List<Todos> getAllItems() {
    return new ArrayList<Todos>(todoList.values());
  }

  public static Todos getTodoItem(int id) {
    return todoList.get(id);
  }

  public static void updateTodoItem(int id, Todos todo) {
    todoList.put(id, todo);
  }

  public static void removeTodoItem(int id) {
    todoList.remove(id);
  }
}