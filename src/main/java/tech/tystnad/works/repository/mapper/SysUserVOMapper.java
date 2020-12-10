package tech.tystnad.works.repository.mapper;

import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.model.vo.SysUserVO;

import java.util.List;

public interface SysUserVOMapper {
    List<SysUserVO> findByDTO(SysUserDTO example);
}
