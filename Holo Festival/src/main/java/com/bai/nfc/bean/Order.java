package com.bai.nfc.bean;

public class Order {

    /**
     * errorCode : 00
     * errorMsg : 成功
     * consumption : {"consumptionId":"1570026377709436","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"1570026377709","payType":0,"payWay":0}
     */

    private String errorCode;
    private String errorMsg;
    private ConsumptionBean consumption;

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

    public ConsumptionBean getConsumption() {
        return consumption;
    }

    public void setConsumption(ConsumptionBean consumption) {
        this.consumption = consumption;
    }

    public static class ConsumptionBean {
        /**
         * consumptionId : 1570026377709436
         * consumptionAmount : 1
         * merchantId : 18701646700
         * consumptionTime : 1570026377709
         * payType : 0
         * payWay : 0
         */

        private String consumptionId;
        private int consumptionAmount;
        private String merchantId;
        private String consumptionTime;
        private int payType;
        private int payWay;

        public String getConsumptionId() {
            return consumptionId;
        }

        public void setConsumptionId(String consumptionId) {
            this.consumptionId = consumptionId;
        }

        public int getConsumptionAmount() {
            return consumptionAmount;
        }

        public void setConsumptionAmount(int consumptionAmount) {
            this.consumptionAmount = consumptionAmount;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getConsumptionTime() {
            return consumptionTime;
        }

        public void setConsumptionTime(String consumptionTime) {
            this.consumptionTime = consumptionTime;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public int getPayWay() {
            return payWay;
        }

        public void setPayWay(int payWay) {
            this.payWay = payWay;
        }
    }
}
