package tech.tystnad.works.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tech.tystnad.works.repository.domain.WorkLogDO;
import tech.tystnad.works.repository.domain.WorkLogDOExample;

public interface WorkLogDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    long countByExample(WorkLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    int deleteByExample(WorkLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    int insert(WorkLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    int insertSelective(WorkLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    List<WorkLogDO> selectByExampleWithRowbounds(WorkLogDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    List<WorkLogDO> selectByExample(WorkLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    WorkLogDO selectByPrimaryKey(Long logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") WorkLogDO record, @Param("example") WorkLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") WorkLogDO record, @Param("example") WorkLogDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(WorkLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(WorkLogDO record);
}