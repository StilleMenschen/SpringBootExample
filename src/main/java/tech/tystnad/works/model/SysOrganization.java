package tech.tystnad.works.model;

import java.sql.Timestamp;

public class SysOrganization {

    private long org_id;
    private long top_id;
    private long parent_id;
    private int org_level;
    private String org_name;
    private boolean enabled = true;
    private boolean deleted = false;
    private long updater;
    private long creator;
    private Timestamp update_time;
    private Timestamp create_time;

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public long getTop_id() {
        return top_id;
    }

    public void setTop_id(long top_id) {
        this.top_id = top_id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public int getOrg_level() {
        return org_level;
    }

    public void setOrg_level(int org_level) {
        this.org_level = org_level;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getUpdater() {
        return updater;
    }

    public void setUpdater(long updater) {
        this.updater = updater;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "SysOrganization{" +
                "org_id=" + org_id +
                ", top_id=" + top_id +
                ", parent_id=" + parent_id +
                ", org_level=" + org_level +
                ", org_name='" + org_name + '\'' +
                ", enabled=" + enabled +
                ", deleted=" + deleted +
                ", updater=" + updater +
                ", creator=" + creator +
                ", update_time=" + update_time +
                ", create_time=" + create_time +
                '}';
    }
}
