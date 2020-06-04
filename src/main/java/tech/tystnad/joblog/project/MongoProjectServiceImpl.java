package tech.tystnad.joblog.project;

import tech.tystnad.joblog.taskgroup.TaskGroup;
import tech.tystnad.joblog.taskgroup.TaskGroupRepository;
import tech.tystnad.joblog.user.User;
import tech.tystnad.joblog.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

/**
 * Created by wangpeng on 2017/4/18.
 */
@Service
public class MongoProjectServiceImpl implements ProjectService {
  final MongoProjectRepository repository;
  final UserRepository userRepository;
  final TaskGroupRepository taskGroupRepository;

  @Autowired
  public MongoProjectServiceImpl(MongoProjectRepository repository, UserRepository userRepository,
      TaskGroupRepository taskGroupService) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.taskGroupRepository = taskGroupService;
  }

  @Override
  public Project add(Project project, String userId) {
    final User user = userRepository.findById(userId).get();
    project.setOwner(user);
    project.setMembers(asList(user));
    project.setEnabled(true);
    project.setArchived(false);
    TaskGroup plan = new TaskGroup();
    plan.setName("计划");
    plan.setOrder(0);
    TaskGroup inProgress = new TaskGroup();
    inProgress.setName("进行中");
    inProgress.setOrder(1);
    TaskGroup done = new TaskGroup();
    done.setName("已完成");
    done.setOrder(2);
    project.setGroups(asList(taskGroupRepository.insert(plan), taskGroupRepository.insert(inProgress),
        taskGroupRepository.insert(done)));
    return repository.insert(project);
  }

  @Override
  public Project delete(String id) {
    final Project project = repository.findById(id).get();
    repository.deleteById(id);
    return project;
  }

  @Override
  public Page<Project> findRelated(String userId, boolean enabled, boolean archived, Pageable pageable) {
//        final User user =  userRepository.findOne(userId);
    return repository.findRelated(new ObjectId(userId), enabled, archived, pageable);
  }

  @Override
  public Project findById(String id) {
    return repository.findById(id).get();
  }

  @Override
  public Project update(Project project) {
    repository.save(project);
    return project;
  }
}
