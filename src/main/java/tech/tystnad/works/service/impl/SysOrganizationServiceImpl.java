package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tystnad.works.converter.SysOrganizationConverter;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;
import tech.tystnad.works.repository.domain.SysOrganizationDO;
import tech.tystnad.works.repository.domain.SysOrganizationDOExample;
import tech.tystnad.works.repository.mapper.SysOrganizationDOMapper;
import tech.tystnad.works.repository.mapper.SysOrganizationVOMapper;
import tech.tystnad.works.service.SysOrganizationService;
import tech.tystnad.works.util.IdWorker;

import java.util.*;

@Service
public class SysOrganizationServiceImpl extends BaseService implements SysOrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(SysOrganizationServiceImpl.class);

    private final SysOrganizationDOMapper sysOrganizationDOMapper;
    private final SysOrganizationVOMapper sysOrganizationVOMapper;
    private final IdWorker idWorker;

    @Autowired
    public SysOrganizationServiceImpl(SysOrganizationDOMapper sysOrganizationDOMapper, SysOrganizationVOMapper sysOrganizationVOMapper, IdWorker idWorker) {
        this.sysOrganizationDOMapper = sysOrganizationDOMapper;
        this.sysOrganizationVOMapper = sysOrganizationVOMapper;
        this.idWorker = idWorker;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<SysOrganizationVO> save(SysOrganizationDTO sysOrganizationDTO) {
        if (sysOrganizationDTO == null) {
            return fail(400, "机构信息不能为空");
        }
        SysOrganizationDOExample example = new SysOrganizationDOExample();
        List<SysOrganizationDO> list;
        example.createCriteria().andDeletedEqualTo(Boolean.FALSE).andTopIdEqualTo(sysOrganizationDTO.getTopId());
        list = sysOrganizationDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            return fail(400, "顶级机构不存在");
        }
        example.clear();
        if (sysOrganizationDTO.getOrgId() != null) {
            example.createCriteria().andDeletedEqualTo(Boolean.FALSE).andOrgNameEqualTo(sysOrganizationDTO.getOrgName())
                    .andOrgIdNotEqualTo(sysOrganizationDTO.getOrgId()).andTopIdEqualTo(sysOrganizationDTO.getTopId());
        } else {
            example.createCriteria().andDeletedEqualTo(Boolean.FALSE).andOrgNameEqualTo(sysOrganizationDTO.getOrgName())
                    .andTopIdEqualTo(sysOrganizationDTO.getTopId());
        }
        list = sysOrganizationDOMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return fail(400, "机构名称重复");
        }
        example.clear();
        example.createCriteria().andDeletedEqualTo(false).andTopIdEqualTo(sysOrganizationDTO.getTopId())
                .andParentIdEqualTo(sysOrganizationDTO.getParentId());
        list = sysOrganizationDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            return fail(400, "父级机构不存在");
        } else {
            SysOrganizationDO sysOrganizationDO = SysOrganizationConverter.dto2do(sysOrganizationDTO);
            final byte plus = 1;
            final int orgLevel = list.get(0).getOrgLevel() + plus;
            if (orgLevel > 5) {
                return fail(400, "机构层级最大为5级");
            }
            sysOrganizationDO.setOrgLevel((byte) orgLevel);
            JwtUser user = (JwtUser) getCurrentUser();
            if (sysOrganizationDO.getOrgId() != null) {
                sysOrganizationDO.setUpdater(user.getUserId());
                if (sysOrganizationDOMapper.updateByPrimaryKeySelective(sysOrganizationDO) > 1) {
                    return ok(null);
                } else {
                    fail(400, "机构不存在");
                }
            } else {
                sysOrganizationDO.setOrgId(idWorker.nextId());
                sysOrganizationDO.setTopId(user.getTopId());
                sysOrganizationDO.setCreator(user.getUserId());
                if (sysOrganizationDOMapper.insertSelective(sysOrganizationDO) > 1) {
                    return ok(null);
                }
            }
        }
        return fail(500, "数据异常,请稍后再试");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<?> delete(Long sysOrganizationId) {
        if (sysOrganizationId != null) {
            SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
            sysOrganizationDO.setOrgId(sysOrganizationId);
            sysOrganizationDO.setDeleted(Boolean.TRUE);
            int c = sysOrganizationDOMapper.updateByPrimaryKeySelective(sysOrganizationDO);
            logger.info("delete SysOrganizationDO {}", c);
            return ok(null);
        }
        return fail(500, "操作失败,请稍后再试");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<?> delete(List<Long> sysOrganizationIds) {
        if (sysOrganizationIds != null && !sysOrganizationIds.isEmpty()) {
            SysOrganizationDOExample example = new SysOrganizationDOExample();
            SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
            sysOrganizationDO.setDeleted(Boolean.TRUE);
            example.createCriteria().andOrgIdIn(sysOrganizationIds).andDeletedEqualTo(Boolean.FALSE);
            int c = sysOrganizationDOMapper.updateByExampleSelective(sysOrganizationDO, example);
            logger.info("delete SysOrganizationDO {}", c);
            return ok(null);
        }
        return fail(500, "操作失败,请稍后再试");
    }

    @Override
    public ResponseObjectEntity<Map<String, Object>> tree(Long topId, Long parentId) {
        if (topId == null || parentId == null) {
            return fail(400, "机构ID不能为空");
        } else {
            SysOrganizationDOExample example = new SysOrganizationDOExample();
            example.createCriteria().andOrgIdIn(Arrays.asList(topId, parentId));
            long count = sysOrganizationDOMapper.countByExample(example);
            if (count <= 0) {
                return fail(400, "机构ID不存在");
            }
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("topId", topId);
            map.put("parentId", parentId);
            map.put("children", sysOrganizationVOMapper.findByParentId(topId, parentId));
            return ok(map);
        }
    }

    @Override
    public ResponseObjectEntity<SysOrganizationVO> search(Long sysOrganizationId) {
        SysOrganizationDO sysOrganizationDO = sysOrganizationDOMapper.selectByPrimaryKey(sysOrganizationId);
        if (sysOrganizationDO == null) {
            return fail(400, "机构不存在");
        }
        SysOrganizationVO sysOrganizationVO = SysOrganizationConverter.do2vo(sysOrganizationDO);
        return ok(sysOrganizationVO);
    }

    @Override
    public ResponseObjectEntity<SysOrganizationVO> search(SysOrganizationDTO sysOrganizationDTO, PageEntity pageEntity) {
        return ok(sysOrganizationVOMapper.findByDTO(sysOrganizationDTO, pageEntity));
    }
}
