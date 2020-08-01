package tech.tystnad.works.converter.dto;

import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.repository.domain.SysOrgUserDO;

public class SysUserDTOConverter {
    public static SysOrgUserDO convert2DO(SysUserDTO sysUserDTO) {
        return new SysOrgUserDO();
    }

    public static SysUserDTO convert2DTO(SysOrgUserDO sysOrgUserDO) {
        return new SysUserDTO();
    }
}
