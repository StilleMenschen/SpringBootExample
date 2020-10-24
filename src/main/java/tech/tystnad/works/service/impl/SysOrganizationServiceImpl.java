package tech.tystnad.works.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;
import tech.tystnad.works.repository.domain.SysOrganizationDO;
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
    public ResponseObjectEntity<SysOrganizationVO> add(@RequestBody SysOrganizationDTO sysOrganizationDTO) {
        if (sysOrganizationDTO == null){
            fail(400, "机构信息不能为空");
        }
        SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
        sysOrganizationDO.setOrgId(idWorker.nextId());
        return null;
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
