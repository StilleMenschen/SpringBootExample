package tech.tystnad.works.repository.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleAuthorityRelationshipVOMapper {
    int insertMulti(@Param("roleId") Long roleId, @Param("authIds") List<Short> authIds);
}
