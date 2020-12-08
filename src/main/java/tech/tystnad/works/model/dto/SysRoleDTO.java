package tech.tystnad.works.model.dto;

import java.util.Date;

public class SysRoleDTO {
    /**
     * Database Column Remarks:
     *   角色ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.role_id
     *
     * @mbg.generated
     */
    private Long roleId;

    /**
     * Database Column Remarks:
     *   机构ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.org_id
     *
     * @mbg.generated
     */
    private Long orgId;

    /**
     * Database Column Remarks:
     *   顶级机构ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.top_id
     *
     * @mbg.generated
     */
    private Long topId;

    /**
     * Database Column Remarks:
     *   角色名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.role_name
     *
     * @mbg.generated
     */
    private String roleName;

    /**
     * Database Column Remarks:
     *   是否删除(1-删除,0-未删除)
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.updater
     *
     * @mbg.generated
     */
    private Long updater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.creator
     *
     * @mbg.generated
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.role_id
     *
     * @return the value of sys_role.role_id
     *
     * @mbg.generated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.role_id
     *
     * @param roleId the value for sys_role.role_id
     *
     * @mbg.generated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.org_id
     *
     * @return the value of sys_role.org_id
     *
     * @mbg.generated
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.org_id
     *
     * @param orgId the value for sys_role.org_id
     *
     * @mbg.generated
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.top_id
     *
     * @return the value of sys_role.top_id
     *
     * @mbg.generated
     */
    public Long getTopId() {
        return topId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.top_id
     *
     * @param topId the value for sys_role.top_id
     *
     * @mbg.generated
     */
    public void setTopId(Long topId) {
        this.topId = topId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.role_name
     *
     * @return the value of sys_role.role_name
     *
     * @mbg.generated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.role_name
     *
     * @param roleName the value for sys_role.role_name
     *
     * @mbg.generated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.deleted
     *
     * @return the value of sys_role.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.deleted
     *
     * @param deleted the value for sys_role.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.updater
     *
     * @return the value of sys_role.updater
     *
     * @mbg.generated
     */
    public Long getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.updater
     *
     * @param updater the value for sys_role.updater
     *
     * @mbg.generated
     */
    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.creator
     *
     * @return the value of sys_role.creator
     *
     * @mbg.generated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.creator
     *
     * @param creator the value for sys_role.creator
     *
     * @mbg.generated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.update_time
     *
     * @return the value of sys_role.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.update_time
     *
     * @param updateTime the value for sys_role.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.create_time
     *
     * @return the value of sys_role.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.create_time
     *
     * @param createTime the value for sys_role.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
