package tech.tystnad.joblog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.tystnad.joblog.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(final String username);
}
