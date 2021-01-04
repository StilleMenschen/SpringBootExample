package tech.tystnad.works.service;

import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.repository.domain.SysAuthorityDO;

import java.util.List;

public interface SysAuthorityService {
    ResponseObjectEntity<?> delete(Integer sysAuthorityId);

    ResponseObjectEntity<?> delete(List<Integer> sysAuthorityIds);

    ResponseObjectEntity<SysAuthorityDO> search(Integer sysAuthorityId);

    ResponseObjectEntity<SysAuthorityDO> search(List<Integer> sysAuthorityIds);
}
