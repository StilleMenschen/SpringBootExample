package tech.tystnad.works.enums;

public enum SysUserType {

    SUPER_ADMIN((byte) 0, "超级管理员"),
    ORG_ADMIN((byte) 1, "机构管理员"),
    USER((byte) 2, "普通用户");

    private Byte code;
    private String desc;

    SysUserType(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SysUserType getUserTypeByCode(Byte code) {
        for (SysUserType item : SysUserType.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
