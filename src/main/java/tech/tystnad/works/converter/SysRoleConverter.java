package tech.tystnad.works.converter;

import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysRoleVO;
import tech.tystnad.works.repository.domain.SysRoleDO;

public class SysRoleConverter {
    public static SysRoleDO dto2do(final SysRoleDTO sysRoleDTO) {
        if (sysRoleDTO == null) {
            return null;
        }
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setRoleId(sysRoleDTO.getRoleId());
        sysRoleDO.setRoleName(sysRoleDTO.getRoleName());
        sysRoleDO.setOrgId(sysRoleDTO.getOrgId());
        sysRoleDO.setTopId(sysRoleDTO.getTopId());
        return sysRoleDO;
    }

    public static SysRoleVO do2vo(final SysRoleDO sysRoleDO) {
        if (sysRoleDO == null) {
            return null;
        }
        SysRoleVO sysRoleVO = new SysRoleVO();
        sysRoleVO.setRoleId(sysRoleDO.getRoleId());
        sysRoleVO.setRoleName(sysRoleDO.getRoleName());
        sysRoleVO.setOrgId(sysRoleDO.getOrgId());
        sysRoleVO.setTopId(sysRoleDO.getTopId());
        return sysRoleVO;
    }
}
