package com.api.todo.todotask.exceptions;

public class TodosNotFoundException extends Exception{
    private long todoId;
    public TodosNotFoundException(long todoId){

            super(String.format("Todo item is not found with ID '%s'",todoId));
        }

}
