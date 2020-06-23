package tech.tystnad.works.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.tystnad.works.model.Project;
import tech.tystnad.works.service.ProjectService;
import tech.tystnad.works.model.TaskGroup;
import tech.tystnad.works.repository.TaskGroupRepository;
import tech.tystnad.works.service.TaskGroupService;

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
