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
import tech.tystnad.works.repository.mapper.SysRoleDOMapper;
import tech.tystnad.works.service.RoleAuthorityRelationshipService;

import java.util.List;

@Service
public class RoleAuthorityRelationshipServiceImpl extends BaseService implements RoleAuthorityRelationshipService {

    private final RoleAuthorityRelationshipDOMapper relationshipDOMapper;
    private final RoleAuthorityRelationshipVOMapper relationshipVOMapper;
    private final SysRoleDOMapper sysRoleDOMapper;

    @Autowired
    public RoleAuthorityRelationshipServiceImpl(RoleAuthorityRelationshipDOMapper relationshipDOMapper, RoleAuthorityRelationshipVOMapper relationshipVOMapper, SysRoleDOMapper sysRoleDOMapper) {
        this.relationshipDOMapper = relationshipDOMapper;
        this.relationshipVOMapper = relationshipVOMapper;
        this.sysRoleDOMapper = sysRoleDOMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<?> save(Long roleId, List<Integer> authIds) {
        if (sysRoleDOMapper.selectByPrimaryKey(roleId) == null) {
            return fail(400, "角色不存在");
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
    public ResponseObjectEntity<?> delete(Long roleId, List<Integer> authIds) {
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
