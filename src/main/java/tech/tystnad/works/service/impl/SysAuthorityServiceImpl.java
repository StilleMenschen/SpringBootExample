package tech.tystnad.works.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.repository.domain.SysAuthorityDO;
import tech.tystnad.works.repository.domain.SysAuthorityDOExample;
import tech.tystnad.works.repository.mapper.SysAuthorityDOMapper;
import tech.tystnad.works.service.SysAuthorityService;

import java.util.List;

@Service
public class SysAuthorityServiceImpl extends BaseService implements SysAuthorityService {

    private final SysAuthorityDOMapper sysAuthorityDOMapper;

    @Autowired
    public SysAuthorityServiceImpl(SysAuthorityDOMapper sysAuthorityDOMapper) {
        this.sysAuthorityDOMapper = sysAuthorityDOMapper;
    }

    @Override
    public ResponseObjectEntity<?> delete(Integer sysAuthorityId) {
        sysAuthorityDOMapper.deleteByPrimaryKey(sysAuthorityId);
        return ok(null);
    }

    @Override
    public ResponseObjectEntity<?> delete(List<Integer> sysAuthorityIds) {
        SysAuthorityDOExample example = new SysAuthorityDOExample();
        example.createCriteria().andAuthIdIn(sysAuthorityIds);
        sysAuthorityDOMapper.deleteByExample(example);
        return ok(null);
    }

    @Override
    public ResponseObjectEntity<SysAuthorityDO> search() {
        return ok(sysAuthorityDOMapper.selectByExample(new SysAuthorityDOExample()));
    }

    @Override
    public ResponseObjectEntity<SysAuthorityDO> search(Integer sysAuthorityId) {
        return ok(sysAuthorityDOMapper.selectByPrimaryKey(sysAuthorityId));
    }

    @Override
    public ResponseObjectEntity<SysAuthorityDO> search(List<Integer> sysAuthorityIds) {
        if (sysAuthorityIds.isEmpty()) {
            return ok(null);
        }
        SysAuthorityDOExample example = new SysAuthorityDOExample();
        example.createCriteria().andAuthIdIn(sysAuthorityIds);
        example.setOrderByClause("parent_id, auth_id");
        return ok(sysAuthorityDOMapper.selectByExample(example));
    }
}
