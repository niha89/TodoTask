package com.api.todo.todotask.configurations;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class HibernateConfiguration extends Configuration {

  @Valid
  @NotNull
  @JsonProperty("database")
  private DataSourceFactory database = new DataSourceFactory();

  public void setDatabase(DataSourceFactory database) {
    this.database = database;
  }

  public DataSourceFactory getDatabaseAppDataSourceFactory() {
    return database;
  }
}