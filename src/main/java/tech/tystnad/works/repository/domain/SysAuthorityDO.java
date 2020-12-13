package tech.tystnad.works.repository.domain;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sys_authority
 */
public class SysAuthorityDO {
    /**
     * Database Column Remarks:
     *   权限ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_authority.auth_id
     *
     * @mbg.generated
     */
    private Short authId;

    /**
     * Database Column Remarks:
     *   父权限ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_authority.parent_id
     *
     * @mbg.generated
     */
    private Short parentId;

    /**
     * Database Column Remarks:
     *   权限名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_authority.auth_name
     *
     * @mbg.generated
     */
    private String authName;

    /**
     * Database Column Remarks:
     *   权限描述
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_authority.auth_description
     *
     * @mbg.generated
     */
    private String authDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_authority.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.auth_id
     *
     * @return the value of sys_authority.auth_id
     *
     * @mbg.generated
     */
    public Short getAuthId() {
        return authId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.auth_id
     *
     * @param authId the value for sys_authority.auth_id
     *
     * @mbg.generated
     */
    public void setAuthId(Short authId) {
        this.authId = authId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.parent_id
     *
     * @return the value of sys_authority.parent_id
     *
     * @mbg.generated
     */
    public Short getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.parent_id
     *
     * @param parentId the value for sys_authority.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(Short parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.auth_name
     *
     * @return the value of sys_authority.auth_name
     *
     * @mbg.generated
     */
    public String getAuthName() {
        return authName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.auth_name
     *
     * @param authName the value for sys_authority.auth_name
     *
     * @mbg.generated
     */
    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.auth_description
     *
     * @return the value of sys_authority.auth_description
     *
     * @mbg.generated
     */
    public String getAuthDescription() {
        return authDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.auth_description
     *
     * @param authDescription the value for sys_authority.auth_description
     *
     * @mbg.generated
     */
    public void setAuthDescription(String authDescription) {
        this.authDescription = authDescription == null ? null : authDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.update_time
     *
     * @return the value of sys_authority.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.update_time
     *
     * @param updateTime the value for sys_authority.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}