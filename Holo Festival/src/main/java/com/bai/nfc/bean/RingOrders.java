package com.bai.nfc.bean;

import java.util.List;

public class RingOrders {

    /**
     * errorCode : 00
     * errorMsg : 成功
     * consumptions : [{"consumptionId":"3621ebf5315f44948225eecd803c4bad","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:08:32 PM"},{"consumptionId":"4e60688bebd541e4ba8b31c4248f1584","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":58,"merchantId":"18701646700","consumptionTime":"Apr 17, 2019 2:18:41 PM"},{"consumptionId":"5f295175adef453586ed912d3cd07cb0","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:18:25 PM"},{"consumptionId":"6e28941f325346949d7215dfc595b893","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:06:56 PM"},{"consumptionId":"98a517bffeac4faaa1933088bc02c979","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":16,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:34:57 PM"},{"consumptionId":"b48f2b93da00451eb0f6043f8199aea8","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:16:46 PM"},{"consumptionId":"b596bbff070f4136a24d448781cf1cd4","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:13:40 PM"},{"consumptionId":"d9bcdca2238a4f38829e986ae4ce146d","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":17,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 6:48:16 PM"},{"consumptionId":"ebe10e1ca66240ca83f221dbcce941ef","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:08:22 PM"},{"consumptionId":"f631e4c8b7644ffba65d5d92cab32c7c","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 8:28:48 PM"},{"consumptionId":"fbb768d6da9b4e5d9133b7eff3c7c7b0","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:20:47 PM"}]
     */

    private String errorCode;
    private String errorMsg;
    private List<ConsumptionsBean> consumptions;

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

    public List<ConsumptionsBean> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<ConsumptionsBean> consumptions) {
        this.consumptions = consumptions;
    }

    public static class ConsumptionsBean {
        /**
         * consumptionId : 3621ebf5315f44948225eecd803c4bad
         * customerId : 373a7f1fca8d4689b98ae0db571a665c
         * phoneNumber : 13389216484
         * consumptionAmount : 7
         * merchantId : 18701646700
         * consumptionTime : Apr 16, 2019 9:08:32 PM
         */

        private String consumptionId;
        private String customerId;
        private String phoneNumber;
        private int consumptionAmount;
        private String merchantId;
        private String consumptionTime;

        public String getConsumptionId() {
            return consumptionId;
        }

        public void setConsumptionId(String consumptionId) {
            this.consumptionId = consumptionId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
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
    }
}
