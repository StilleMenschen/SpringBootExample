package tech.tystnad.works.service.impl;

import tech.tystnad.works.repository.TodoRepository;
import tech.tystnad.works.model.Todo;
import tech.tystnad.works.service.TodoService;
import tech.tystnad.works.model.User;
import tech.tystnad.works.repository.UserRepository;
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
