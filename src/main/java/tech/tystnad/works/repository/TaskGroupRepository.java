package tech.tystnad.works.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.tystnad.works.model.TaskGroup;

/**
 * 任务组存储
 */
@Repository
public interface TaskGroupRepository extends MongoRepository<TaskGroup, String> {}
