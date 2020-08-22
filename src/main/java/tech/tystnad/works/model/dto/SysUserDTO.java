package tech.tystnad.works.model.dto;


import tech.tystnad.works.core.validator.groups.SysUserValidatorGroups.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class SysUserDTO {
    @NotNull(message = "用户ID不能为空", groups = {addGroup.class})
    private Long userId;
    @NotNull(message = "机构ID不能为空", groups = {addGroup.class})
    private Long orgId;
    private String orgName;
    private Long topId;
    @NotBlank(message = "{user.username.not-null}", groups = {deleteGroup.class})
    @Pattern(regexp = "^[a-zA-Z]\\w{1,15}$", message = "{user.username.not-validated}", groups = {deleteGroup.class})
    private String userName;
    private Long roleId;
    @Pattern(regexp = "^\\S+(\\s*\\S+)*$", message = "昵称不能为空", groups = {deleteGroup.class})
    private String nickname;
    @Email(regexp = "^[a-z0-9]+([.-_]?[a-z0-9]+)*@[a-z]+(\\.?[a-z0-9]+)*\\.[a-z]+$", message = "请输入正确的邮箱", groups = {addGroup.class})
    private String email;
    private String telephoneNumber;
    private Byte userType;
    private Boolean enabled;
    private String updaterName;
    private String creatorName;
    private Date createTimeStart;
    private Date createTimeEnd;

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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    @Override
    public String toString() {
        return "SysUserDTO{" +
                "userId=" + userId +
                ", orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                ", topId=" + topId +
                ", userName='" + userName + '\'' +
                ", roleId=" + roleId +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", userType=" + userType +
                ", enabled=" + enabled +
                ", updaterName='" + updaterName + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", createTimeStart=" + createTimeStart +
                ", createTimeEnd=" + createTimeEnd +
                '}';
    }
}
