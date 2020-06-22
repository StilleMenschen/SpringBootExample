package tech.tystnad.joblog.repository;

import tech.tystnad.joblog.model.Todo;
import tech.tystnad.joblog.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{
    List<Todo> findByParticipantsContaining(User user);
    List<Todo> findByGroupId(String id);
}
