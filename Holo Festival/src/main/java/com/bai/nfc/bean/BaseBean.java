package com.bai.nfc.bean;

public class BaseBean {
    /**
     * errorCode : 09
     * errorMsg : 手环验证失败，该手环已被绑定
     */

    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
