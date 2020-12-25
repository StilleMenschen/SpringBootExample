package tech.tystnad.works.repository.mapper;

import org.apache.ibatis.annotations.Param;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.model.vo.SysUserVO;

import java.util.List;

public interface SysUserVOMapper {
    List<SysUserVO> findByDTO(@Param("dto") SysUserDTO sysUserDTO, @Param("page") PageEntity pageEntity);

    Integer countByDTO(@Param("dto") SysUserDTO sysUserDTO);
}
