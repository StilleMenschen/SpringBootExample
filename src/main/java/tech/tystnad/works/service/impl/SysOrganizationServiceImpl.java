package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tystnad.works.converter.SysOrganizationConverter;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;
import tech.tystnad.works.repository.domain.SysOrganizationDO;
import tech.tystnad.works.repository.domain.SysOrganizationDOExample;
import tech.tystnad.works.repository.mapper.SysOrganizationDOMapper;
import tech.tystnad.works.repository.mapper.SysOrganizationVOMapper;
import tech.tystnad.works.service.SysOrganizationService;
import tech.tystnad.works.util.IdWorker;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseObjectEntity<SysOrganizationVO> save(SysOrganizationDTO dto) {
        if (dto == null) {
            return fail(400, "机构信息不能为空");
        }
        SysOrganizationDOExample example = new SysOrganizationDOExample();
        List<SysOrganizationDO> list;
        example.createCriteria().andDeletedEqualTo(false).andTopIdEqualTo(dto.getTopId());
        list = sysOrganizationDOMapper.selectByExample(example);
        if (list.size() != 1) {
            return fail(400, "顶级机构不存在");
        }
        example.clear();
        example.createCriteria().andDeletedEqualTo(false).andOrgNameEqualTo(dto.getOrgName());
        list = sysOrganizationDOMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return fail(400, "机构名称重复");
        }
        example.clear();
        example.createCriteria().andDeletedEqualTo(false).andParentIdEqualTo(dto.getParentId());
        list = sysOrganizationDOMapper.selectByExample(example);
        if (list.size() != 1) {
            return fail(400, "父级机构不存在");
        } else {
            SysOrganizationDO sysOrganizationDO = SysOrganizationConverter.dto2do(dto);
            final byte plus = 1;
            final int orgLevel = list.get(0).getOrgLevel() + plus;
            if (orgLevel > 5) {
                return fail(400, "机构层级最大为5级");
            }
            sysOrganizationDO.setOrgLevel((byte) orgLevel);
            JwtUser user = (JwtUser) getCurrentUser();
            sysOrganizationDO.setUpdater(user.getId());
            if (sysOrganizationDO.getOrgId() != null) {
                if (sysOrganizationDOMapper.updateByPrimaryKeySelective(sysOrganizationDO) > 1) {
                    return ok(null);
                } else {
                    fail(400, "机构不存在");
                }
            } else {
                sysOrganizationDO.setOrgId(idWorker.nextId());
                if (sysOrganizationDOMapper.insertSelective(sysOrganizationDO) > 1) {
                    return ok(null);
                }
            }
        }
        return fail(500, "数据异常,请稍后再试");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<SysOrganizationVO> delete(Long sysOrganizationId) {
        if (sysOrganizationId != null) {
            SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
            sysOrganizationDO.setOrgId(sysOrganizationId);
            sysOrganizationDO.setDeleted(true);
            int c = sysOrganizationDOMapper.updateByPrimaryKeySelective(sysOrganizationDO);
            logger.info("delete SysOrganizationDO {}", c);
            return ok(null);
        }
        return fail(500, "操作失败,请稍后再试");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObjectEntity<SysOrganizationVO> delete(List<Long> sysOrganizationIds) {
        if (sysOrganizationIds != null && sysOrganizationIds.size() > 0) {
            SysOrganizationDOExample example = new SysOrganizationDOExample();
            SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
            sysOrganizationDO.setDeleted(true);
            example.createCriteria().andOrgIdIn(sysOrganizationIds).andDeletedEqualTo(false);
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
    public ResponseObjectEntity<List<SysOrganizationVO>> search(Long sysOrganizationId) {
        SysOrganizationDO sysOrganizationDO = sysOrganizationDOMapper.selectByPrimaryKey(sysOrganizationId);
        if (sysOrganizationDO == null || !sysOrganizationDO.getOrgId().equals(sysOrganizationId)) {
            return fail(400, "机构不存在");
        }
        SysOrganizationVO sysOrganizationVO = SysOrganizationConverter.do2vo(sysOrganizationDO);
        return ok(Arrays.asList(sysOrganizationVO));
    }

    @Override
    public ResponseObjectEntity<List<SysOrganizationVO>> search(SysOrganizationDTO sysOrganizationDTO) {
        return ok(sysOrganizationVOMapper.findByDTO(sysOrganizationDTO));
    }
}
