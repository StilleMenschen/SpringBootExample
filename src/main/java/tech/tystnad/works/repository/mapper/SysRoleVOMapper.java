package tech.tystnad.works.repository.mapper;

import org.apache.ibatis.annotations.Param;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.dto.SysRoleDTO;
import tech.tystnad.works.model.vo.SysRoleVO;

import java.util.List;

public interface SysRoleVOMapper {
    List<SysRoleVO> findByDTO(@Param("dto") SysRoleDTO sysRoleDTO, @Param("page") PageEntity pageEntity);

    Integer countByDTO(@Param("dto") SysRoleDTO sysRoleDTO);
}
