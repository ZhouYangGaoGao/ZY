package com.bai.nfc.bean;

import java.util.List;

public class Classify {

    /**
     * errorCode : 00
     * errorMsg : 成功
     * classifies : [{"classifyId":1,"merchantId":"18701646700","classifyName":"aaa"},{"classifyId":2,"merchantId":"18701646700","classifyName":"bbb"}]
     */

    private String errorCode;
    private String errorMsg;
    private List<ClassifiesBean> classifies;

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

    public List<ClassifiesBean> getClassifies() {
        return classifies;
    }

    public void setClassifies(List<ClassifiesBean> classifies) {
        this.classifies = classifies;
    }

    public static class ClassifiesBean {
        /**
         * classifyId : 1
         * merchantId : 18701646700
         * classifyName : aaa
         */

        private int classifyId;
        private String merchantId;
        private String classifyName;

        public int getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(int classifyId) {
            this.classifyId = classifyId;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }
    }
}
