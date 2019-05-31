package com.bai.nfc.bean;

public class Ring {

    /**
     * errorCode : 00
     * errorMsg : 成功
     * customer : {"customerId":"373a7f1fca8d4689b98ae0db571a665c","handRingId":"0000000000023","openId":"oJt8-5fB4nmBvRB4Gf8yGoVNZIhc","phoneNumber":"13389216484","idType":"1","idNumber":"610111199402211513","customerName":"陈持","accountBalance":20200,"integralaccountBalance":0,"insertTime":"Apr 13, 2019 8:45:15 PM","updateTime":"Apr 14, 2019 4:46:28 AM"}
     */

    private String errorCode;
    private String errorMsg;
    private CustomerBean customer;

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

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public static class CustomerBean {
        /**
         * customerId : 373a7f1fca8d4689b98ae0db571a665c
         * handRingId : 0000000000023
         * openId : oJt8-5fB4nmBvRB4Gf8yGoVNZIhc
         * phoneNumber : 13389216484
         * idType : 1
         * idNumber : 610111199402211513
         * customerName : 陈持
         * accountBalance : 20200
         * integralaccountBalance : 0
         * insertTime : Apr 13, 2019 8:45:15 PM
         * updateTime : Apr 14, 2019 4:46:28 AM
         */

        private String customerId;
        private String handRingId;
        private String openId;
        private String phoneNumber;
        private String idType;
        private String idNumber;
        private String customerName;
        private int accountBalance;
        private int integralaccountBalance;
        private String insertTime;
        private String updateTime;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getHandRingId() {
            return handRingId;
        }

        public void setHandRingId(String handRingId) {
            this.handRingId = handRingId;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getIdType() {
            return idType;
        }

        public void setIdType(String idType) {
            this.idType = idType;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public int getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(int accountBalance) {
            this.accountBalance = accountBalance;
        }

        public int getIntegralaccountBalance() {
            return integralaccountBalance;
        }

        public void setIntegralaccountBalance(int integralaccountBalance) {
            this.integralaccountBalance = integralaccountBalance;
        }

        public String getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(String insertTime) {
            this.insertTime = insertTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
