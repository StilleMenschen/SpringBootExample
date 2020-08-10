package tech.tystnad.works.model.vo;

import java.util.Date;

public class SysUserVO {
    private Long userId;
    private Long orgId;
    private String orgName;
    private Long topId;
    private String userName;
    private Long roleId;
    private String nickname;
    private String email;
    private Byte userType;
    private Boolean enabled;
    private String updaterName;
    private String creatorName;
    private Date createTime;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public void setTopId(Long topId) {
        this.topId = topId;
    }

    public Long getTopId() {
        return this.topId;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEmail() {
        return this.email;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Byte getUserType() {
        return this.userType;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
