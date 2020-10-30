package tech.tystnad.works.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;
import tech.tystnad.works.repository.domain.SysOrganizationDO;
import tech.tystnad.works.repository.domain.SysOrganizationDOExample;
import tech.tystnad.works.repository.mapper.SysOrganizationDOMapper;
import tech.tystnad.works.service.SysOrganizationService;
import tech.tystnad.works.util.IdWorker;

import java.util.List;

@Service
public class SysOrganizationServiceImpl extends BaseService implements SysOrganizationService {
    private final SysOrganizationDOMapper sysOrganizationDOMapper;
    private final IdWorker idWorker;

    @Autowired
    public SysOrganizationServiceImpl(SysOrganizationDOMapper sysOrganizationDOMapper, IdWorker idWorker) {
        this.sysOrganizationDOMapper = sysOrganizationDOMapper;
        this.idWorker = idWorker;
    }

    @Override
    public ResponseObjectEntity<SysOrganizationVO> add(SysOrganizationDTO dto) {
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
            final byte plus = 1;
            final int orgLevel = list.get(0).getOrgLevel() + plus;
            if (orgLevel > 5) {
                fail(400, "机构层级最大为5级");
            }
            SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
            sysOrganizationDO.setOrgId(idWorker.nextId());
            sysOrganizationDO.setOrgLevel((byte) orgLevel);
            if (sysOrganizationDOMapper.insertSelective(sysOrganizationDO) > 1) {
                return ok(null);
            }
        }
        return fail(500, "数据异常,请稍后再试");
    }

    @Override
    public ResponseObjectEntity<SysOrganizationVO> update(SysOrganizationDTO sysOrganizationDTO) {
        return null;
    }

    @Override
    public ResponseObjectEntity<SysOrganizationVO> delete(String sysOrganizationId) {
        return null;
    }

    @Override
    public ResponseObjectEntity<SysOrganizationVO> delete(List<String> sysOrganizationIds) {
        return null;
    }

    @Override
    public ResponseObjectEntity<List<SysOrganizationVO>> search(String sysOrganizationId) {
        return null;
    }

    @Override
    public ResponseObjectEntity<List<SysOrganizationVO>> search(SysOrganizationDTO sysOrganizationDTO) {
        return null;
    }
}
