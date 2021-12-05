package com.api.todo.todotask.app;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.api.todo.todotask.basicauth.AppAuthenticator;
import com.api.todo.todotask.basicauth.AppAuthorizer;
import com.api.todo.todotask.basicauth.User;
import com.api.todo.todotask.configurations.HibernateConfiguration;
import com.api.todo.todotask.controller.TodoController;
import com.api.todo.todotask.dao.TodosDAO;
import com.api.todo.todotask.healthcheck.AppHealthCheck;
import com.api.todo.todotask.healthcheck.HealthCheckController;
import com.api.todo.todotask.representations.Tasks;
import com.api.todo.todotask.representations.Todos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<HibernateConfiguration> {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  @Override
  public void initialize(Bootstrap<HibernateConfiguration> bootstrap) {
    bootstrap.addBundle(hibernate);
  }

  private final HibernateBundle<HibernateConfiguration> hibernate = new HibernateBundle<HibernateConfiguration>(
      Todos.class, Tasks.class) {
    @Override
    public DataSourceFactory getDataSourceFactory(HibernateConfiguration configuration) {
      return configuration.getDatabaseAppDataSourceFactory();
    }
  };

  @Override
  public void run(HibernateConfiguration configuration, Environment environment) throws Exception {
    LOGGER.info("Registering REST resources");
    final TodosDAO todosDAO = new TodosDAO(hibernate.getSessionFactory());
    final TodoController todoResource = new TodoController(todosDAO, environment.getValidator());
    environment.jersey().register(todoResource);

    final Client client = new JerseyClientBuilder(environment)
        .build("DemoRESTClient");

    /*
     * Application health check
     */
    environment.healthChecks().register("APIHealthCheck", new AppHealthCheck(client));

    /*
     * Run multiple health checks
     */
    environment.jersey().register(new HealthCheckController(environment.healthChecks()));

    /*
     * Setup Basic Security
     */
    environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
        .setAuthenticator(new AppAuthenticator())
        .setAuthorizer(new AppAuthorizer())
        .setRealm("App Security")
        .buildAuthFilter()));
    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    environment.jersey().register(RolesAllowedDynamicFeature.class);
  }

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

}