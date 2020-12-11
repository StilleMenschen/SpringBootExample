package tech.tystnad.works.repository.mapper;

import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysRoleVO;

import java.util.List;

public interface SysRoleVOMapper {
    List<SysRoleVO> findByDTO(SysRoleDTO sysRoleDTO);
}
