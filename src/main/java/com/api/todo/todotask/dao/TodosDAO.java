package com.api.todo.todotask.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.api.todo.todotask.representations.Todos;

import io.dropwizard.hibernate.AbstractDAO;

public class TodosDAO extends AbstractDAO<Todos> {

  public TodosDAO(SessionFactory factory) {
    super(factory);
  }

  @SuppressWarnings("unchecked")
  public List<Todos> getAll() {
    return (List<Todos>) currentSession().createCriteria(Todos.class).list();
  }

  public Todos findById(int id) {
    return (Todos) currentSession().get(Todos.class, id);
  }

  public void delete(Todos todos) {
    currentSession().delete(todos);
  }

  public void update(Todos todos) {
    currentSession().saveOrUpdate(todos);
  }

  public Todos insert(Todos todos) {
    return persist(todos);
  }
}