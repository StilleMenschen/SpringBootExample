package tech.tystnad.works.model.vo;

import java.util.List;

public class SysAuthorityTreeVO {
    private Short authId;
    private String authDescription;
    private Boolean checked = Boolean.FALSE;
    private List<SysAuthorityTreeVO> children;

    public Short getAuthId() {
        return authId;
    }

    public void setAuthId(Short authId) {
        this.authId = authId;
    }

    public String getAuthDescription() {
        return authDescription;
    }

    public void setAuthDescription(String authDescription) {
        this.authDescription = authDescription == null ? null : authDescription.trim();
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<SysAuthorityTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysAuthorityTreeVO> children) {
        this.children = children;
    }
}
