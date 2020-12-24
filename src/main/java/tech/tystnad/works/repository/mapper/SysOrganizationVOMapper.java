package tech.tystnad.works.repository.mapper;

import org.apache.ibatis.annotations.Param;
import tech.tystnad.works.model.PageEntity;
import tech.tystnad.works.model.dto.SysOrganizationDTO;
import tech.tystnad.works.model.vo.SysOrganizationTreeVO;
import tech.tystnad.works.model.vo.SysOrganizationVO;

import java.util.List;

public interface SysOrganizationVOMapper {
    List<SysOrganizationVO> findByDTO(@Param("dto") SysOrganizationDTO sysOrganizationDTO, @Param("page") PageEntity pageEntity);

    Integer countByDTO(@Param("dto") SysOrganizationDTO sysOrganizationDTO);

    List<SysOrganizationTreeVO> findByParentId(@Param("top_id") Long topId, @Param("parent_id") Long parentId);
}
