package tech.tystnad.works.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tech.tystnad.works.repository.domain.RoleAuthorityRelationshipDO;
import tech.tystnad.works.repository.domain.RoleAuthorityRelationshipDOExample;

public interface RoleAuthorityRelationshipDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority_relationship
     *
     * @mbg.generated
     */
    long countByExample(RoleAuthorityRelationshipDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority_relationship
     *
     * @mbg.generated
     */
    int deleteByExample(RoleAuthorityRelationshipDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority_relationship
     *
     * @mbg.generated
     */
    int insert(RoleAuthorityRelationshipDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority_relationship
     *
     * @mbg.generated
     */
    int insertSelective(RoleAuthorityRelationshipDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority_relationship
     *
     * @mbg.generated
     */
    List<RoleAuthorityRelationshipDO> selectByExampleWithRowbounds(RoleAuthorityRelationshipDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority_relationship
     *
     * @mbg.generated
     */
    List<RoleAuthorityRelationshipDO> selectByExample(RoleAuthorityRelationshipDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority_relationship
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") RoleAuthorityRelationshipDO record, @Param("example") RoleAuthorityRelationshipDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_authority_relationship
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") RoleAuthorityRelationshipDO record, @Param("example") RoleAuthorityRelationshipDOExample example);
}