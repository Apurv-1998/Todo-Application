package com.fullstack.todo.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.todo.application.entity.TodoEntity;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {

	TodoEntity findTodoByTodoId(String todoId);

}
