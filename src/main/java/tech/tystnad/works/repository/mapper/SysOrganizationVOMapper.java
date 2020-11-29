package tech.tystnad.works.repository.mapper;

import org.apache.ibatis.annotations.Param;
import tech.tystnad.works.model.vo.SysOrganizationTreeVO;

import java.util.List;

public interface SysOrganizationVOMapper {
    List<SysOrganizationTreeVO> findByParentId(@Param("top_id") Long topId, @Param("parent_id") Long parentId);
}
