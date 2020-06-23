package tech.tystnad.works.service;

import tech.tystnad.works.model.Todo;

import java.util.List;

public interface TodoService {
    Todo add(Todo todo);
    Todo delete(String id);
    List<Todo> findRelated(String userId);
    List<Todo> findByGroupId(String groupId);
    Todo findById(String id);
    Todo update(Todo todo);
}
