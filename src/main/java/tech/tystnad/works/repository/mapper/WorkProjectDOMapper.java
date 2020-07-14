package tech.tystnad.works.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tech.tystnad.works.repository.domain.WorkProjectDO;
import tech.tystnad.works.repository.domain.WorkProjectDOExample;

public interface WorkProjectDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    long countByExample(WorkProjectDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    int deleteByExample(WorkProjectDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long projectId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    int insert(WorkProjectDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    int insertSelective(WorkProjectDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    List<WorkProjectDO> selectByExampleWithRowbounds(WorkProjectDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    List<WorkProjectDO> selectByExample(WorkProjectDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    WorkProjectDO selectByPrimaryKey(Long projectId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") WorkProjectDO record, @Param("example") WorkProjectDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") WorkProjectDO record, @Param("example") WorkProjectDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(WorkProjectDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_project
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(WorkProjectDO record);
}