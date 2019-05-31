package com.bai.nfc.bean;

import java.util.List;

public class OrderDsc {


    /**
     * errorCode : 00
     * errorMsg : 成功
     * buyJournals : [{"buyJournalId":1826,"goodsId":"12","goodsName":"xxx","consumptionId":"a8d1e670451045e9903a817def29ea1f","quantity":1,"price":100,"buyJournalRs":[{"buyJournalRId":36,"buyJournalId":1826,"goodsId":12,"consumptionRId":"488b08b78965412a892858ea275c83e9","consumptionId":"a8d1e670451045e9903a817def29ea1f","quantity":1,"price":100,"insertTime":"May 3, 2019 3:25:03 AM","updateTime":"May 3, 2019 3:25:03 AM"}]}]
     */

    private String errorCode;
    private String errorMsg;
    private List<BuyJournalsBean> buyJournals;

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

    public List<BuyJournalsBean> getBuyJournals() {
        return buyJournals;
    }

    public void setBuyJournals(List<BuyJournalsBean> buyJournals) {
        this.buyJournals = buyJournals;
    }

    public static class BuyJournalsBean {
        /**
         * buyJournalId : 1826
         * goodsId : 12
         * goodsName : xxx
         * consumptionId : a8d1e670451045e9903a817def29ea1f
         * quantity : 1
         * price : 100
         * buyJournalRs : [{"buyJournalRId":36,"buyJournalId":1826,"goodsId":12,"consumptionRId":"488b08b78965412a892858ea275c83e9","consumptionId":"a8d1e670451045e9903a817def29ea1f","quantity":1,"price":100,"insertTime":"May 3, 2019 3:25:03 AM","updateTime":"May 3, 2019 3:25:03 AM"}]
         */

        private int buyJournalId;
        private String goodsId;
        private String goodsName;
        private String consumptionId;
        private int quantity;

        public int getSelectCount() {
            return selectCount;
        }

        public void setSelectCount(int selectCount) {
            this.selectCount = selectCount;
        }

        private int selectCount;
        private int price;
        private List<BuyJournalRsBean> buyJournalRs;

        public int getBuyJournalId() {
            return buyJournalId;
        }

        public void setBuyJournalId(int buyJournalId) {
            this.buyJournalId = buyJournalId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getConsumptionId() {
            return consumptionId;
        }

        public void setConsumptionId(String consumptionId) {
            this.consumptionId = consumptionId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public List<BuyJournalRsBean> getBuyJournalRs() {
            return buyJournalRs;
        }

        public void setBuyJournalRs(List<BuyJournalRsBean> buyJournalRs) {
            this.buyJournalRs = buyJournalRs;
        }

        public static class BuyJournalRsBean {
            /**
             * buyJournalRId : 36
             * buyJournalId : 1826
             * goodsId : 12
             * consumptionRId : 488b08b78965412a892858ea275c83e9
             * consumptionId : a8d1e670451045e9903a817def29ea1f
             * quantity : 1
             * price : 100
             * insertTime : May 3, 2019 3:25:03 AM
             * updateTime : May 3, 2019 3:25:03 AM
             */

            private int buyJournalRId;
            private int buyJournalId;
            private int goodsId;
            private String consumptionRId;
            private String consumptionId;
            private int quantity;
            private int price;
            private String insertTime;
            private String updateTime;

            public int getBuyJournalRId() {
                return buyJournalRId;
            }

            public void setBuyJournalRId(int buyJournalRId) {
                this.buyJournalRId = buyJournalRId;
            }

            public int getBuyJournalId() {
                return buyJournalId;
            }

            public void setBuyJournalId(int buyJournalId) {
                this.buyJournalId = buyJournalId;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getConsumptionRId() {
                return consumptionRId;
            }

            public void setConsumptionRId(String consumptionRId) {
                this.consumptionRId = consumptionRId;
            }

            public String getConsumptionId() {
                return consumptionId;
            }

            public void setConsumptionId(String consumptionId) {
                this.consumptionId = consumptionId;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
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
}
