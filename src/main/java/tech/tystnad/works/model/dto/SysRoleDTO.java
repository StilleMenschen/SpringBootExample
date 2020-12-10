package tech.tystnad.works.model.dto;

import org.hibernate.validator.constraints.Length;
import tech.tystnad.works.core.validator.groups.SysRoleGroups.addGroup;
import tech.tystnad.works.core.validator.groups.SysRoleGroups.queryGroup;
import tech.tystnad.works.core.validator.groups.SysRoleGroups.updateGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class SysRoleDTO {
    @NotNull(message = "{role.id.notnull}", groups = updateGroup.class)
    private Long roleId;
    @NotNull(message = "{org.id.notnull}", groups = {updateGroup.class, addGroup.class, queryGroup.class})
    private Long orgId;
    @NotNull(message = "{org.top-id.notnull}", groups = {addGroup.class, updateGroup.class, queryGroup.class})
    private Long topId;
    @Length(min = 1, max = 16, message = "{role.name.not-validated}", groups = {addGroup.class, updateGroup.class})
    @Pattern(regexp = "^\\S+(\\s*\\S+)*$", message = "{role.name.notnull}", groups = queryGroup.class)
    private String roleName;
    private Long updater;
    private String updaterName;
    private Long creator;
    private String creatorName;
    private Date createTimeStart;
    private Date createTimeEnd;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getTopId() {
        return topId;
    }

    public void setTopId(Long topId) {
        this.topId = topId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName == null ? null : updaterName.trim();
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
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
