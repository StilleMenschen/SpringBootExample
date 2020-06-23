package tech.tystnad.works.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.tystnad.works.model.Project;


/**
 * Created by wangpeng on 2017/4/18.
 */
public interface ProjectService {
    Project add(Project project, String userId);
    Project delete(String id);
    Page<Project> findRelated(String userId, boolean enabled, boolean archived, Pageable pageable);
    Project findById(String id);
    Project update(Project project);
}
