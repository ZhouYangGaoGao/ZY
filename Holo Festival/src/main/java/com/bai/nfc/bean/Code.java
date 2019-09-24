package com.bai.nfc.bean;

public class Code {

    /**
     * errorCode : 00
     * errorMsg : 成功
     * outTradeNo : 836a21604f90429f81c95d5a85442cf2
     * result : {"code":0,"message":"SUCCESS","data":"weixin://wxpay/bizpayurl?pr=D6QWd3J"}
     */

    private String errorCode;
    private String errorMsg;
    private String outTradeNo;
    private ResultBean result;

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

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : SUCCESS
         * data : weixin://wxpay/bizpayurl?pr=D6QWd3J
         */

        private int code;
        private String message;
        private String data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
