package tech.tystnad.works.repository.domain;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table work_log
 */
public class WorkLogDO {
    /**
     * Database Column Remarks:
     *   日志ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.log_id
     *
     * @mbg.generated
     */
    private Long logId;

    /**
     * Database Column Remarks:
     *   机构ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.org_id
     *
     * @mbg.generated
     */
    private Long orgId;

    /**
     * Database Column Remarks:
     *   顶级机构ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.top_id
     *
     * @mbg.generated
     */
    private Long topId;

    /**
     * Database Column Remarks:
     *   项目ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.project_id
     *
     * @mbg.generated
     */
    private Long projectId;

    /**
     * Database Column Remarks:
     *   工作时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.work_time
     *
     * @mbg.generated
     */
    private Float workTime;

    /**
     * Database Column Remarks:
     *   备注
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * Database Column Remarks:
     *   日志时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.log_time
     *
     * @mbg.generated
     */
    private Date logTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.updater
     *
     * @mbg.generated
     */
    private Long updater;

    /**
     * Database Column Remarks:
     *   创建用户ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.creator
     *
     * @mbg.generated
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column work_log.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.log_id
     *
     * @return the value of work_log.log_id
     *
     * @mbg.generated
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.log_id
     *
     * @param logId the value for work_log.log_id
     *
     * @mbg.generated
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.org_id
     *
     * @return the value of work_log.org_id
     *
     * @mbg.generated
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.org_id
     *
     * @param orgId the value for work_log.org_id
     *
     * @mbg.generated
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.top_id
     *
     * @return the value of work_log.top_id
     *
     * @mbg.generated
     */
    public Long getTopId() {
        return topId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.top_id
     *
     * @param topId the value for work_log.top_id
     *
     * @mbg.generated
     */
    public void setTopId(Long topId) {
        this.topId = topId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.project_id
     *
     * @return the value of work_log.project_id
     *
     * @mbg.generated
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.project_id
     *
     * @param projectId the value for work_log.project_id
     *
     * @mbg.generated
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.work_time
     *
     * @return the value of work_log.work_time
     *
     * @mbg.generated
     */
    public Float getWorkTime() {
        return workTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.work_time
     *
     * @param workTime the value for work_log.work_time
     *
     * @mbg.generated
     */
    public void setWorkTime(Float workTime) {
        this.workTime = workTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.remark
     *
     * @return the value of work_log.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.remark
     *
     * @param remark the value for work_log.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.log_time
     *
     * @return the value of work_log.log_time
     *
     * @mbg.generated
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.log_time
     *
     * @param logTime the value for work_log.log_time
     *
     * @mbg.generated
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.updater
     *
     * @return the value of work_log.updater
     *
     * @mbg.generated
     */
    public Long getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.updater
     *
     * @param updater the value for work_log.updater
     *
     * @mbg.generated
     */
    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.creator
     *
     * @return the value of work_log.creator
     *
     * @mbg.generated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.creator
     *
     * @param creator the value for work_log.creator
     *
     * @mbg.generated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.update_time
     *
     * @return the value of work_log.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.update_time
     *
     * @param updateTime the value for work_log.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_log.create_time
     *
     * @return the value of work_log.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_log.create_time
     *
     * @param createTime the value for work_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}