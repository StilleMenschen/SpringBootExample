package tech.tystnad.works.model.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.addGroup;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.queryGroup;
import tech.tystnad.works.core.validator.groups.SysOrganizationGroups.updateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;


public class SysOrganizationDTO {
    @NotNull(message = "{org.id.notnull}", groups = updateGroup.class)
    private Long orgId;
    @NotNull(message = "{org.top-id.notnull}", groups = {addGroup.class, updateGroup.class, queryGroup.class})
    private Long topId;
    @NotNull(message = "{org.parent-id.notnull}", groups = {addGroup.class, updateGroup.class})
    private Long parentId;
    @Range(min = 0, max = 5, message = "{org.level.not-validated}", groups = {addGroup.class, updateGroup.class})
    private Byte orgLevel;
    @NotBlank(message = "{org.name.not-validated}", groups = {addGroup.class, updateGroup.class})
    @Length(min = 1, max = 16, message = "{org.name.not-validated}", groups = {addGroup.class, updateGroup.class})
    @Pattern(regexp = "\\S+", message = "{org.name.notnull}", groups = queryGroup.class)
    private String orgName;
    private Boolean enabled;
    private Long updater;
    private String updaterName;
    private Long creator;
    private String creatorName;
    private Date createTimeStart;
    private Date createTimeEnd;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Byte getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Byte orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
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

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName == null ? null : updaterName.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }
}