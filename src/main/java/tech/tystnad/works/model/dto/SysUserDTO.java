package tech.tystnad.works.model.dto;


import org.hibernate.validator.constraints.Length;
import tech.tystnad.works.core.validator.annotation.EnumCheck;
import tech.tystnad.works.core.validator.groups.SysUserValidatorGroups.addGroup;
import tech.tystnad.works.core.validator.groups.SysUserValidatorGroups.queryGroup;
import tech.tystnad.works.core.validator.groups.SysUserValidatorGroups.updateGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class SysUserDTO {
    @NotNull(message = "{user.id.notnull}", groups = addGroup.class)
    private Long userId;
    @NotNull(message = "{org.id.notnull}", groups = addGroup.class)
    private Long orgId;
    @Length(min = 1, max = 16, message = "{org.name.not-validated}")
    @Pattern(regexp = "^\\S+(\\s*\\S+)*$", message = "{org.name.notnull}", groups = queryGroup.class)
    private String orgName;
    private Long topId;
    @NotBlank(message = "{user.username.notnull}", groups = {addGroup.class, updateGroup.class})
    @Pattern(regexp = "^[a-zA-Z]\\w{1,15}$", message = "{user.username.not-validated}", groups = {addGroup.class, updateGroup.class})
    private String userName;
    @NotNull(message = "{role.id.notnull}", groups = {addGroup.class})
    private Long roleId;
    @Length(min = 1, max = 32, message = "{user.nickname.not-validated}")
    @Pattern(regexp = "^\\S+(\\s*\\S+)*$", message = "{user.nickname.notnull}", groups = updateGroup.class)
    private String nickname;
    @Email(regexp = "^[a-z0-9]+([.-_]?[a-z0-9]+)*@[a-z]+(\\.?[a-z0-9]+)*\\.[a-z]+$", message = "{user.email.not-validated}", groups = addGroup.class)
    private String email;
    @Pattern(regexp = "^(\\d{11}|(0[0-9]{2,3}/-)?([2-9][0-9]{6,7})+(/-[0-9]{1,4})?)$", message = "{user.telephone.not-validated}", groups = {addGroup.class, updateGroup.class})
    private String telephoneNumber;
    @EnumCheck(enums = {0, 1, 2}, message = "{user.type.not-validated}", groups = {addGroup.class})
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
}
