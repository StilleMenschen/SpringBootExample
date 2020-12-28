package tech.tystnad.works.service;

import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.repository.domain.SysAuthorityDO;

import java.util.List;

public interface SysAuthorityService {
    ResponseObjectEntity<?> delete(Short sysAuthorityId);

    ResponseObjectEntity<?> delete(List<Short> sysAuthorityIds);

    ResponseObjectEntity<SysAuthorityDO> search(Short sysAuthorityId);

    ResponseObjectEntity<SysAuthorityDO> search(List<Short> sysAuthorityIds);
}
