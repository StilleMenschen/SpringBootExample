package tech.tystnad.works.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseObjectEntity<SysOrganizationVO> add(@RequestBody SysOrganizationDTO dto) {
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
        example.createCriteria().andDeletedEqualTo(false).andParentIdEqualTo(dto.getParentId());
        list = sysOrganizationDOMapper.selectByExample(example);
        if (list.size() != 1) {
            return fail(400, "父级机构不存在");
        }
        example.clear();
        example.createCriteria().andDeletedEqualTo(false).andOrgNameEqualTo(dto.getOrgName());
        list = sysOrganizationDOMapper.selectByExample(example);
        if (list.size() > 0) {
            return fail(400, "机构名称重复");
        }
        SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
        sysOrganizationDO.setOrgId(idWorker.nextId());
        sysOrganizationDOMapper.insertSelective(sysOrganizationDO);
        return ok(null);
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
