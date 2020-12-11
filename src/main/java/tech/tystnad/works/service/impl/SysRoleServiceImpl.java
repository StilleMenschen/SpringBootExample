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
import tech.tystnad.works.repository.domain.SysRoleDO;
import tech.tystnad.works.repository.domain.SysRoleDOExample;
import tech.tystnad.works.repository.mapper.SysRoleDOMapper;
import tech.tystnad.works.repository.mapper.SysRoleVOMapper;
import tech.tystnad.works.service.SysOrganizationService;
import tech.tystnad.works.service.SysRoleService;
import tech.tystnad.works.util.IdWorker;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseService implements SysRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysOrganizationServiceImpl.class);

    private final SysRoleDOMapper sysRoleDOMapper;
    private final SysRoleVOMapper sysRoleVOMapper;
    private final IdWorker idWorker;
    private final SysOrganizationService sysOrganizationService;

    @Autowired
    public SysRoleServiceImpl(SysRoleDOMapper sysRoleDOMapper, SysRoleVOMapper sysRoleVOMapper, IdWorker idWorker, SysOrganizationService sysOrganizationService) {
        this.sysRoleDOMapper = sysRoleDOMapper;
        this.sysRoleVOMapper = sysRoleVOMapper;
        this.idWorker = idWorker;
        this.sysOrganizationService = sysOrganizationService;
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
                ResponseObjectEntity<?> response = sysOrganizationService.search(sysRoleDTO.getTopId());
                if (response.getCode() != 0) {
                    return fail(response.getCode(), "顶级机构不存在");
                }
                response = sysOrganizationService.search(sysRoleDTO.getOrgId());
                if (response.getCode() != 0) {
                    return fail(response.getCode(), "所属机构不存在");
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
    public ResponseObjectEntity<?> delete(Long sysRoleId) {
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
    public ResponseObjectEntity<?> delete(List<Long> sysRoleIds) {
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
    public ResponseObjectEntity<SysRoleVO> search(Long sysRoleId) {
        SysRoleDO sysRoleDO = sysRoleDOMapper.selectByPrimaryKey(sysRoleId);
        if (sysRoleDO == null) {
            return fail(400, "角色不存在");
        }
        SysRoleVO sysRoleVO = SysRoleConverter.do2vo(sysRoleDO);
        return ok(sysRoleVO);
    }

    @Override
    public ResponseObjectEntity<SysRoleVO> search(SysRoleDTO sysRoleDTO) {
        return ok(sysRoleVOMapper.findByDTO(sysRoleDTO));
    }
}
