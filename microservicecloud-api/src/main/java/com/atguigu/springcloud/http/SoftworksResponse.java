package com.atguigu.springcloud.http;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SoftworksResponse<T> {

    private MessageCode status;

    private List<String> messages;

    private T result;

    public SoftworksResponse() {
        messages = new ArrayList<>();
    }

    public SoftworksResponse(MessageCode status, T result) {
        messages = new ArrayList<>();
        this.status = status;
        this.result = result;
    }

    public MessageCode getStatus() {
        return status;
    }

    public void setStatus(MessageCode status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "error code:" + status + " result:" + result;
    }

    public static SoftworksResponse failure(String msg) {
        SoftworksResponse resp = new SoftworksResponse();
        resp.status = MessageCode.COMMON_FAILURE;
        resp.getMessages().add(msg);
        return resp;
    }

    public static SoftworksResponse failure(MessageCode messageCode) {
        SoftworksResponse resp = new SoftworksResponse();
        resp.status = messageCode;
        resp.getMessages().add(messageCode.getMsg());
        return resp;
    }

    public static SoftworksResponse failure(String code, String message) {
        SoftworksResponse resp = new SoftworksResponse();
        MessageCode messageCode = null;
        for (MessageCode temp : MessageCode.values()) {
            if (temp.getCode().equals(code)) {
                messageCode = temp;
                break;
            }
        }
        if (null == messageCode) {
            messageCode = MessageCode.COMMON_API_ERROR;
        }
        resp.status = messageCode;
        resp.getMessages().add(message);
        return resp;
    }

    public static SoftworksResponse failure(MessageCode messageCode, String message) {
        SoftworksResponse resp = new SoftworksResponse();
        resp.status = messageCode;
        resp.getMessages().add(messageCode.getMsg());
        if (StringUtils.isNotBlank(message)) {
            resp.getMessages().add(message);
        }
        return resp;
    }


    public static SoftworksResponse success() {
        SoftworksResponse resp = new SoftworksResponse();
        resp.status = MessageCode.COMMON_SUCCESS;
        resp.getMessages().add(MessageCode.COMMON_SUCCESS.getMsg());
        return resp;
    }

    public static <K> SoftworksResponse<K> success(K t) {
        SoftworksResponse<K> resp = new SoftworksResponse<>();
        resp.status = MessageCode.COMMON_SUCCESS;
        resp.getMessages().add(MessageCode.COMMON_SUCCESS.getMsg());
        resp.result = t;

        return resp;
    }

    /**
     * 判断字符串是否已经是 WsResponse返回格式
     * @param json
     * @return
     */
    public static boolean isWsResponseJson(String json) {
        if (json != null && json.indexOf("\"status\":") != -1
                && json.indexOf("\"messages\":") != -1
                && json.indexOf("\"data\":") != -1) {
            return true;
        } else {
            return false;
        }
    }
}
