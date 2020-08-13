package tech.tystnad.works.enums;

public enum ResponseMessage {
    /**
     * 1001-用户名或密码不能为空
     */
    MSG1001(1001, "用户名或密码不能为空"),
    /**
     * 1002-用户名为字母开头,1到16位字母数字或下划线组成
     */
    MSG1002(1002, "用户名不合法"),
    /**
     * 1003-用户密码为字母数字符号6到30位,不包含空格
     */
    MSG1003(1003, "用户密码不合法"),
    /**
     * 1004-用户邮箱不符合正则表达式
     */
    MSG1004(1004, "用户邮箱不合法");


    private int code;
    private String msg;

    ResponseMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
