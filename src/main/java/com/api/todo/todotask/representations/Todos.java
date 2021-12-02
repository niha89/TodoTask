package com.api.todo.todotask.representations;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "todos")
@Entity
public class Todos implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /*public Todos() {}

  public Todos(int id, String name, String description, List<Tasks> tasks) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.tasks = tasks;
  }*/

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "todo_item_id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany(targetEntity = Tasks.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "todo_task_fk", referencedColumnName = "todo_item_id")
  private List<Tasks> tasks;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Tasks> getTasks() {
    return tasks;
  }

  public void setTasks(List<Tasks> tasks) {
    this.tasks = tasks;
  }

}
