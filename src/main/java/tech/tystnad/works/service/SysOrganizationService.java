package tech.tystnad.works.service;

import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;

import java.util.List;

public interface SysOrganizationService {
    ResponseObjectEntity<SysOrganizationVO> save(SysOrganizationDTO sysOrganizationDTO);

    ResponseObjectEntity<SysOrganizationVO> delete(Long sysOrganizationId);

    ResponseObjectEntity<SysOrganizationVO> delete(List<Long> sysOrganizationIds);

    ResponseObjectEntity<List<SysOrganizationVO>> search(String sysOrganizationId);

    ResponseObjectEntity<List<SysOrganizationVO>> search(SysOrganizationDTO sysOrganizationDTO);
}
