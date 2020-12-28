package tech.tystnad.works.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.repository.domain.RoleAuthorityRelationshipDO;
import tech.tystnad.works.repository.domain.RoleAuthorityRelationshipDOExample;
import tech.tystnad.works.repository.mapper.RoleAuthorityRelationshipDOMapper;
import tech.tystnad.works.repository.mapper.RoleAuthorityRelationshipVOMapper;
import tech.tystnad.works.service.RoleAuthorityRelationshipService;
import tech.tystnad.works.service.SysRoleService;

import java.util.List;

@Service
public class RoleAuthorityRelationshipServiceImpl extends BaseService implements RoleAuthorityRelationshipService {

    private final RoleAuthorityRelationshipDOMapper relationshipDOMapper;
    private final RoleAuthorityRelationshipVOMapper relationshipVOMapper;
    private final SysRoleService sysRoleService;

    @Autowired
    public RoleAuthorityRelationshipServiceImpl(RoleAuthorityRelationshipDOMapper relationshipDOMapper, RoleAuthorityRelationshipVOMapper relationshipVOMapper, SysRoleService sysRoleService) {
        this.relationshipDOMapper = relationshipDOMapper;
        this.relationshipVOMapper = relationshipVOMapper;
        this.sysRoleService = sysRoleService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<?> save(Long roleId, List<Short> authIds) {
        ResponseObjectEntity<?> response = sysRoleService.search(roleId);
        if (response.getCode() != 0) {
            return fail(response.getCode(), response.getMsg());
        }
        if (relationshipVOMapper.insertMulti(roleId, authIds) > 0) {
            return ok(null);
        }
        return fail(500, "服务异常请稍后再试");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<?> delete(Long roleId) {
        RoleAuthorityRelationshipDOExample example = new RoleAuthorityRelationshipDOExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        relationshipDOMapper.deleteByExample(example);
        return ok(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<?> delete(Long roleId, List<Short> authIds) {
        RoleAuthorityRelationshipDOExample example = new RoleAuthorityRelationshipDOExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andAuthIdIn(authIds);
        relationshipDOMapper.deleteByExample(example);
        return ok(null);
    }

    @Override
    public ResponseObjectEntity<RoleAuthorityRelationshipDO> search(Long roleId) {
        RoleAuthorityRelationshipDOExample example = new RoleAuthorityRelationshipDOExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return ok(relationshipDOMapper.selectByExample(example));
    }
}
