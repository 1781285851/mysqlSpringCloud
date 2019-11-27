package com.atguigu.springcloud.http;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageCode {
    /** ===========公用 Message Code===================*/
    /**
     * 前缀 00
     */
    COMMON_SUCCESS("00_0", "执行成功"),
    COMMON_FAILURE("00_1", "执行失败"),
    COMMON_NO_AUTHORIZED("0000_2", "没有权限执行"),
    COMMON_NO_DATA("00_3", "查询不到对应数据"),
    COMMON_PARAMETER_ERROR("00_4", "参数错误"),
    COMMON_UNKNOWN_ERROR("00_11", "未知异常"),
    COMMON_DB_ERROR("00_12", "数据库操作异常"),
    COMMON_API_ERROR("00_13", "操作异常"),
    COMMON_SERVICE_ERROR("00_14", "服务异常"),
    COMMON_USER_NOT_EXIST("00_15", "用户不存在"),
    COMMON_DATA_NOT_EXIST("00_16", "数据不存在"),
    COMMON_USER_LOGIN_FAIL("00_17", "登录失败,账号或密码错误"),
    COMMON_FEIGN_PARSE_FAIL("00_18", "Feign 数据解析错误"),
    COMMON_DATA_ERROR("00_19", "数据库数据异常"),
    COMMON_DATA_EXPORT_FAIL("00_20", "数据导出异常"),
    COMMON_DATA_UPDATE_FAIL("00_21", "修改或更新失败"),
    COMMON_RELATION_DATA_NOT_EXIST("00_22", "相关联的数据不存在"),
    COMMON_VERIFY_SIGN_FAIL("00_23", "验证API签名失败"),
    COMMON_CONDITION_QUERY_FAIL("00_24", "该条件无法查询到匹配的数据"),
    COMMON_TIMEOUT_FAIL("00_25", "服务器访问超时"),
    COMMON_SERVER_UNAVAILABLE("00_26", "服务器不可用"),
    COMMON_SERVICE_STRING_IS_EMPTY("00_27", "字符串不能为空"),
    COMMON_SERVICE_RSA_ERROR("00_28", "RSA数据加解密错误"),
    COMMON_SERVICE_OBJECT_IS_NULL("00_29", "对象为空"),
    COMMON_SERVICE_PASSWORD_IS_NULL("00_30", "密码为空"),
    COMMON_SERVICE_TIME_CONVERT_ERROR("00_31", "时间转换异常"),
    COMMON_NO_TOKEN("00_32", "Token验证失败"),
    COMMON_TOKEN_INVALID("00_33", "Token已失效，请重新登录"),
    COMMON_QUARTZ_JOB_EXIST("00_34","定时任务已经存在"),
    COMMON_QUARTZ_JOB_NOT_EXIST("00_35","定时任务不存在"),
    COMMON_VERIFY_CODE_FAIL("00_36","验证码校验错误！"),
    /**
     * ================= admin service Message Code=========================
     */
    ADMIN_MANAGEMENT_NOT_EXIST("01_0", "管理员不存在"),
    ADMIN_MANAGEMENT_SAVE_FAIL("01_1", "保存管理员失败"),
    ADMIN_MANAGEMENT_DELETE_FAIL("01_2", "删除管理员失败"),
    ADMIN_MANAGEMENT_UPDATE_FAIL("01_3", "更新管理员失败"),
    ADMIN_MANAGEMENT_UPDATE_PASSWORD("01_4", "修改管理员密码失败"),
    ADMIN_MANAGEMENT_NAME_EXIST("01_5", "管理员名称已存在"),
    ADMIN_PERMISSION_IN_USE("01_6","角色有管理员正在使用，无法删除"),
    ADMIN_PERMISSION_NAME_EXIST("01_7","角色名重名，新增失败，请重新输入"),

    /**
     * ================= wechat auth Message Code=========================
     */
    WECHAT_AUTH_NO_RESULT("02_0", "第三方（微信）认证尚未返回结果"),
    WECHAT_AUTH_RETURN_FAIL("02_1", "第三方（微信）认证返回失败"),
    WECHAT_AUTH_USER_DETAIL_FAIL("02_3", "第三方（微信）获取用户医保信息返回失败"),
    WECHAT_AUTH_USER_NOT_AGREED("02_4", "第三方（微信）用户尚未接受用户须知条款"),

    /**
     * ================= flowable Message Code=========================
     */
    FLOWABLE_COMMON_FAIL("03_1", "流程执行失败！"),
    FLOWABLE_ITEMS_UPDATE_ERROR("03_2", "业务事项当前状态不支持被修改！"),

    /**
     * ================= Reservation Message Code=========================
     */
    RESERVATION_ADD_FAIL("04_1","该条件下没有剩余窗口"),

    /**
     * ================= Error Message Code=========================
     */
    NO_VAILD_SERVICE("99_0", "无可用服务！");

    //Message 编码
    private String code;
    //Message 描叙
    private String message;

    MessageCode(String code) {
        this.code = code;
    }

    MessageCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }


    @JsonCreator
    public static MessageCode getStatusCode(String status) {
        for (MessageCode unit : MessageCode.values()) {
            if (unit.getCode().equals(status)) {
                return unit;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "{code:'" + code + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}
