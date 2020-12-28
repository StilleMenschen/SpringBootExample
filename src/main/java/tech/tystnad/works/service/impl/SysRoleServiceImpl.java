package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tystnad.works.converter.SysRoleConverter;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysRoleVO;
import tech.tystnad.works.repository.domain.SysAuthorityDO;
import tech.tystnad.works.repository.domain.SysRoleDO;
import tech.tystnad.works.repository.domain.SysRoleDOExample;
import tech.tystnad.works.repository.mapper.SysRoleDOMapper;
import tech.tystnad.works.repository.mapper.SysRoleVOMapper;
import tech.tystnad.works.service.SysAuthorityService;
import tech.tystnad.works.service.SysOrganizationService;
import tech.tystnad.works.service.SysRoleService;
import tech.tystnad.works.util.IdWorker;

import java.util.*;

@Service
public class SysRoleServiceImpl extends BaseService implements SysRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysOrganizationServiceImpl.class);

    private final SysRoleDOMapper sysRoleDOMapper;
    private final SysRoleVOMapper sysRoleVOMapper;
    private final IdWorker idWorker;
    private final SysOrganizationService sysOrganizationService;
    private final SysAuthorityService sysAuthorityService;

    @Autowired
    public SysRoleServiceImpl(SysRoleDOMapper sysRoleDOMapper, SysRoleVOMapper sysRoleVOMapper, IdWorker idWorker, SysOrganizationService sysOrganizationService, SysAuthorityService sysAuthorityService) {
        this.sysRoleDOMapper = sysRoleDOMapper;
        this.sysRoleVOMapper = sysRoleVOMapper;
        this.idWorker = idWorker;
        this.sysOrganizationService = sysOrganizationService;
        this.sysAuthorityService = sysAuthorityService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<SysRoleVO> save(SysRoleDTO sysRoleDTO) {
        if (sysRoleDTO == null) {
            return fail(400, "角色信息不能为空");
        }
        ResponseObjectEntity<?> response = sysOrganizationService.search(sysRoleDTO.getTopId());
        if (response.getCode() != 0) {
            return fail(response.getCode(), "顶级机构不存在");
        }
        response = sysOrganizationService.search(sysRoleDTO.getOrgId());
        if (response.getCode() != 0) {
            return fail(response.getCode(), "所属机构不存在");
        }
        SysRoleDOExample example = new SysRoleDOExample();
        List<SysRoleDO> list;
        if (sysRoleDTO.getRoleId() != null) {
            example.createCriteria().andDeletedEqualTo(Boolean.FALSE).andRoleNameEqualTo(sysRoleDTO.getRoleName())
                    .andRoleIdNotEqualTo(sysRoleDTO.getRoleId()).andTopIdEqualTo(sysRoleDTO.getTopId());
        } else {
            example.createCriteria().andDeletedEqualTo(Boolean.FALSE).andRoleNameEqualTo(sysRoleDTO.getRoleName())
                    .andTopIdEqualTo(sysRoleDTO.getTopId());
        }
        list = sysRoleDOMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return fail(400, "角色名称重复");
        } else {
            SysRoleDO sysRoleDO = SysRoleConverter.dto2do(sysRoleDTO);
            JwtUser user = (JwtUser) getCurrentUser();
            if (sysRoleDO.getOrgId() != null) {
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
    public ResponseObjectEntity<SysRoleVO> search(SysRoleDTO sysRoleDTO, PageEntity pageEntity) {
        List<SysRoleVO> list = sysRoleVOMapper.findByDTO(sysRoleDTO, pageEntity);
        int size = sysRoleVOMapper.countByDTO(sysRoleDTO);
        pageEntity.setSize(size);
        return ok(list, pageEntity);
    }

    private Map<Integer, List<SysAuthorityDO>> listAuthority() {
        // 1. 获取顶级机构ID
        final JwtUser user = (JwtUser) getCurrentUser();
        // 2. 查询顶级机构可用权限
        final List<Short> authorityIds = sysRoleVOMapper.findRoleAuthorityByTopId(user.getTopId());
        // 3. 获取权限详细信息
        ResponseObjectEntity<SysAuthorityDO> response = sysAuthorityService.search(authorityIds);
        List<SysAuthorityDO> authority = response.getValues();
        final Map<Integer, List<SysAuthorityDO>> map = new LinkedHashMap<>();
        if (authority.isEmpty()) {
            map.put(1, Collections.emptyList());
            map.put(2, Collections.emptyList());
        } else {
            List<SysAuthorityDO> topAuthority = new LinkedList<>();
            List<SysAuthorityDO> childrenAuthority = new LinkedList<>();
            for (SysAuthorityDO e : authority) {
                if (e.getParentId() == null) {
                    topAuthority.add(e);
                } else {
                    childrenAuthority.add(e);
                }
            }
            map.put(1, topAuthority);
            map.put(2, childrenAuthority);
        }
        return map;
    }

    private Map<String, Object> buildTree(final SysAuthorityDO parent, List<SysAuthorityDO> authority) {
        Map<String, Object> top = new LinkedHashMap<>();
        // TODO: 实现树形算法
        for (SysAuthorityDO e : authority) {
            if (parent.getParentId().equals(e.getParentId())) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("auth_id", e.getAuthId());
                map.put("auth_description", e.getAuthDescription());
                map.put("checked", Boolean.FALSE);
                map.put("children", null);
            }
        }
        top.put("auth_id", parent.getAuthId());
        top.put("auth_description", parent.getAuthDescription());
        top.put("checked", Boolean.FALSE);
        return top;
    }

    @Override
    public ResponseObjectEntity<Map<String, Object>> authorityTree() {
        final Map<Integer, List<SysAuthorityDO>> map = listAuthority();
        // 4. 结构化权限树
        List<Map<String, Object>> results = new LinkedList<>();
        return ok(results);
    }

    @Override
    public ResponseObjectEntity<Map<String, Object>> authorityTree(Long roleId) {
        final Map<Integer, List<SysAuthorityDO>> map = listAuthority();
        List<Map<String, Object>> results = new LinkedList<>();
        return ok(results);
    }
}
