package tech.tystnad.works.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tech.tystnad.works.repository.domain.SysLogDO;
import tech.tystnad.works.repository.domain.SysLogDOExample;

public interface SysLogDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    long countByExample(SysLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int deleteByExample(SysLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int insert(SysLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int insertSelective(SysLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    List<SysLogDO> selectByExampleWithRowbounds(SysLogDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    List<SysLogDO> selectByExample(SysLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    SysLogDO selectByPrimaryKey(Integer logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysLogDO record, @Param("example") SysLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysLogDO record, @Param("example") SysLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysLogDO record);
}