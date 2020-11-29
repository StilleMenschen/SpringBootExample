package tech.tystnad.works.converter;

import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationVO;
import tech.tystnad.works.repository.domain.SysOrganizationDO;

public class SysOrganizationConverter {

    public static SysOrganizationDO dto2do(final SysOrganizationDTO sysOrganizationDTO){
        if (sysOrganizationDTO == null){
            return null;
        }
        SysOrganizationDO sysOrganizationDO = new SysOrganizationDO();
        sysOrganizationDO.setOrgId(sysOrganizationDTO.getOrgId());
        sysOrganizationDO.setTopId(sysOrganizationDTO.getTopId());
        sysOrganizationDO.setParentId(sysOrganizationDTO.getParentId());
        sysOrganizationDO.setOrgLevel(sysOrganizationDTO.getOrgLevel());
        sysOrganizationDO.setOrgName(sysOrganizationDTO.getOrgName());
        sysOrganizationDO.setEnabled(sysOrganizationDTO.getEnabled());
        return sysOrganizationDO;
    }

    public static SysOrganizationVO do2vo(final SysOrganizationDO sysOrganizationDO){
        if (sysOrganizationDO == null) {
            return null;
        }
        SysOrganizationVO vo = new SysOrganizationVO();
        vo.setOrgId(sysOrganizationDO.getOrgId());
        vo.setTopId(sysOrganizationDO.getTopId());
        vo.setParentId(sysOrganizationDO.getParentId());
        vo.setOrgLevel(sysOrganizationDO.getOrgLevel());
        vo.setOrgName(sysOrganizationDO.getOrgName());
        vo.setEnabled(sysOrganizationDO.getEnabled());
        return vo;
    }
}
