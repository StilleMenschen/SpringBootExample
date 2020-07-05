package tech.tystnad.works.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tech.tystnad.works.model.SysOrganization;

import java.util.List;

@Repository
public interface SysOrganizationRepository {

    public List<SysOrganization> findAll(@Param("org") SysOrganization sysOrganization, @Param("page") int page, @Param("size") int size);

    public int countAll(@Param("org") SysOrganization sysOrganization);

    public int addOnce(SysOrganization sysOrganization);

    public int delById(@Param("org_id") long org_id);
}
