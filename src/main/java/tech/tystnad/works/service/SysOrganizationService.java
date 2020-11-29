package tech.tystnad.works.service;

import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;

import java.util.List;
import java.util.Map;

public interface SysOrganizationService {
    ResponseObjectEntity<SysOrganizationVO> save(SysOrganizationDTO sysOrganizationDTO);

    ResponseObjectEntity<SysOrganizationVO> delete(Long sysOrganizationId);

    ResponseObjectEntity<SysOrganizationVO> delete(List<Long> sysOrganizationIds);

    ResponseObjectEntity<Map<String, Object>> tree(Long topId, Long parentId);

    ResponseObjectEntity<List<SysOrganizationVO>> search(Long sysOrganizationId);

    ResponseObjectEntity<List<SysOrganizationVO>> search(SysOrganizationDTO sysOrganizationDTO);
}
