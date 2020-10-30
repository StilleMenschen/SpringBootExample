package tech.tystnad.works.converter;

import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.repository.domain.SysOrganizationDO;

public class SysOrganizationConverter {

    public static SysOrganizationDO dto2do(final SysOrganizationDTO dto){
        if (dto == null){
            return null;
        }
        SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
        sysOrganizationDO.setOrgId(dto.getOrgId());
        sysOrganizationDO.setTopId(dto.getTopId());
        sysOrganizationDO.setParentId(dto.getParentId());
        sysOrganizationDO.setOrgLevel(dto.getOrgLevel());
        sysOrganizationDO.setOrgName(dto.getOrgName());
        return sysOrganizationDO;
    }
}
