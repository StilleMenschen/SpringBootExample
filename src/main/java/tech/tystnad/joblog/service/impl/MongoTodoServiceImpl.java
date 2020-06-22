package tech.tystnad.joblog.service.impl;

import tech.tystnad.joblog.repository.TodoRepository;
import tech.tystnad.joblog.model.Todo;
import tech.tystnad.joblog.service.TodoService;
import tech.tystnad.joblog.model.User;
import tech.tystnad.joblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoTodoServiceImpl implements TodoService {
    private final TodoRepository repository;
    private final UserRepository userRepository;

    @Autowired
    MongoTodoServiceImpl(
            TodoRepository repository,
            UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Todo add(Todo todo) {
        return repository.insert(todo);
    }

    @Override
    public Todo delete(String id) {
        Todo deletedTodo = repository.findById(id).get();
        repository.deleteById(id);
        return deletedTodo;
    }

    @Override
    public List<Todo> findRelated(String userId) {
        final User user = userRepository.findById(userId).get();
        return repository.findByParticipantsContaining(user);
    }

    @Override
    public List<Todo> findByGroupId(String groupId) {
        return repository.findByGroupId(groupId);
    }

    @Override
    public Todo findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public Todo update(Todo todo) {
        repository.save(todo);
        return todo;
    }
}
