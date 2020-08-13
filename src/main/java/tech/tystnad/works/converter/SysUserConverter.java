package tech.tystnad.works.converter;

import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.repository.domain.SysUserDO;

public class SysUserConverter {
    public static SysUserDO dto2do(SysUserDTO dto) {
        if (dto == null) {
            return null;
        }
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setUserId(dto.getUserId());
        sysUserDO.setOrgId(dto.getOrgId());
        sysUserDO.setTopId(dto.getTopId());
        sysUserDO.setUserName(dto.getUserName());
        sysUserDO.setNickname(dto.getNickname());
        sysUserDO.setRoleId(dto.getRoleId());
        sysUserDO.setUserType(dto.getUserType());
        sysUserDO.setEnabled(dto.isEnabled());
        sysUserDO.setEmail(dto.getEmail());
        sysUserDO.setTelephoneNumber(dto.getTelephoneNumber());
        return sysUserDO;
    }
}
