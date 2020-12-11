package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tystnad.works.converter.SysRoleConverter;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysRoleVO;
import tech.tystnad.works.repository.domain.SysOrganizationDOExample;
import tech.tystnad.works.repository.domain.SysRoleDO;
import tech.tystnad.works.repository.domain.SysRoleDOExample;
import tech.tystnad.works.repository.mapper.SysOrganizationDOMapper;
import tech.tystnad.works.repository.mapper.SysRoleDOMapper;
import tech.tystnad.works.repository.mapper.SysRoleVOMapper;
import tech.tystnad.works.service.SysRoleService;
import tech.tystnad.works.util.IdWorker;

import java.util.Collections;
import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseService implements SysRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysOrganizationServiceImpl.class);

    private final SysRoleDOMapper sysRoleDOMapper;
    private final SysRoleVOMapper sysRoleVOMapper;
    private final IdWorker idWorker;
    private final SysOrganizationDOMapper sysOrganizationDOMapper;

    @Autowired
    public SysRoleServiceImpl(SysRoleDOMapper sysRoleDOMapper, SysRoleVOMapper sysRoleVOMapper, IdWorker idWorker, SysOrganizationDOMapper sysOrganizationDOMapper) {
        this.sysRoleDOMapper = sysRoleDOMapper;
        this.sysRoleVOMapper = sysRoleVOMapper;
        this.idWorker = idWorker;
        this.sysOrganizationDOMapper = sysOrganizationDOMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<SysRoleVO> save(SysRoleDTO sysRoleDTO) {
        if (sysRoleDTO == null) {
            return fail(400, "角色信息不能为空");
        }
        SysRoleDOExample example = new SysRoleDOExample();
        List<SysRoleDO> list;
        if (sysRoleDTO.getRoleId() != null) {
            example.createCriteria().andDeletedEqualTo(Boolean.FALSE).andRoleNameEqualTo(sysRoleDTO.getRoleName())
                    .andRoleIdNotEqualTo(sysRoleDTO.getRoleId());
        } else {
            example.createCriteria().andDeletedEqualTo(Boolean.FALSE).andRoleNameEqualTo(sysRoleDTO.getRoleName());
        }
        list = sysRoleDOMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return fail(400, "角色名称重复");
        } else {
            SysRoleDO sysRoleDO = SysRoleConverter.dto2do(sysRoleDTO);
            JwtUser user = (JwtUser) getCurrentUser();
            if (sysRoleDO.getOrgId() != null) {
                SysOrganizationDOExample sysOrganizationDOExample = new SysOrganizationDOExample();
                sysOrganizationDOExample.createCriteria().andDeletedEqualTo(Boolean.FALSE).andTopIdEqualTo(sysRoleDTO.getTopId());
                if (sysOrganizationDOMapper.selectByExample(sysOrganizationDOExample).isEmpty()) {
                    return fail(400, "顶级机构不存在");
                }
                sysOrganizationDOExample.clear();
                sysOrganizationDOExample.createCriteria().andDeletedEqualTo(Boolean.FALSE).andOrgIdNotEqualTo(sysRoleDTO.getOrgId());
                if (sysOrganizationDOMapper.selectByExample(sysOrganizationDOExample).isEmpty()) {
                    return fail(400, "所属机构不存在");
                }
                sysRoleDO.setUpdater(user.getUserId());
                if (sysRoleDOMapper.updateByPrimaryKeySelective(sysRoleDO) > 1) {
                    return ok(null);
                } else {
                    fail(400, "角色不存在");
                }
            } else {
                sysRoleDO.setRoleId(idWorker.nextId());
                sysRoleDO.setTopId(user.getTopId());
                sysRoleDO.setOrgId(user.getOrgId());
                sysRoleDO.setCreator(user.getUserId());
                if (sysRoleDOMapper.insertSelective(sysRoleDO) > 1) {
                    return ok(null);
                }
            }
        }
        return fail(500, "数据异常,请稍后再试");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<SysRoleVO> delete(Long sysRoleId) {
        if (sysRoleId != null) {
            SysRoleDO sysRoleDO = new SysRoleDO();
            sysRoleDO.setRoleId(sysRoleId);
            sysRoleDO.setDeleted(Boolean.TRUE);
            int c = sysRoleDOMapper.updateByPrimaryKeySelective(sysRoleDO);
            logger.info("delete SysRoleDO {}", c);
            return ok(null);
        }
        return fail(500, "操作失败,请稍后再试");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<SysRoleVO> delete(List<Long> sysRoleIds) {
        if (sysRoleIds != null && !sysRoleIds.isEmpty()) {
            SysRoleDOExample example = new SysRoleDOExample();
            SysRoleDO sysRoleDO = new SysRoleDO();
            sysRoleDO.setDeleted(Boolean.TRUE);
            example.createCriteria().andRoleIdIn(sysRoleIds).andDeletedEqualTo(Boolean.FALSE);
            int c = sysRoleDOMapper.updateByExampleSelective(sysRoleDO, example);
            logger.info("delete SysRoleDO {}", c);
            return ok(null);
        }
        return fail(500, "操作失败,请稍后再试");
    }

    @Override
    public ResponseObjectEntity<List<SysRoleVO>> search(Long sysRoleId) {
        SysRoleDO sysRoleDO = sysRoleDOMapper.selectByPrimaryKey(sysRoleId);
        if (sysRoleDO == null) {
            return fail(400, "角色不存在");
        }
        SysRoleVO sysRoleVO = SysRoleConverter.do2vo(sysRoleDO);
        return ok(Collections.singletonList(sysRoleVO));
    }

    @Override
    public ResponseObjectEntity<List<SysRoleVO>> search(SysRoleDTO sysRoleDTO) {
        return ok(sysRoleVOMapper.findByDTO(sysRoleDTO));
    }
}
