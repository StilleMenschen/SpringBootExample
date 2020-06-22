package tech.tystnad.joblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.tystnad.joblog.model.Project;
import tech.tystnad.joblog.service.ProjectService;
import tech.tystnad.joblog.model.TaskGroup;
import tech.tystnad.joblog.repository.TaskGroupRepository;
import tech.tystnad.joblog.service.TaskGroupService;

/**
 * Created by wangpeng on 2017/4/18.
 */
@Service
public class MongoTaskGroupServiceImpl implements TaskGroupService {

    private TaskGroupRepository repository;
    private ProjectService projectService;

    @Autowired
    public MongoTaskGroupServiceImpl(
            TaskGroupRepository repository,
            ProjectService projectService){
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public TaskGroup add(TaskGroup group, String projectId) {
        Project project = projectService.findById(projectId);
        TaskGroup added = repository.insert(group);
        project.getGroups().add(added);
        projectService.update(project);
        return added;
    }

    @Override
    public TaskGroup delete(String id) {
        final TaskGroup group = repository.findById(id).get();
        repository.deleteById(id);
        return group;
    }

    @Override
    public TaskGroup findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public TaskGroup update(TaskGroup group) {
        return repository.save(group);
    }
}
