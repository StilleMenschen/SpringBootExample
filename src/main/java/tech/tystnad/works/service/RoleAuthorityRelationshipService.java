package tech.tystnad.works.service;

import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.repository.domain.RoleAuthorityRelationshipDO;

import java.util.List;

public interface RoleAuthorityRelationshipService {
    ResponseObjectEntity<?> save(Long roleId, List<Integer> authIds);

    ResponseObjectEntity<?> delete(Long roleId);

    ResponseObjectEntity<?> delete(Long roleId, List<Integer> authIds);

    ResponseObjectEntity<RoleAuthorityRelationshipDO> search(Long roleId);
}
