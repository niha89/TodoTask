package com.api.todo.todotask.representations;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tasks")
@Entity
public class Tasks implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /*public Tasks(){
  }

  public Tasks(long id, String name, String description) {
      this.id = id;
      this.name = name;
      this.description = description;
  }*/
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_id")
  private int id;

  @Column(name = "task_name")
  private String name;

  @Column(name = "task_desc")
  private String description;

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
}
