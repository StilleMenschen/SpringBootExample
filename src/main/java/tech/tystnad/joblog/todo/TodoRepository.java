package tech.tystnad.joblog.todo;

import tech.tystnad.joblog.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{
    List<Todo> findByParticipantsContaining(User user);
    List<Todo> findByGroupId(String id);
}
