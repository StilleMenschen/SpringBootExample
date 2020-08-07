package tech.tystnad.works.repository.domain;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sys_user
 */
public class SysUserDO {
    /**
     * Database Column Remarks:
     *   用户ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     * Database Column Remarks:
     *   机构ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.org_id
     *
     * @mbg.generated
     */
    private Long orgId;

    /**
     * Database Column Remarks:
     *   顶级机构ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.top_id
     *
     * @mbg.generated
     */
    private Long topId;

    /**
     * Database Column Remarks:
     *   用户名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * Database Column Remarks:
     *   密码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_cipher
     *
     * @mbg.generated
     */
    private String userCipher;

    /**
     * Database Column Remarks:
     *   角色ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.role_id
     *
     * @mbg.generated
     */
    private Long roleId;

    /**
     * Database Column Remarks:
     *   昵称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.nickname
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     * Database Column Remarks:
     *   邮箱
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     * Database Column Remarks:
     *   用户类型(0-超级管理员,1-机构管理员,2-普通用户)
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_type
     *
     * @mbg.generated
     */
    private Byte userType;

    /**
     * Database Column Remarks:
     *   状态(1-启用,0-禁用)
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.enabled
     *
     * @mbg.generated
     */
    private Boolean enabled;

    /**
     * Database Column Remarks:
     *   是否删除(1-删除,0-未删除)
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.updater
     *
     * @mbg.generated
     */
    private Long updater;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.creator
     *
     * @mbg.generated
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_id
     *
     * @return the value of sys_user.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_id
     *
     * @param userId the value for sys_user.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.org_id
     *
     * @return the value of sys_user.org_id
     *
     * @mbg.generated
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.org_id
     *
     * @param orgId the value for sys_user.org_id
     *
     * @mbg.generated
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.top_id
     *
     * @return the value of sys_user.top_id
     *
     * @mbg.generated
     */
    public Long getTopId() {
        return topId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.top_id
     *
     * @param topId the value for sys_user.top_id
     *
     * @mbg.generated
     */
    public void setTopId(Long topId) {
        this.topId = topId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_name
     *
     * @return the value of sys_user.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_name
     *
     * @param userName the value for sys_user.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_cipher
     *
     * @return the value of sys_user.user_cipher
     *
     * @mbg.generated
     */
    public String getUserCipher() {
        return userCipher;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_cipher
     *
     * @param userCipher the value for sys_user.user_cipher
     *
     * @mbg.generated
     */
    public void setUserCipher(String userCipher) {
        this.userCipher = userCipher == null ? null : userCipher.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.role_id
     *
     * @return the value of sys_user.role_id
     *
     * @mbg.generated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.role_id
     *
     * @param roleId the value for sys_user.role_id
     *
     * @mbg.generated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.nickname
     *
     * @return the value of sys_user.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.nickname
     *
     * @param nickname the value for sys_user.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.email
     *
     * @return the value of sys_user.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.email
     *
     * @param email the value for sys_user.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_type
     *
     * @return the value of sys_user.user_type
     *
     * @mbg.generated
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_type
     *
     * @param userType the value for sys_user.user_type
     *
     * @mbg.generated
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.enabled
     *
     * @return the value of sys_user.enabled
     *
     * @mbg.generated
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.enabled
     *
     * @param enabled the value for sys_user.enabled
     *
     * @mbg.generated
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.deleted
     *
     * @return the value of sys_user.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.deleted
     *
     * @param deleted the value for sys_user.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.updater
     *
     * @return the value of sys_user.updater
     *
     * @mbg.generated
     */
    public Long getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.updater
     *
     * @param updater the value for sys_user.updater
     *
     * @mbg.generated
     */
    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.creator
     *
     * @return the value of sys_user.creator
     *
     * @mbg.generated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.creator
     *
     * @param creator the value for sys_user.creator
     *
     * @mbg.generated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.update_time
     *
     * @return the value of sys_user.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.update_time
     *
     * @param updateTime the value for sys_user.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.create_time
     *
     * @return the value of sys_user.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.create_time
     *
     * @param createTime the value for sys_user.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}